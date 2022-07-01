package com.hyunju.todoapp.domain.todo

import com.hyunju.todoapp.data.repository.ToDoRepository
import com.hyunju.todoapp.domain.UseCase

internal class DeleteToDoItemUseCase(
    private val toDoRepository: ToDoRepository
) : UseCase {

    suspend operator fun invoke(itemId: Long): Boolean {
        return toDoRepository.deleteToDoItem(itemId)
    }
}