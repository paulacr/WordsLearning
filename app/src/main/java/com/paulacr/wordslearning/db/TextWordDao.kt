package com.paulacr.wordslearning.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.paulacr.wordslearning.data.TextWord
import io.reactivex.Completable

@Dao
interface TextWordDao {

    @Insert
    fun insertAll(vararg textWord: TextWord)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(textWord: TextWord): Completable
}
