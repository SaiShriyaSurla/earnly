package com.shriya.earnly.aggregator

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun App() {
    val viewModel: FeedViewModel = viewModel()
    val gigs by viewModel.gigs.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadGigs()
    }

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Earnly", style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(16.dp))

            when {
                isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }
                error != null -> {
                    Text("Error: $error", color = MaterialTheme.colorScheme.error)
                }
                gigs.isEmpty() -> {
                    Text("No gigs found")
                }
                else -> {
                    LazyColumn {
                        items(gigs) { gig ->
                            GigCard(gig)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GigCard(gig: GigListing) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(gig.title, style = MaterialTheme.typography.titleMedium)
            Text(gig.source_platform, style = MaterialTheme.typography.labelSmall)
            Text("${gig.raw_payout}", style = MaterialTheme.typography.bodyMedium)
            Text(gig.ai_summary, style = MaterialTheme.typography.bodySmall)
        }
    }
}