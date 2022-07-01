package com.hyunju.todoapp.data.repository

import com.hyunju.todoapp.data.entity.ToDoEntity

interface ToDoRepository {

    suspend fun getToDoList(): List<ToDoEntity>

    suspend fun insertToDoItem(toDoItem: ToDoEntity): Long

    suspend fun insertToDoList(toDoList: List<ToDoEntity>)

    suspend fun updateToDoItem(toDoItem: ToDoEntity): Int

    suspend fun getTodoItem(itemId: Long): ToDoEntity?

    suspend fun deleteAll()

    suspend fun deleteToDoItem(id: Long): Int
}