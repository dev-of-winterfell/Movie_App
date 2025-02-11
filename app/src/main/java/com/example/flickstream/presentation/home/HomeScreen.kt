package com.example.flickstream.presentation.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flickstream.presentation.home.components.*
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onNavigateToDetails: (String) -> Unit,
) {
    val state by viewModel.state.collectAsState()
    val contentType by viewModel.contentType.collectAsState()

    Column {
        HomeTopBar(
            selectedType = contentType,
            onTypeSelected = viewModel::setContentType,
        )

        AnimatedContent(targetState = state) { currentState ->
            when (currentState) {
                is HomeState.Loading -> ShimmerContent()
                is HomeState.Success -> {
                    // Display movies or TV shows based on contentType
                    val contents = when (contentType) {
                        ContentType.MOVIES -> currentState.movies
                        ContentType.TV_SHOWS -> currentState.tvShows
                    }
                    ContentGrid(
                        contents = contents,
                        onItemClick = onNavigateToDetails,
                    )
                }
                is HomeState.Error -> {
                    ErrorContent(
                        message = currentState.message,
                        onRetry = viewModel::loadContent,
                    )
                }
            }
        }
    }
}