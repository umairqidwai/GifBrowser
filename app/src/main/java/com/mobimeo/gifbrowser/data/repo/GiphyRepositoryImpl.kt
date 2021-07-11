package com.mobimeo.gifbrowser.data.repo

import com.mobimeo.gifbrowser.data.remote.GiphyService
import com.mobimeo.gifbrowser.domain.model.GifSearch
import com.mobimeo.gifbrowser.domain.repo.GiphyRepository
import retrofit2.Response
import javax.inject.Inject

class GiphyRepositoryImpl @Inject constructor(val giphyService: GiphyService) :
    GiphyRepository {
    override suspend fun search(apiKey: String, search: String, offset: Int): Response<GifSearch> {
        return giphyService.search(apiKey, search, offset)
    }

}