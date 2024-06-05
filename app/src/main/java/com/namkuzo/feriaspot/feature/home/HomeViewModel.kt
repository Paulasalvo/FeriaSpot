package com.namkuzo.feriaspot.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.namkuzo.feriaspot.data.Spot
import com.namkuzo.feriaspot.data.repository.SpotRepository
import com.namkuzo.feriaspot.data.source.Source
import com.namkuzo.feriaspot.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val spotRepository: SpotRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _spotsStateFlow: MutableStateFlow<SpotUiState?> = MutableStateFlow(null)
    val spotsStateFlow: StateFlow<SpotUiState?> = _spotsStateFlow

    init {
        fetchSpots()
    }

    fun fetchSpots(indexComuna: Int = -1) {
        _spotsStateFlow.value = SpotUiState.Loading
        viewModelScope.launch(ioDispatcher) {
            try {
                val response = spotRepository.getAllSpots(indexComuna)
                _spotsStateFlow.value = SpotUiState.Success(response)
            } catch (e: Exception) {
                _spotsStateFlow.value =
                    SpotUiState.Error(message = e.message.orEmpty() + e.cause?.message.toString())
            }

        }
    }

    fun getComunas() : List<String> = Source.getAllComunas()

}

sealed interface SpotUiState {
    data object Loading : SpotUiState
    data class Success(val spots: List<Spot>) : SpotUiState
    data class Error(val message: String): SpotUiState
}
