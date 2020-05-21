package com.jemmycalak.common.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.jemmycalak.common.utils.ErrorHandler
import com.jemmycalak.common.utils.showSnackbar

abstract class BaseFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        super.onCreate(savedInstanceState)
        setupObserver(getViewModel())
    }

    open fun setupObserver(viewModel: BaseViewModel) {
        viewModel.errorHandler.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let {

                when (it.errorType) {
                    ErrorHandler.ErrorType.SNACKBAR -> {
                        if (it.errorResponse?.code == 403) {
                            showSnackbar("Limit request terlampaui", Snackbar.LENGTH_LONG)
                        }else{
                            showSnackbar(
                                it.errorResponse?.message ?: "Terjadi kesalahan pada sistem",
                                Snackbar.LENGTH_LONG
                            )
                        }
                    }
                }
            }
        })
    }

    abstract fun getViewModel(): BaseViewModel
}