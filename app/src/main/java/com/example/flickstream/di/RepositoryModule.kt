package com.example.flickstream.di

import com.example.flickstream.data.api.WatchmodeApi
import com.example.flickstream.data.mapper.WatchMapper
import com.example.flickstream.data.repository.WatchRepository
import com.example.flickstream.data.repository.WatchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWatchRepository(
        api: WatchmodeApi,
        mapper: WatchMapper,

    ): WatchRepository {
        return WatchRepositoryImpl(api, mapper)
    }

    @Provides
    @Singleton
    fun provideWatchMapper(): WatchMapper {
        return WatchMapper()
    }
}