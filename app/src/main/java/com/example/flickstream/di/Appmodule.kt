package com.example.flickstream.di

import com.example.flickstream.WatchApplication
import com.example.flickstream.domain.usecase.GetContentDetailsUseCase
import com.example.flickstream.domain.usecase.GetMoviesAndTvShowsUseCase

import com.example.flickstream.presentation.details.DetailsViewModel
import com.example.flickstream.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Application
    single { WatchApplication() }

    // UseCases
    factory { GetMoviesAndTvShowsUseCase(get()) }
    factory { GetContentDetailsUseCase(get()) }

    // ViewModels
    viewModel { HomeViewModel(get()) }  // Only passing GetMoviesAndTvShowsUseCase
    viewModel { DetailsViewModel(get()) }
}