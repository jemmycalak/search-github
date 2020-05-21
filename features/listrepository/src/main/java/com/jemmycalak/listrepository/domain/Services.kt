package com.jemmycalak.listrepository.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.jemmycalak.model.Repository
import com.jemmycalak.repository.SearchRepositoryListener
import com.jemmycalak.repository.utils.Resource

class Services(val listRepository: SearchRepositoryListener) {

    suspend operator fun invoke(q:String, page:Int, per_page:Int, shouldFetch:Boolean) : LiveData<Resource<Repository>> =
        Transformations.map(listRepository.searchRepository(q, page, per_page, shouldFetch)){it}
}