package com.hyunju.todoapp.viewmodel.todo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hyunju.todoapp.data.Entity.ToDoEntity
import com.hyunju.todoapp.domain.todo.InsertToDoListUseCase
import com.hyunju.todoapp.presentation.list.ListViewModel
import com.hyunju.todoapp.viewmodel.ViewModelTest
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.inject

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
internal class ListViewModelTest : ViewModelTest() {

    private val viewModel: ListViewModel by inject()

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val insertToDoListUseCase: InsertToDoListUseCase by inject()

    private val mockList = (0 until 10).map {
        ToDoEntity(
            id = it.toLong(),
            title = "title $it",
            description = "description $it",
            hasCompleted = false
        )
    }

    @Before
    fun init() {
        initData()
    }

    private fun initData() = runBlockingTest {
        insertToDoListUseCase(mockList)
    }

    @Test
    fun `test viewModel fetch`(): Unit = runBlockingTest {
        val testObservable = viewModel.toDoListLiveData.test()
        viewModel.fetchData()
        testObservable.assertValueSequence(listOf(mockList))
    }

}