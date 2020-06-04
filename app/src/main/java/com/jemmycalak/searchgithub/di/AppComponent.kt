package com.jemmycalak.searchgithub.di

import com.jemmycalak.auth.di.authModul
import com.jemmycalak.listrepository.di.listRepositoryModule
import com.jemmycalak.remote.di.remoteModule
import com.jemmycalak.repository.di.repositoryModule
import com.jemmycalak.searchgithub.BuildConfig
import org.koin.core.module.Module

val appComponent : List<Module> = listOf(
    remoteModule(BuildConfig.BASE_URL),
    repositoryModule,
    listRepositoryModule,
    authModul
)