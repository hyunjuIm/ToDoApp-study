package com.hyunju.todoapp.data.repository

import com.hyunju.todoapp.data.Entity.ToDoEntity

class DefaultToDoRepository:ToDoRepository {

    override suspend fun getToDoList(): List<ToDoEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun insertToDoList(toDoList: List<ToDoEntity>) {
        TODO("Not yet implemented")
    }
}