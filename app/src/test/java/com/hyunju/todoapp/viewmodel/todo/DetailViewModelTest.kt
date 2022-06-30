package com.hyunju.todoapp.viewmodel.todo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hyunju.todoapp.data.Entity.ToDoEntity
import com.hyunju.todoapp.domain.todo.InsertToItemDoUseCase
import com.hyunju.todoapp.presentation.detail.DetailMode
import com.hyunju.todoapp.presentation.detail.DetailViewModel
import com.hyunju.todoapp.presentation.detail.ToDoDetailState
import com.hyunju.todoapp.presentation.list.ListViewModel
import com.hyunju.todoapp.presentation.list.ToDoListState
import com.hyunju.todoapp.viewmodel.ViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.parameter.parametersOf
import org.koin.test.inject
import java.lang.Exception

@ExperimentalCoroutinesApi
internal class DetailViewModelTest : ViewModelTest() {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val id = 1L

    private val detailViewModel by inject<DetailViewModel> { parametersOf(DetailMode.DETAIL, id) }
    private val listViewModel by inject<ListViewModel>()

    private val insertToItemDoItemUseCase: InsertToItemDoUseCase by inject()

    private val todo = ToDoEntity(
        id = id,
        title = "title $id",
        description = "description $id",
        hasCompleted = false
    )

    @Before
    fun init() {
        initData()
    }

    private fun initData() = runBlockingTest {
        insertToItemDoItemUseCase(todo)
    }

    @Test
    fun `test viewModel fetch`(): Unit = runBlockingTest {
        val testObservable = detailViewModel.toDoDetailLiveData.test()
        detailViewModel.fetchData()
        testObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Success(todo)
            )
        )
    }

    @Test
    fun `test delete todo`() = runBlockingTest {
        val detailTestObservable = detailViewModel.toDoDetailLiveData.test()
        detailViewModel.deleteToDoItem()
        detailTestObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Delete
            )
        )

        val listTestObservable = listViewModel.toDoListLiveData.test()
        listViewModel.fetchData()
        listTestObservable.assertValueSequence(
            listOf(
                ToDoListState.UnInitialized,
                ToDoListState.Loading,
                ToDoListState.Success(listOf()),
            )
        )
    }

    @Test
    fun `test update todo`() = runBlockingTest {
        val testObservable = detailViewModel.toDoDetailLiveData.test()

        val updateTitle = "title 1 update"
        val updateDescription = "description 1 update"

        val updateToDo = todo.copy(
            title = updateTitle,
            description = updateDescription
        )
        detailViewModel.writeToDoItem(
            title = updateTitle,
            description = updateDescription
        )

        testObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Success(updateToDo),
            )
        )
    }
}