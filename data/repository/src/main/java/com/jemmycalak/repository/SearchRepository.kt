package com.jemmycalak.repository

import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.jemmycalak.model.Repository
import com.jemmycalak.remote.RepositoryDataSource
import com.jemmycalak.repository.utils.NetworkResource
import com.jemmycalak.repository.utils.Resource
import retrofit2.Response

interface SearchRepositoryListener{
    suspend fun searchRepository(q:String, page:Int, per_page:Int, shouldFetch:Boolean):LiveData<Resource<Repository>>
}

class SearchRepository(
    val dataSource : RepositoryDataSource,
    val gson:Gson
):SearchRepositoryListener {
    override suspend fun searchRepository(q:String, page:Int, per_page:Int, shouldFetch:Boolean): LiveData<Resource<Repository>> {
        return object : NetworkResource<Repository, Repository>(gson){

            override fun processResponse(response: Repository): Repository = response

            override suspend fun createCallAsync(): Response<Repository> = dataSource.searchRepository(q, page, per_page)

        }.build().asLiveData()
    }
}