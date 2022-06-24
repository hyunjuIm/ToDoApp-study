package com.hyunju.todoapp.di

import com.hyunju.todoapp.data.repository.TestToDoRepository
import com.hyunju.todoapp.data.repository.ToDoRepository
import com.hyunju.todoapp.domain.todo.GetToDoListUseCase
import com.hyunju.todoapp.domain.todo.InsertToDoListUseCase
import com.hyunju.todoapp.presentation.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appTestModule = module {

    // ViewModel
    viewModel { ListViewModel(get()) }

    // UseCase
    factory { GetToDoListUseCase(get()) }
    factory { InsertToDoListUseCase(get()) }

    // Repository
    single<ToDoRepository> { TestToDoRepository() }
}