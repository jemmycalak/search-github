package com.jemmycalak.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jemmycalak.common.utils.ErrorHandler
import com.jemmycalak.common.utils.Event
import com.jemmycalak.repository.utils.AppDispatchers

abstract class BaseViewModel(
    private val dispatchers: AppDispatchers
) : ViewModel() {

    protected val _errorHandler = MutableLiveData<Event<ErrorHandler>>()
    val errorHandler: LiveData<Event<ErrorHandler>> get() = _errorHandler

}