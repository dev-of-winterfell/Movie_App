package com.example.flickstream.presentation.details

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flickstream.presentation.details.components.DetailContent
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun DetailsScreen(
    contentId: String,
    contentType: String,
    onNavigateBack: () -> Unit,
    viewModel: DetailsViewModel =  koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val isMovie = contentType == "movie"

    // Handle error messages
    LaunchedEffect(state) {
        (state as? DetailsState.Error)?.let {
            snackbarHostState.showSnackbar(
                message = it.message,
                duration = SnackbarDuration.Long,
                withDismissAction = true
            )
        }
    }

    // Load content when contentId changes
    LaunchedEffect(contentId) { viewModel.loadContent(contentId, isMovie) }

    Scaffold(
        topBar = { DetailsTopBar(state, onNavigateBack) },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 16.dp), // Adds some margin
            contentAlignment = Alignment.TopCenter // Ensures content isn't stuck at center
        ) {
            AnimatedContent(
                targetState = state,
                transitionSpec = { fadeIn() with fadeOut() }
            ) { currentState ->
                when (currentState) {
                    is DetailsState.Loading -> LoadingIndicator()
                    is DetailsState.Success -> DetailContent(
                        content = currentState.content,
                        modifier = Modifier.fillMaxSize()
                    )
                    is DetailsState.Error -> ErrorScreen(
                        message = currentState.message,
                        onRetry = { scope.launch { viewModel.loadContent(contentId, isMovie) } }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(state: DetailsState, onNavigateBack: () -> Unit) {
    LargeTopAppBar(
        title = {
            val title = (state as? DetailsState.Success)?.content?.title ?: "Details"
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        navigationIcon = {
            IconButton(onClick = onNavigateBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier.statusBarsPadding()
    )
}

@Composable
fun LoadingIndicator() {
    val alpha by rememberInfiniteTransition().animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        )
    )

    CircularProgressIndicator(
        modifier = Modifier.size(48.dp).alpha(alpha),
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
fun ErrorScreen(message: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = message, color = MaterialTheme.colorScheme.error)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onRetry) { Text("Retry") }
    }
}
