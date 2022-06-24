package com.hyunju.todoapp.domain.todo

import com.hyunju.todoapp.data.Entity.ToDoEntity
import com.hyunju.todoapp.data.repository.ToDoRepository
import com.hyunju.todoapp.domain.UseCase

internal class InsertToDoListUseCase(
    private val toDoRepository: ToDoRepository
) : UseCase {

    suspend operator fun invoke(toDoList: List<ToDoEntity>) {
        return toDoRepository.insertToDoList(toDoList)
    }

}