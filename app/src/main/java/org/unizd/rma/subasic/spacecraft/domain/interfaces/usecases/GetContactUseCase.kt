package org.unizd.rma.subasic.spacecraft.domain.interfaces.usecases

import org.unizd.rma.subasic.spacecraft.domain.models.SpaceCraftResponseModel


interface GetSpaceCraftUseCase {
    suspend fun execute(id: Int): SpaceCraftResponseModel?
}
