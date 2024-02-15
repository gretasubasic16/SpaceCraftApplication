package org.unizd.rma.subasic.spacecraft.presentation.contact.details

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.unizd.rma.subasic.spacecraft.domain.interfaces.usecases.GetSpaceCraftUseCase
import org.unizd.rma.subasic.spacecraft.domain.models.SpaceCraftResponseModel
import javax.inject.Inject

@HiltViewModel
class DetailsSpaceCraftViewModel @Inject constructor(
    private val getSpaceCraftUseCase: GetSpaceCraftUseCase
) : ViewModel() {

    var spaceCraft: SpaceCraftResponseModel? = SpaceCraftResponseModel(
        id = 1,
        ime = "",
        model = "",
        brzina = 0.0,
        slika = Uri.parse(""),
        tezina = 0.0,
        datum = ""
    )

    fun loadSpaceCraftDetails(id: String) {
        viewModelScope.launch {
            try {
                spaceCraft = getSpaceCraftUseCase.execute(id.toInt())
            } catch (e: Exception) {
                spaceCraft = null
            }
        }
    }
}
