package com.example.flickstream.di

import android.content.Context
import com.example.flickstream.WatchApplication
import com.example.flickstream.data.mapper.WatchMapper
import com.example.flickstream.data.repository.WatchRepository
import com.example.flickstream.domain.usecase.GetMoviesUseCase
import com.example.flickstream.domain.usecase.GetTvShowsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApplication(@ApplicationContext context: Context): WatchApplication {
        return context as WatchApplication
    }

    @Provides
    @Singleton
    fun provideGetMoviesUseCase(repository: WatchRepository): GetMoviesUseCase {
        return GetMoviesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetTvShowsUseCase(
        repository: WatchRepository,
        mapper: WatchMapper
    ): GetTvShowsUseCase {
        return GetTvShowsUseCase(repository, mapper)
    }
}
