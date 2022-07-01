package com.hyunju.todoapp.domain.todo

import com.hyunju.todoapp.data.entity.ToDoEntity
import com.hyunju.todoapp.data.repository.ToDoRepository
import com.hyunju.todoapp.domain.UseCase

internal class InsertToItemDoUseCase(
    private val toDoRepository: ToDoRepository
) : UseCase {

    suspend operator fun invoke(toDoItem: ToDoEntity):Long {
        return toDoRepository.insertToDoItem(toDoItem)
    }

}