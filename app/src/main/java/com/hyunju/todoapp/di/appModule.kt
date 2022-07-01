package com.hyunju.todoapp.di

import android.content.Context
import androidx.room.Room
import com.hyunju.todoapp.data.local.db.ToDoDatabase
import com.hyunju.todoapp.data.repository.DefaultToDoRepository
import com.hyunju.todoapp.data.repository.ToDoRepository
import com.hyunju.todoapp.domain.todo.DeleteAllToDoItemUseCase
import com.hyunju.todoapp.domain.todo.DeleteToDoItemUseCase
import com.hyunju.todoapp.domain.todo.GetToDoItemUseCase
import com.hyunju.todoapp.domain.todo.GetToDoListUseCase
import com.hyunju.todoapp.domain.todo.InsertToDoListUseCase
import com.hyunju.todoapp.domain.todo.InsertToItemDoUseCase
import com.hyunju.todoapp.domain.todo.UpdateToDoUseCase
import com.hyunju.todoapp.presentation.detail.DetailMode
import com.hyunju.todoapp.presentation.detail.DetailViewModel
import com.hyunju.todoapp.presentation.list.ListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appModule = module {

    single { Dispatchers.Main }
    single { Dispatchers.IO }

    factory { GetToDoListUseCase(get()) }
    factory { GetToDoItemUseCase(get()) }
    factory { InsertToDoListUseCase(get()) }
    factory { InsertToItemDoUseCase(get()) }
    factory { DeleteToDoItemUseCase(get()) }
    factory { DeleteAllToDoItemUseCase(get()) }
    factory { UpdateToDoUseCase(get()) }

    single<ToDoRepository> { DefaultToDoRepository(get(), get()) }

    single { provideDB(androidApplication()) }
    single { provideToDoDao(get()) }

    viewModel { ListViewModel(get(), get(), get()) }
    viewModel { (detailMode: DetailMode, id: Long) ->
        DetailViewModel(
            detailMode,
            id,
            get(),
            get(),
            get(),
            get()
        )
    }

}

fun provideDB(context: Context): ToDoDatabase =
    Room.databaseBuilder(context, ToDoDatabase::class.java, ToDoDatabase.DB_NAME).build()

fun provideToDoDao(database: ToDoDatabase) = database.toDoDao()