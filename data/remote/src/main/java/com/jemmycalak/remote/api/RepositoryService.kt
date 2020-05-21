package com.jemmycalak.remote.api

import com.jemmycalak.model.Repository
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RepositoryService {

    @GET("search/users")
    suspend fun searchRepository(@QueryMap query:HashMap<String, Any>):Response<Repository>
}