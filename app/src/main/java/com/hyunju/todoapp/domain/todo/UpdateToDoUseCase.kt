package com.hyunju.todoapp.domain.todo

import com.hyunju.todoapp.data.entity.ToDoEntity
import com.hyunju.todoapp.data.repository.ToDoRepository
import com.hyunju.todoapp.domain.UseCase

internal class UpdateToDoUseCase(
    private val toDoRepository: ToDoRepository
) : UseCase {

    suspend operator fun invoke(toDoEntity: ToDoEntity): Int {
        return toDoRepository.updateToDoItem(toDoEntity)
    }

}