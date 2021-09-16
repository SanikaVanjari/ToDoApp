package com.practice.todoapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import javax.inject.Inject

@Database(entities = [ToDo::class], version = 1, exportSchema = false)
abstract class ToDoDatabase() : RoomDatabase() {
    abstract fun todoDao(): ToDoDao

//    companion object {
//        @Volatile
//        private var instance: ToDoDatabase? = null
//
//        fun getInstance(context: Context): ToDoDatabase {
//            return instance ?: synchronized(this) {
//                instance ?: buildDatabase(context).also { instance = it }
//            }
//        }
//
//        private fun buildDatabase(context: Context): ToDoDatabase {
//            return Room.databaseBuilder(context, ToDoDatabase::class.java, "todo_db").build()
//        }
//    }
}