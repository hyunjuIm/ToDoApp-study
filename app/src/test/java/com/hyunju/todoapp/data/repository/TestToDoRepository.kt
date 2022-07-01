package com.hyunju.todoapp.data.repository

import com.hyunju.todoapp.data.entity.ToDoEntity

class TestToDoRepository : ToDoRepository {

    private val toDoList: MutableList<ToDoEntity> = mutableListOf()

    override suspend fun getToDoList(): List<ToDoEntity> {
        return toDoList
    }

    override suspend fun insertToDoItem(toDoItem: ToDoEntity): Long {
        this.toDoList.add(toDoItem)
        return toDoItem.id
    }

    override suspend fun insertToDoList(toDoList: List<ToDoEntity>) {
        this.toDoList.addAll(toDoList)
    }

    override suspend fun updateToDoItem(toDoItem: ToDoEntity): Boolean {
        val foundTodoEntity = toDoList.find { it.id == toDoItem.id }
        return if (foundTodoEntity == null) {
            false
        } else {
            this.toDoList[toDoList.indexOf(foundTodoEntity)] = toDoItem
            true
        }
    }

    override suspend fun getTodoItem(itemId: Long): ToDoEntity? {
        return toDoList.find { it.id == itemId }
    }

    override suspend fun deleteAll() {
        toDoList.clear()
    }

    override suspend fun deleteToDoItem(id: Long): Boolean {
        val foundTodoEntity = toDoList.find { it.id == id }
        return if (foundTodoEntity == null) {
            false
        } else {
            this.toDoList.removeAt(toDoList.indexOf(foundTodoEntity))
            true
        }
    }
}