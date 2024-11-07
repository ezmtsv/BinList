package ru.netology.binlist.repo

import kotlinx.coroutines.flow.Flow
import ru.netology.binlist.dto.BinRequest

interface RepoBinReq {
    val binReqFlow: Flow<List<BinRequest>>
    suspend fun getBinReq(id: Long): BinRequest?
}