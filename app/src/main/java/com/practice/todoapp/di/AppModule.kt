package com.practice.todoapp.di

import android.content.Context
import androidx.room.Room
import com.practice.todoapp.db.ToDoDao
import com.practice.todoapp.db.ToDoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideToDoRoomDb(@ApplicationContext context: Context): ToDoDatabase =
        Room.databaseBuilder(context, ToDoDatabase::class.java, "todo_db").build()

    @Singleton
    @Provides
    fun provideToDoService(db: ToDoDatabase): ToDoDao = db.todoDao()
}