package com.paulacr.wordslearning.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.paulacr.wordslearning.data.TextWord
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface TextWordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg textWord: TextWord)

    @Insert
    fun insert(textWord: TextWord): Completable

    @Query("SELECT * FROM text_word")
    fun loadAllUsers(): Single<List<TextWord>>
}
