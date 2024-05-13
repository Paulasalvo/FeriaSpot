package com.namkuzo.feriaspot.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.namkuzo.feriaspot.data.Spot
import com.namkuzo.feriaspot.network.SpotService
import com.namkuzo.feriaspot.network.getService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val service: SpotService = getService()

    private val _spotsStateFlow: MutableStateFlow<SpotUiState?> = MutableStateFlow(null)
    val spotsStateFlow: StateFlow<SpotUiState?> = _spotsStateFlow

    init {
        fetchAllSpots()
    }

    private fun fetchAllSpots() {
        _spotsStateFlow.value = SpotUiState.Loading
        viewModelScope.launch {
            try {
                val response = service.getAllSpots()
                _spotsStateFlow.value = SpotUiState.Success(response.map { Spot(it.id, it.name) })
            } catch (e: Exception) {
                _spotsStateFlow.value = SpotUiState.Error(message = e.message.orEmpty() + e.cause?.message.toString())
            }

        }
    }
}

sealed interface SpotUiState {
    data object Loading : SpotUiState
    data class Success(val spots: List<Spot>) : SpotUiState
    data class Error(val message: String): SpotUiState
}
