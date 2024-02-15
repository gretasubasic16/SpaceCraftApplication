package org.unizd.rma.subasic.spacecraft.domain.interfaces.usecases

import org.unizd.rma.subasic.spacecraft.domain.models.SpaceCraftRequestModel


interface CreateSpaceCraftUseCase {
    suspend fun execute(spaceCraft: SpaceCraftRequestModel)
}
