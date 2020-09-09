package com.paulacr.wordslearning.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.paulacr.wordslearning.data.MockWordData
import com.paulacr.wordslearning.data.Word

@Dao
interface WordDao {

    @Query("SELECT * FROM Word")
    suspend fun getAllWords(): List<Word> = MockWordData().getMockedWordsList()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveWord(word: Word): Long
}
