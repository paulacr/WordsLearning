package com.paulacr.wordslearning.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.paulacr.wordslearning.data.ImageWord
import com.paulacr.wordslearning.data.TextWord

@Database(entities = [TextWord::class, ImageWord::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun textWordDao(): TextWordDao
    abstract fun imageWordDao(): ImageWordDao
}
