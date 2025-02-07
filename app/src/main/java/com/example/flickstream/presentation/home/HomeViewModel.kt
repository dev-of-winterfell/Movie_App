package com.example.flickstream.presentation.home

import androidx.lifecycle.ViewModel
import com.example.flickstream.domain.usecase.GetMoviesUseCase
import com.example.flickstream.domain.usecase.GetTvShowsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getTvShowsUseCase: GetTvShowsUseCase
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
        loadContent()
    }

    fun loadContent() {
        _state.value = HomeState.Loading

        val useCase = when (_contentType.value) {
            ContentType.MOVIES -> getMoviesUseCase()
            ContentType.TV_SHOWS -> getTvShowsUseCase()
        }

        useCase
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ contents ->
                _state.value = HomeState.Success(contents)
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
