package ru.netology.binlist.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import ru.netology.binlist.dto.BinRequest
import ru.netology.binlist.model.FeedModelState
import ru.netology.binlist.repo.RepoBinReq
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
@SuppressLint("StaticFieldLeak")
class BinReqViewModel @Inject constructor(
    private val repo: RepoBinReq
): ViewModel() {

    private val _dataState = MutableLiveData<FeedModelState>()
    val dataState: LiveData<FeedModelState>
        get() = _dataState

    private val _binReq = MutableLiveData<BinRequest>()
    val binReq: LiveData<BinRequest>
        get() = _binReq

    val listBins: LiveData<List<BinRequest>> = repo.binReqFlow.asLiveData(Dispatchers.IO)

    init {
        loadReq(403244)
        getBins()
    }

    private fun getBins() {
        viewModelScope.launch {
            try {
                repo.getAllBinReq()
            } catch (e: Exception) {

            }
        }

    }

    private  fun loadReq(bin: Long) {
        viewModelScope.launch {
            try {
                var binStr = bin.toString()
                if(binStr.length < 6) {
                    for(i in 0..5-binStr.length) {
                        binStr = "${binStr}0"
                    }
                } else if (binStr.length > 6){
                    binStr = binStr.substring(0, 6)
                }
                _dataState.value = FeedModelState(loading = true)
                _binReq.value = repo.getBinReq(binStr.toLong())

            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    private fun handleError(e: Exception) {
        when (e.javaClass.name) {
            "ru.netology.binlist.error.ApiError403" -> {
                _dataState.value = FeedModelState(error403 = true)
            }

            "ru.netology.binlist.error.ApiError400" -> {
                _dataState.value = FeedModelState(error400 = true)
            }

            "ru.netology.binlist.error.ApiError415" -> {
                _dataState.value = FeedModelState(error415 = true)
            }

            "ru.netology.binlist.error.ApiError404" -> {
                _dataState.value = FeedModelState(error404 = true)
            }

            else -> {

                _dataState.value = FeedModelState(error = true)
            }
        }
    }
}