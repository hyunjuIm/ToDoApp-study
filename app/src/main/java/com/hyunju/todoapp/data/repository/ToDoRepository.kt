package com.hyunju.todoapp.data.repository

import com.hyunju.todoapp.data.Entity.ToDoEntity

interface ToDoRepository {

    suspend fun getToDoList(): List<ToDoEntity>

    suspend fun insertToDoList(toDoList: List<ToDoEntity>)
}