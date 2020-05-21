package com.jemmycalak.repository.di

import com.jemmycalak.repository.SearchRepository
import com.jemmycalak.repository.SearchRepositoryListener
import com.jemmycalak.repository.utils.AppDispatchers
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repositoryModule = module{

    factory { AppDispatchers(Dispatchers.Main, Dispatchers.IO) }
    factory { SearchRepository(get(), get()) as SearchRepositoryListener }
}