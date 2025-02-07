package com.example.flickstream.presentation.details.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.flickstream.data.model.WatchContent
import coil.compose.AsyncImage

@Composable
fun DetailContent(
    content: WatchContent,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        DetailHeader(content = content)

        Spacer(modifier = Modifier.height(16.dp)) // Add some space after the header

        // Overview Section
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
        ) {
            Text(
                text = "Overview",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = content.description,
                style = MaterialTheme.typography.bodyLarge,
                lineHeight = MaterialTheme.typography.bodyLarge.lineHeight * 1.5
            )
        }

        Spacer(modifier = Modifier.height(16.dp)) // Space before next section

        // Other Sections (for future expansion)
        // Example: Rating Section, Genre, Cast, etc.

        // If you want to add more sections, you can replicate this structure:
        // - Text("Rating", style = MaterialTheme.typography.titleMedium)
        // - Text("Cast: John Doe, Jane Smith", style = MaterialTheme.typography.bodyMedium)
    }
}