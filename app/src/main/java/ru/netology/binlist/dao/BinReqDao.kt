package ru.netology.binlist.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.netology.binlist.entity.BinReqEntity

@Dao
interface BinReqDao {
    @Query("SELECT * FROM BinReqEntity ORDER BY id DESC")
    fun getAllReq(): Flow<List<BinReqEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReq(reg: BinReqEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllReq(requests: List<BinReqEntity>)

    @Query("DELETE FROM BinReqEntity WHERE id = :id")
    suspend fun removeReqById(id: Long)

    @Query("DELETE FROM BinReqEntity")
    suspend fun removeReq()

    @Query("SELECT COUNT() FROM BinReqEntity")
    suspend fun count(): Long?
}