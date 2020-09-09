package com.paulacr.wordslearning.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.paulacr.wordslearning.data.Word

@Database(entities = [Word::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}
