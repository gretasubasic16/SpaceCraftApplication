package org.unizd.rma.subasic.spacecraft.presentation.contact.edit


import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.unizd.rma.subasic.spacecraft.domain.interfaces.SpaceCraftRepository
import org.unizd.rma.subasic.spacecraft.domain.interfaces.usecases.UpdateSpaceCraftUseCase
import org.unizd.rma.subasic.spacecraft.domain.models.SpaceCraftResponseModel
import javax.inject.Inject

@HiltViewModel
class UpdateSpaceCraftViewModel @Inject constructor(
    private val updateSpaceCraftUseCase: UpdateSpaceCraftUseCase,
    private val spaceCraftRepository: SpaceCraftRepository

) : ViewModel() {

    private val _errorMessage = mutableStateOf("")
    private val _ime = mutableStateOf("")
    private val _model = mutableStateOf("")
    private val _brzina = mutableStateOf(0.0)
    private val _slika = mutableStateOf("")
    private val _tezina = mutableStateOf(0.0)
    private val _datum = mutableStateOf("")


    val name: String
        get() = _ime.value

    val model: String
        get() = _model.value

    val speed: Double
        get() = _brzina.value

    val weight: Double
        get() = _tezina.value

    val datum: String
        get() = _datum.value

    val slika: String
        get() = _slika.value

    val errorMessage: String
        get() = _errorMessage.value

    fun onNameChange(newName: String) {
        _ime.value = newName
    }

    fun onModelChange(newModel: String) {
        _model.value = newModel
    }

    fun onImageChange(newImage: String) {
        _slika.value = newImage
    }

    fun onSpeedChange(newSpeed: Double) {
        _brzina.value = newSpeed
    }

    fun onWeightChange(newWeight: Double) {
        _tezina.value = newWeight
    }

    fun onDateChange(newDate: String) {
        _datum.value = newDate
    }

    suspend fun updateSpaceCraft(id: String) {
        val spaceCraftId = id.toInt()
        try {
            updateSpaceCraftUseCase.execute(
                spaceCraftId,
                _ime.value,
                _model.value,
                _brzina.value,
                _slika.value,
                _tezina.value,
                _datum.value
            )
        } catch (e: Exception) {
            _errorMessage.value = "Error ${e.message}"
        }
    }


    suspend fun getSpaceCraftData(id: Int): SpaceCraftResponseModel? {
        return spaceCraftRepository.getSpaceCraft(id)
    }


}
