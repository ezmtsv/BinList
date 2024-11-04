package ru.netology.binlist.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.netology.binlist.dao.BinReqDao
import ru.netology.binlist.dto.Bin
import ru.netology.binlist.dto.Ip
import ru.netology.binlist.entity.BinReqEntity

@TypeConverters(
    DataConvertorBin::class,
    DataConvertorIp::class
)
@Database(
    entities = [BinReqEntity::class],
    version = 1
)
abstract class AppDb: RoomDatabase() {
    abstract fun binReqDao(): BinReqDao
}

class DataConvertorBin {
    private val type = object : TypeToken<Bin?>() {}.type

    @TypeConverter
    fun toGson(obj: Bin?): String =
        Gson().toJson(obj)

    @TypeConverter
    fun fromGson(str: String): Bin? =
        Gson().fromJson<Bin?>(str, type)
}

class DataConvertorIp {
    private val type = object : TypeToken<Ip?>() {}.type

    @TypeConverter
    fun toGson(obj: Ip?): String =
        Gson().toJson(obj)

    @TypeConverter
    fun fromGson(str: String): Ip? =
        Gson().fromJson<Ip?>(str, type)
}