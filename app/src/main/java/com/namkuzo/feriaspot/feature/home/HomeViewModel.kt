package com.namkuzo.feriaspot.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.namkuzo.feriaspot.data.FilterCountry
import com.namkuzo.feriaspot.data.Spot
import com.namkuzo.feriaspot.data.Zones
import com.namkuzo.feriaspot.data.repository.SpotRepository
import com.namkuzo.feriaspot.data.source.CountryRegionProvider
import com.namkuzo.feriaspot.di.IoDispatcher
import com.squareup.moshi.JsonEncodingException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.json.JSONException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val spotRepository: SpotRepository,
    private val countryRegionProvider: CountryRegionProvider,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _spotsStateFlow: MutableStateFlow<SpotUiState?> = MutableStateFlow(null)
    val spotsStateFlow: StateFlow<SpotUiState?> = _spotsStateFlow

    private val _filterCountry: MutableStateFlow<FilterCountry> = MutableStateFlow(FilterCountry())
    val filterCountry: StateFlow<FilterCountry> = _filterCountry

    private lateinit var allRegions : Zones

    init {
        loadCountryRegion()
        fetchSpots()
    }

    private fun loadCountryRegion() {
        viewModelScope.launch(ioDispatcher) {
            allRegions = countryRegionProvider.getZones() ?: throw Exception()
            _filterCountry.update { filterCountry ->
                filterCountry.copy(firstItems = allRegions.regions.map { it.name })
            }
        }
    }

    private fun fetchSpots(comuna: String = "") {
        _spotsStateFlow.value = SpotUiState.Loading
        viewModelScope.launch(ioDispatcher) {
            try {
                val response = spotRepository.getAllSpots(comuna)
                _spotsStateFlow.value = SpotUiState.Success(response)
            } catch (e: Exception) {
                if (e is JsonEncodingException) {
                    _spotsStateFlow.value = SpotUiState.Success(emptyList())
                } else {
                    _spotsStateFlow.value =
                        SpotUiState.Error(message = e.message.orEmpty() + e.cause?.message.toString())
                }
            }

        }
    }

    fun setFilterSelected(filterCountry: FilterCountry) {
        val region = filterCountry.firstItemSelected
        val selectCommunes = allRegions.regions.first { it.name == region }
        _filterCountry.update {
            filterCountry.copy(
                secondItems = selectCommunes.communes.map { comuna -> comuna.name },
                secondItemSelected = if (it.firstItemSelected != region) "" else filterCountry.secondItemSelected
            )
        }
    }

    fun applyFilter() {
        val currentValue = _filterCountry.value.secondItemSelected
        fetchSpots(currentValue)
    }

    fun clearFilter() {
        _filterCountry.update {
            it.copy(firstItemSelected = "", secondItemSelected = "")
        }
        applyFilter()
    }

}

sealed interface SpotUiState {
    data object Loading : SpotUiState
    data class Success(val spots: List<Spot>) : SpotUiState
    data class Error(val message: String): SpotUiState
}
