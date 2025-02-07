package com.example.flickstream.presentation.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flickstream.presentation.home.components.*

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
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
                is HomeState.Success ->
                    ContentGrid(
                        contents = currentState.contents,
                        onItemClick = onNavigateToDetails,
                    )
                is HomeState.Error ->
                    ErrorContent(
                        message = currentState.message,
                        onRetry = viewModel::loadContent,
                    )
            }
        }
    }
}
