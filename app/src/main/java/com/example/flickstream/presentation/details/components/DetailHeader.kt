package com.example.flickstream.presentation.details.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.flickstream.data.model.WatchContent

@Composable
fun DetailHeader(content: WatchContent) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image (Header)
        AsyncImage(
            model = content.posterUrl,
            contentDescription = content.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(MaterialTheme.shapes.medium) // Rounded corners for the image
                .padding(bottom = 16.dp), // Space below the image
            contentScale = ContentScale.Crop
        )

        // Title Section
        Text(
            text = content.title,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 8.dp) // Space below the title
        )

        // Tagline or Year if available (Optional)
        Text(
            text = content.releaseDate ?: "Release Year Not Available",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}