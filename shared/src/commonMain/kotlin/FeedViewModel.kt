package com.shriya.earnly.aggregator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {
    private val _gigs = MutableStateFlow<List<GigListing>>(emptyList())
    val gigs: StateFlow<List<GigListing>> = _gigs

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadGigs() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val fetchedGigs = SupabaseClient.fetchGigs()
                _gigs.value = fetchedGigs
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }
}