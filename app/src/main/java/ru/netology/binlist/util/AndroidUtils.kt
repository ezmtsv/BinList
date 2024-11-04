package ru.netology.binlist.util

import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.netology.binlist.dto.BinRequest
import java.lang.reflect.Type
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1

object AndroidUtils {

    fun getTime(str: String?): String {
        //"2023-10-17T13:01:59.846Z"
        //"2024-04-25T15:00:33.874Z"
        str?.let{
            try {
                val yyyy = it.subSequence(0, 4)
                val mm = it.subSequence(5, 7)
                val dd = it.subSequence(8, 10)
                val hh = it.subSequence(11, 13)
                val min = it.subSequence(14, 16)
                return "$dd.$mm.$yyyy  $hh:$min"
            } catch (e: Exception) {
                println(e.printStackTrace())
            }
        }
        return " "
    }

    fun <T> getFieldsObj(obj: T?): HashMap<String?, String?> {  // возвращает поля класса и их значения, в gradle нужно добавить implementation "org.jetbrains.kotlin:kotlin-reflect:1.9.20"
        val getObj: HashMap<String?, String?> = hashMapOf()
        obj?.let {
            it::class.java
                .declaredFields.forEach { field ->
                    val k = field.name
                    val v = readInstanceProperty(obj, field.name)
                    //println("$k = $v}")
                    with(getObj) { put(key = k, value = v) }
                }
        }
        return getObj
    }

    @Suppress("UNCHECKED_CAST")
    private fun readInstanceProperty(instance: Any, propertyName: String): String {
        val property = instance::class.members
            // don't cast here to <Any, R>, it would succeed silently
            .first { it.name == propertyName } as KProperty1<Any, *>
        // force a invalid cast exception if incorrect type here
        return property.get(instance).toString()
    }


}

object BinArg : ReadWriteProperty<Bundle, BinRequest?> {

    override fun setValue(thisRef: Bundle, property: KProperty<*>, value: BinRequest?) {
        thisRef.putString(property.name, Gson().toJson(value))
    }

    override fun getValue(thisRef: Bundle, property: KProperty<*>): BinRequest? {
        val listType: Type = object : TypeToken<BinRequest?>() {}.type
        return Gson().fromJson<BinRequest?>(thisRef.getString(property.name), listType)
    }
}

object LongEditArg : ReadWriteProperty<Bundle, Long> {
    override fun getValue(thisRef: Bundle, property: KProperty<*>): Long =
        thisRef.getLong(property.name)

    override fun setValue(thisRef: Bundle, property: KProperty<*>, value: Long) {
        thisRef.putLong(property.name, value)
    }
}