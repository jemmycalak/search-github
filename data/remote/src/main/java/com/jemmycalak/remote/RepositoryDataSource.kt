package com.jemmycalak.remote

import com.jemmycalak.model.Repository
import com.jemmycalak.remote.api.RepositoryService
import com.jemmycalak.remote.utils.Constants as k
import retrofit2.Response

class RepositoryDataSource(val api:RepositoryService) {

    suspend fun searchRepository(q:String, page:Int, per_page:Int):Response<Repository> {
        val queryMap = HashMap<String, Any>()
        queryMap[k.query] = q
        queryMap[k.page] = page
        queryMap[k.pageSize] = per_page
        return api.searchRepository(queryMap)
    }
}