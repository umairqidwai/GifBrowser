package com.mobimeo.gifbrowser.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobimeo.gifbrowser.domain.interactors.GifSearchUseCase
import com.mobimeo.gifbrowser.domain.model.GifSearch
import kotlinx.coroutines.*
import javax.inject.Inject

class GifBrowserViewModel @Inject constructor(val gifSearchUseCase: GifSearchUseCase) :
    ViewModel() {
    var job1: Job? = null
    var job2: Job? = null
    val gifList = MutableLiveData<GifSearch>()
    val gifMoreItemList = MutableLiveData<GifSearch>()
    val gifSearchError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun searchGif(apiKey: String, search: String) {
        loading.value = true
        job1 = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = gifSearchUseCase.run(GifSearchUseCase.Params(apiKey, search, 0))
            withContext(Dispatchers.Main) {
                if (response.isSuccess) {
                    gifList.value = response.data
                    gifSearchError.value = null
                    loading.value = false
                } else {
                    onError("Error : ${response.errMsg}")
                }
            }
        }
    }

    fun loadMoreGif(apiKey: String, search: String, offSet: Int) {
        loading.value = true
        job2 = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = gifSearchUseCase.run(GifSearchUseCase.Params(apiKey, search, offSet))
            withContext(Dispatchers.Main) {
                if (response.isSuccess) {
                    //update offset value
                    gifMoreItemList.value = response.data
                    gifSearchError.value = null
                    loading.value = false
                } else {
                    onError("Error : ${response.errMsg}")
                }
            }


        }
    }

    private fun onError(message: String) {
        CoroutineScope(Dispatchers.Main).launch {
            gifSearchError.value = message
            loading.value = false

        }

    }

    override fun onCleared() {
        super.onCleared()
        job1?.cancel()
        job2?.cancel()
    }


}