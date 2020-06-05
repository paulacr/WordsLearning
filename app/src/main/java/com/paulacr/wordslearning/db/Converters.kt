package com.paulacr.wordslearning.db

import androidx.room.TypeConverter
import com.paulacr.wordslearning.data.Language
import com.paulacr.wordslearning.data.WordItemType

class Converters {
    @TypeConverter
    fun fromWordItem(type: WordItemType) = type.value

    @TypeConverter
    fun toWordItem(value: Int) = value.toEnum<WordItemType>()

    @TypeConverter
    fun toLanguageEnum(value: String) = enumValueOf<Language>(value)

    @TypeConverter
    fun fromLanguageEnum(value: Language) = value.name
}

private inline fun <T : Enum<T>> T.toInt(): Int = this.ordinal

private inline fun <reified T : Enum<T>> Int.toEnum(): T = enumValues<T>()[this]
