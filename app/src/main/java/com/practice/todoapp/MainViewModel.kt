package com.practice.todoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.todoapp.db.ToDo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainViewModel"

@HiltViewModel
class MainViewModel @Inject constructor(private val toDoRepository: ToDoRepository) :
    ViewModel() {

    val todoList: LiveData<List<ToDo>?> = toDoRepository.getAllTodo()

    fun insertToDo(toDo: ToDo) = viewModelScope.launch {
        toDoRepository.insertToDo(toDo)
    }

    fun deleteToDo(toDo: ToDo) = viewModelScope.launch {
        toDoRepository.deleteToDo(toDo)
    }

    fun updateToDo(toDo: ToDo) = viewModelScope.launch {
        toDoRepository.updateToDo(toDo)
    }

    fun markComplete(todo: Int) = viewModelScope.launch {
        toDoRepository.markCompleteToDo(todo)
    }
}