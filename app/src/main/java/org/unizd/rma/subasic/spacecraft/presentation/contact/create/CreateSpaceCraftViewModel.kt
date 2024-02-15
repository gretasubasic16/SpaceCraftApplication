package org.unizd.rma.peric.bookcase.presentation.contact.create

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

import dagger.hilt.android.lifecycle.HiltViewModel
import org.unizd.rma.subasic.spacecraft.domain.interfaces.usecases.CreateSpaceCraftUseCase
import org.unizd.rma.subasic.spacecraft.domain.models.SpaceCraftRequestModel
import javax.inject.Inject

@HiltViewModel
class CreateSpaceCraftViewModel @Inject constructor(
    private val createSpaceCraftUseCase: CreateSpaceCraftUseCase
) : ViewModel() {

    private val _errorMessage = mutableStateOf("")
    private val _ime = mutableStateOf("")
    private val _model = mutableStateOf("")
    private val _brzina = mutableStateOf(0.0)
    private val _slika = mutableStateOf("")
    private val _tezina = mutableStateOf(0.0)
    private val _datum = mutableStateOf("")


    val ime : String
        get() = _ime.value

    val model : String
        get() = _model.value

    val brzina : Double
        get() = _brzina.value

    val slika : String
        get() = _slika.value

    val tezina : Double
        get() = _tezina.value

    val datum : String
        get() = _datum.value

    val errorMessage : String
        get() = _errorMessage.value

    fun promijeniIme(novoIme: String) {
        _ime.value = novoIme
    }

    fun promijeniModel(noviModel: String){
        _model.value = noviModel
    }

    fun promijeniBrzinu(novaBrzina: Double) {
        _brzina.value = novaBrzina
    }

    fun promijeniSliku(novaSlika: String) {
        _slika.value = novaSlika
    }

    fun promijeniTezinu(novaTezina: Double) {
        _tezina.value = novaTezina
    }

    fun promijeniDatum(noviDatum: String) {
        _datum.value = noviDatum
    }


    fun resetirajPolja() {
        _ime.value = ""
        _model.value = ""
        _brzina.value = 0.0
        _slika.value = ""
        _tezina.value = 0.0
        _datum.value = ""
    }


    suspend fun kreirajSpaceCraft() {
        try {
            createSpaceCraftUseCase.execute(SpaceCraftRequestModel(null, _ime.value, _model.value, _brzina.value, _slika.value, _tezina.value, _datum.value))
            resetirajPolja()
        } catch (e: Exception) {
            _errorMessage.value = "Greška ${e.message}"
        }
    }




}
