package org.unizd.rma.subasic.spacecraft.domain.interfaces.usecases

import org.unizd.rma.subasic.spacecraft.domain.models.SpaceCraftResponseModel

interface GetAllSpaceCraftsUseCase {
    suspend fun execute(): List<SpaceCraftResponseModel>
}