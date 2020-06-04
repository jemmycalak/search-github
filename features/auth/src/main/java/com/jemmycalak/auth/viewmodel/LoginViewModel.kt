package com.jemmycalak.auth.viewmodel

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.jemmycalak.auth.fragment.LoginFragmentDirections
import com.jemmycalak.auth.utils.Bindings
import com.jemmycalak.common.base.BaseViewModel
import com.jemmycalak.repository.utils.AppDispatchers

class LoginViewModel(private val context:Context, dispatchers: AppDispatchers) : BaseViewModel(dispatchers) {

    val password = MutableLiveData<String>()
    val username = MutableLiveData<String>()

    fun onLogin(v: View){
        if(username.value.isNullOrBlank() || password.value.isNullOrBlank() )return

        if(!Bindings.isPhone(username.value!!))
            if(!Bindings.isEmail(username.value!!)) return

        if(!password.value!!.matches(Bindings.passwordRegex.toRegex())) return

        v.findNavController().navigate(LoginFragmentDirections.loginToListRepo())
    }
}