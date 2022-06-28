package com.hyunju.todoapp.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyunju.todoapp.data.Entity.ToDoEntity
import com.hyunju.todoapp.domain.todo.GetToDoListUseCase
import com.hyunju.todoapp.domain.todo.UpdateToDoUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class ListViewModel(
    private val getToDoListUseCase: GetToDoListUseCase,
    private val updateToDoUseCase: UpdateToDoUseCase
) : ViewModel() {

    private var _toDoListLiveData = MutableLiveData<List<ToDoEntity>>()
    val toDoListLiveData: LiveData<List<ToDoEntity>> = _toDoListLiveData

    fun fetchData(): Job = viewModelScope.launch {
        _toDoListLiveData.postValue(getToDoListUseCase())
    }

    fun updateEntity(toDoEntity: ToDoEntity) = viewModelScope.launch {
        updateToDoUseCase(toDoEntity)
    }
}