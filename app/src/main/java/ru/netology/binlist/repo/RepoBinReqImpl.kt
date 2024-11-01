package ru.netology.binlist.repo

import androidx.lifecycle.asLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import ru.netology.binlist.BuildConfig.API_KEY
import ru.netology.binlist.api.ApiService
import ru.netology.binlist.dao.BinReqDao
import ru.netology.binlist.db.AppDb
import ru.netology.binlist.dto.Bank
import ru.netology.binlist.dto.BinRequest
import ru.netology.binlist.dto.Card
import ru.netology.binlist.dto.Country
import ru.netology.binlist.dto.Data
import ru.netology.binlist.entity.BinReqEntity
import ru.netology.binlist.entity.toDto
import ru.netology.binlist.error.ApiError
import ru.netology.binlist.error.ApiError403
import ru.netology.binlist.error.ApiError404
import ru.netology.binlist.error.NetworkError
import ru.netology.binlist.error.UnknownErrors
import java.io.IOException
import javax.inject.Inject

class RepoBinReqImpl @Inject constructor(
    private val apiService: ApiService,
    private val binDao: BinReqDao,
) : RepoBinReq {
    private val _binReqFlow = MutableStateFlow(emptyList<BinRequest>())
    override val binReqFlow: Flow<List<BinRequest>>
        get() = _binReqFlow.asStateFlow()

    val req = BinRequest(
        result = 200,
        message = "SUCCESS",
        data = Data(
            card = Card(
                scheme = "Visa",
                type = "Debit",
                category = "Corporate t&e",
                length = -1,
                checkluhn = 1,
                cvvlen = 3
            ),

            country = Country(
                name = "Egypt",
                code = "eg",
                flag = "🇪🇬",
                currency = "Egyptian pound",
                currencyCode = "EGP"
            ),

            bank = Bank(
                name = "ARAB INTERNATIONAL BANK",
                website = "http://www.aib.com.eg:81/web/wps/portal/enaib",
                phone = "23918794"
            ),
        )
    )

    override suspend fun getBinReq(id: Long): BinRequest? {
        try {
            val response = apiService.getBin(id, API_KEY)
            val binReq = if (response.isSuccessful) {
                println("response: ${response.body()}")
//                println(getFieldsObj(response.body()))
                response.body()
            } else {
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
        } catch (e: ApiError403) {
            throw ApiError403("403")
        } catch (e: ApiError404) {
            throw ApiError404("404")
        } catch (e: Exception) {
            throw UnknownErrors
        }

//        try {
//            val lastId = binDao.count() ?: 0
//            binDao.insertReq(BinReqEntity.fromDto(req.copy(id = lastId + 1)))
//
////            val bins = binDao.getAllReq().map(List<BinReqEntity>::toDto)
////            bins.flowOn(Dispatchers.IO).collect{list ->
////                _binReqFlow.update { list }
////            }
//            return req
//        } catch (e: Exception) {
//            println("Exception DataBase")
//        }
//        return null
    }

    override suspend fun getAllBinReq() {
        try {
//            val bins = binDao.getAllReq().map(List<BinReqEntity>::toDto)
//            bins.flowOn(Dispatchers.IO).collect{list ->
//                _binReqFlow.update { list }
//            }

            binDao.getAllReq().flowOn(Dispatchers.IO).collect { list ->
                _binReqFlow.update { list.toDto() }
            }

        } catch (e: Exception) {
            println("Exception DataBase")
        }
    }
}