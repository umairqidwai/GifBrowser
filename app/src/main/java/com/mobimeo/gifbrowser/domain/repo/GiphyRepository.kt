package com.mobimeo.gifbrowser.domain.repo

import com.mobimeo.gifbrowser.domain.model.GifSearch
import retrofit2.Response

interface GiphyRepository {
    suspend fun search(apiKey: String, search: String, offset: Int): Response<GifSearch>
}