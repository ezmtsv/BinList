package ru.netology.binlist.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import ru.netology.binlist.dto.Bin
import ru.netology.binlist.dto.BinRequest
import ru.netology.binlist.dto.Ip


@Entity(tableName = "BinReqEntity")
data class BinReqEntity (
    @PrimaryKey
    val id: Long,
    val success: Boolean? = null,
    val code: Int? = null,
    val bin: Bin? = null,
    val ip: Ip? = null
) {
    fun toDto() = BinRequest(
        id,
        success,
        code,
        bin,
        ip,
    )

    companion object {
        fun fromDto(dto: BinRequest) = BinReqEntity(
            dto.id,
            dto.success,
            dto.code,
            dto.bin,
            dto.ip,
        )
    }
}

fun List<BinReqEntity>.toDto(): List<BinRequest> = map(BinReqEntity::toDto)
fun List<BinRequest>.toEntity(): List<BinReqEntity> = map(BinReqEntity::fromDto)