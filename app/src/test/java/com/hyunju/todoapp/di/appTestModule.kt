package com.hyunju.todoapp.di

import com.hyunju.todoapp.data.repository.TestToDoRepository
import com.hyunju.todoapp.data.repository.ToDoRepository
import com.hyunju.todoapp.domain.todo.*
import com.hyunju.todoapp.presentation.detail.DetailMode
import com.hyunju.todoapp.presentation.detail.DetailViewModel
import com.hyunju.todoapp.presentation.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appTestModule = module {

    // ViewModel
    viewModel { ListViewModel(get(), get(), get()) }
    viewModel { (detailMode: DetailMode, id: Long) ->
        DetailViewModel(
            detailMode = detailMode,
            id = id,
            get(),
            get(),
            get(),
            get()
        )
    }

    // UseCase
    factory { GetToDoListUseCase(get()) }
    factory { InsertToDoListUseCase(get()) }
    factory { InsertToItemDoUseCase(get()) }
    factory { UpdateToDoUseCase(get()) }
    factory { GetToDoItemUseCase(get()) }
    factory { DeleteAllToDoItemUseCase(get()) }
    factory { DeleteToDoItemUseCase(get()) }

    // Repository
    single<ToDoRepository> { TestToDoRepository() }
}