package com.practice.todoapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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

    private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE todo ADD COLUMN complete INTEGER default 0 NOT NULL")
        }

    }

    @Singleton
    @Provides
    fun provideToDoRoomDb(@ApplicationContext context: Context): ToDoDatabase =
        Room.databaseBuilder(context, ToDoDatabase::class.java, "todo_db").addMigrations(
            MIGRATION_1_2
        ).build()

    @Singleton
    @Provides
    fun provideToDoService(db: ToDoDatabase): ToDoDao = db.todoDao()
}