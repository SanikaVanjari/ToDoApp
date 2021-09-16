package com.practice.todoapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.todoapp.db.ToDo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainViewModel"

@HiltViewModel
class MainViewModel @Inject constructor(private val toDoRepository: ToDoRepository) : ViewModel() {

    private var _todoList = MutableLiveData<List<ToDo?>>()
    val todoList: LiveData<List<ToDo?>> get() = _todoList

    init {
        viewModelScope.launch {
            getAllToDos()
        }
    }

    private suspend fun getAllToDos() {
        _todoList.postValue(toDoRepository.getAllTodo())
    }

    fun insertToDo(toDo: ToDo) {
        viewModelScope.launch {
            toDoRepository.insertToDo(toDo)
        }
    }


    suspend fun deleteToDo(toDo: ToDo) = toDoRepository.deleteToDo(toDo)
}