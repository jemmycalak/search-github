package com.jemmycalak.auth.di

import com.jemmycalak.auth.viewmodel.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModul = module {
    viewModel{ LoginViewModel(get(), get()) }
}