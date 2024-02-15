package org.unizd.rma.subasic.spacecraft.domain.interfaces

import org.unizd.rma.subasic.spacecraft.domain.models.SpaceCraftRequestModel
import org.unizd.rma.subasic.spacecraft.domain.models.SpaceCraftResponseModel


interface SpaceCraftRepository {
    suspend fun getSpaceCrafts(): List<SpaceCraftResponseModel>
    suspend fun getSpaceCraft(id: Int): SpaceCraftResponseModel?
    suspend fun deleteSpaceCraft(id: Int)
    suspend fun updateSpaceCraft(id: Int, ime: String, model: String, brzina: Double, slika: String, tezina: Double, datum: String)
    suspend fun createSpaceCraft(data: SpaceCraftRequestModel)


}
