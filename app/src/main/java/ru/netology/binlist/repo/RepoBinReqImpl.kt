package ru.netology.binlist.repo

import kotlinx.coroutines.flow.map
import ru.netology.binlist.api.ApiService
import ru.netology.binlist.dao.BinReqDao
import ru.netology.binlist.dto.BinRequest
import ru.netology.binlist.entity.BinReqEntity
import ru.netology.binlist.entity.toDto
import ru.netology.binlist.error.ApiError
import ru.netology.binlist.error.ApiError403
import ru.netology.binlist.error.ApiError404
import ru.netology.binlist.error.DbError
import ru.netology.binlist.error.NetworkError
import ru.netology.binlist.error.UnknownErrors
import java.io.IOException
import java.sql.SQLException
import javax.inject.Inject

class RepoBinReqImpl @Inject constructor(
    private val apiService: ApiService,
    private val binDao: BinReqDao,
) : RepoBinReq {

    override val binReqFlow = binDao.getAllReq().map(List<BinReqEntity>::toDto)

    override suspend fun getBinReq(id: Long): BinRequest {
        try {
            val response = apiService.getBin(id, "8.8.8.8")
            val binReq = if (response.isSuccessful) {
                println("response: ${response.body()}")
                response.body()
            } else {
                println("response: ${response.errorBody()}")
                when (response.code()) {
                    403 -> throw ApiError403(response.code().toString())
                    404 -> throw ApiError404(response.code().toString())
                    else -> throw ApiError(response.code(), response.message())

                }
            }
            val lastId = binDao.count() ?: 0
            binDao.insertReq(BinReqEntity.fromDto(binReq!!.copy(id = lastId + 1)))
            return binReq
        } catch (e: IOException) {
            throw NetworkError
        } catch (e: SQLException) {
            throw DbError
        } catch (e: ApiError403) {
            throw ApiError403("403")
        } catch (e: ApiError404) {
            throw ApiError404("404")
        } catch (e: Exception) {
            throw UnknownErrors
        }

    }

}