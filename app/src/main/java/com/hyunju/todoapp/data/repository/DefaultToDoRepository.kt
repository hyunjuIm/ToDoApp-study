package com.hyunju.todoapp.data.repository

import com.hyunju.todoapp.data.Entity.ToDoEntity

class DefaultToDoRepository:ToDoRepository {

    override suspend fun getToDoList(): List<ToDoEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun insertToDoItem(toDoItem: ToDoEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun insertToDoList(toDoList: List<ToDoEntity>) {
        TODO("Not yet implemented")
    }

    override suspend fun updateToDoItem(toDoItem: ToDoEntity): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getTodoItem(itemId: Long): ToDoEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteToDoItem(id: Long): Boolean {
        TODO("Not yet implemented")
    }
}