package com.example.flickstream.di

import com.example.flickstream.data.mapper.WatchMapper
import com.example.flickstream.data.repository.WatchRepository
import com.example.flickstream.data.repository.WatchRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single { WatchMapper() }
    single<WatchRepository> { WatchRepositoryImpl(get(), get()) }
}