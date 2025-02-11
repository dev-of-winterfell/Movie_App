package com.example.flickstream.presentation.home

import androidx.lifecycle.ViewModel
import com.example.flickstream.domain.usecase.GetMoviesAndTvShowsUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(
    private val getMoviesAndTvShowsUseCase: GetMoviesAndTvShowsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<HomeState>(HomeState.Loading)
    val state: StateFlow<HomeState> = _state.asStateFlow()

    private val _contentType = MutableStateFlow(ContentType.MOVIES)
    val contentType: StateFlow<ContentType> = _contentType.asStateFlow()

    private val disposables = CompositeDisposable()

    init {
        loadContent()
    }

    fun setContentType(type: ContentType) {
        _contentType.value = type
    }

    fun loadContent() {
        _state.value = HomeState.Loading

        getMoviesAndTvShowsUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ (movies, tvShows) ->
                _state.value = HomeState.Success(
                    movies = movies,
                    tvShows = tvShows
                )
            }, { error ->
                _state.value = HomeState.Error(error.message ?: "Unknown error occurred")
            })
            .let { disposables.add(it) }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}