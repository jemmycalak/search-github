package com.jemmycalak.listrepository.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jemmycalak.common.base.BaseViewModel
import com.jemmycalak.common.utils.ErrorHandler
import com.jemmycalak.common.utils.Event
import com.jemmycalak.listrepository.domain.Services
import com.jemmycalak.model.Repository
import com.jemmycalak.repository.utils.AppDispatchers
import com.jemmycalak.repository.utils.Resource
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListRespositoryViewModel(
    private val dispatchers: AppDispatchers,
    private val service: Services
) : BaseViewModel(dispatchers) {

    private val _repositoryMediator = MediatorLiveData<Resource<Repository>>()
    private var repositoryResource: LiveData<Resource<Repository>> =
        MutableLiveData<Resource<Repository>>()
    val dataRepository: LiveData<Resource<Repository>> get() = _repositoryMediator
    val loaderRepository: MutableLiveData<Boolean> = MutableLiveData()

    fun searchRepository(q: String, page: Int, per_page: Int, shouldFetch: Boolean) {
        viewModelScope.launch(dispatchers.main) {
            _repositoryMediator.removeSource(repositoryResource)
            withContext(dispatchers.io) {
                repositoryResource = service(q, page, per_page, shouldFetch)
            }
            _repositoryMediator.addSource(repositoryResource) {
                loaderRepository.postValue(it.status == Resource.Status.LOADING)
                when (it.status) {
                    Resource.Status.ERROR -> {
                        _errorHandler.postValue(
                            Event(
                                ErrorHandler(
                                    ErrorHandler.ErrorType.SNACKBAR,
                                    it.error
                                )
                            )
                        )
                    }
                }
                _repositoryMediator.postValue(it)
            }
        }
    }
}