package com.hyunju.todoapp.domain.todo

import com.hyunju.todoapp.data.Entity.ToDoEntity
import com.hyunju.todoapp.data.repository.ToDoRepository
import com.hyunju.todoapp.domain.UseCase

internal class GetToDoListUseCase(
    private val toDoRepository: ToDoRepository
) : UseCase {

    suspend operator fun invoke(): List<ToDoEntity> {
        return toDoRepository.getToDoList()
    }
}