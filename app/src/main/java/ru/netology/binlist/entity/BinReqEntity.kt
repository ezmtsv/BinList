package ru.netology.binlist.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.binlist.dto.BinRequest
import ru.netology.binlist.dto.Data

@Entity(tableName = "BinReqEntity")
data class BinReqEntity (
    @PrimaryKey
    val id: Long,
    val data: Data? = null,
    val message: String? = null,
    val result: Int? = null
) {
    fun toDto() = BinRequest(
        id,
        data,
        message,
        result,
    )

    companion object {
        fun fromDto(dto: BinRequest) = BinReqEntity(
            dto.id,
            dto.data,
            dto.message,
            dto.result,
        )
    }
}

fun List<BinReqEntity>.toDto(): List<BinRequest> = map(BinReqEntity::toDto)
fun List<BinRequest>.toEntity(): List<BinReqEntity> = map(BinReqEntity::fromDto)