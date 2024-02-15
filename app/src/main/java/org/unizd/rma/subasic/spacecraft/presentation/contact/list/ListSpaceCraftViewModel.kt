package org.unizd.rma.subasic.spacecraft.presentation.contact.list


import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.unizd.rma.subasic.spacecraft.domain.interfaces.usecases.GetAllSpaceCraftsUseCase
import org.unizd.rma.subasic.spacecraft.domain.models.SpaceCraftResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.unizd.rma.subasic.spacecraft.domain.interfaces.usecases.DeleteSpaceCraftUseCase
import javax.inject.Inject

data class SpaceCraftListResponseModel(
    val id: String,
    val name: String,
    val model: String,
    val speed: Double,
    val weight: Double,
    val date: String,
    val image: Uri
)

fun SpaceCraftResponseModel.toSpaceCraftListResponseModel(): SpaceCraftListResponseModel {
    return SpaceCraftListResponseModel(
        id = id.toString(),
        name = ime,
        model = model,
        speed = brzina,
        weight = tezina,
        date = datum,
        image = slika
    )
}

@HiltViewModel
class ListSpaceCraftViewModel @Inject constructor(
    private val getAllSpaceCraftsUseCase: GetAllSpaceCraftsUseCase,
    private val deleteSpaceCraftUseCase: DeleteSpaceCraftUseCase
) : ViewModel() {

    private val _errorMessage = mutableStateOf("")
    private val _spaceCrafts = mutableStateListOf<SpaceCraftListResponseModel>()

    val errorMessage: String
        get() = _errorMessage.value

    val spaceCrafts: List<SpaceCraftListResponseModel>
        get() = _spaceCrafts.toList()

    suspend fun getSpaceCrafts() {
        try {
            _spaceCrafts.clear()
            val list = getAllSpaceCraftsUseCase.execute()
            _spaceCrafts.addAll(list.map {
                it.toSpaceCraftListResponseModel()
            })
        } catch (e: Exception) {
            _errorMessage.value = "Error ${e.message}"
        }
    }

    suspend fun deleteSpaceCraftById(id: String) {
        val spaceCraftId = id.toInt()
        deleteSpaceCraftUseCase.execute(spaceCraftId)
        getSpaceCrafts()
    }
}
