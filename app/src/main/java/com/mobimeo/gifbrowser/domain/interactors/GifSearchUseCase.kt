package com.mobimeo.gifbrowser.domain.interactors

import com.mobimeo.gifbrowser.domain.interactors.base.BaseUseCase
import com.mobimeo.gifbrowser.domain.model.GifSearch
import com.mobimeo.gifbrowser.domain.model.Meta
import com.mobimeo.gifbrowser.domain.model.Pagination
import com.mobimeo.gifbrowser.domain.repo.GiphyRepository
import javax.inject.Inject

class GifSearchUseCase @Inject constructor(val gifRepository: GiphyRepository) :
    BaseUseCase<GifSearchUseCase.Params, GifSearchUseCase.Result> {

    override suspend fun run(params: Params): Result {
        val response = gifRepository.search(params.apiKey, params.search, params.offset)
        if (response.isSuccessful) {
            var data = response.body()
            if(data!=null){
                return Result(data, true, response.message())
            }else{
                return Result(GifSearch(arrayListOf(), Meta("","", 0), Pagination(0,0,0)), false, response.message())
            }
        } else {
            return Result(GifSearch(arrayListOf(), Meta("","", 0), Pagination(0,0,0)), false, response.message())
        }

    }

    class Params(val apiKey: String, val search: String, val offset: Int)

    class Result(data: GifSearch, isSuccess: Boolean, errMsg: String) {
        val data: GifSearch
        val isSuccess: Boolean
        val errMsg: String

        init {
            this.data = data
            this.isSuccess = isSuccess
            this.errMsg = errMsg
        }
    }

}