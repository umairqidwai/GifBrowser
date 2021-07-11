package com.mobimeo.gifbrowser.data.remote

import com.mobimeo.gifbrowser.domain.model.GifSearch
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyService {
    @GET("search")
    suspend fun search(@Query("api_key") apiKey: String, @Query("q") search: String, @Query("offset") offset: Int): Response<GifSearch>
}