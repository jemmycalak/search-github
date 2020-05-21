package com.jemmycalak.listrepository.di

import com.jemmycalak.listrepository.domain.Services
import com.jemmycalak.listrepository.viewmodel.ListRespositoryViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val listRepositoryModule = module{
    factory { Services(get()) }

    viewModel { ListRespositoryViewModel(get(), get()) }
}