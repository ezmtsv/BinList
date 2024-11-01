package ru.netology.binlist.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.netology.binlist.dao.BinReqDao
import ru.netology.binlist.dto.Data
import ru.netology.binlist.entity.BinReqEntity

@TypeConverters(
    DataConvertor::class,
)
@Database(
    entities = [BinReqEntity::class],
    version = 1
)
abstract class AppDb: RoomDatabase() {
    abstract fun binReqDao(): BinReqDao
}

class DataConvertor {
    private val type = object : TypeToken<Data?>() {}.type

    @TypeConverter
    fun toGson(obj: Data?): String =
        Gson().toJson(obj)

    @TypeConverter
    fun fromGson(str: String): Data? =
        Gson().fromJson<Data?>(str, type)
}