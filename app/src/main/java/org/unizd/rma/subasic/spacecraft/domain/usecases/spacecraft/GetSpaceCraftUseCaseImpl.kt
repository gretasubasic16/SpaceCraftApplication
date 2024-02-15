package org.unizd.rma.subasic.spacecraft.domain.usecases.spacecraft

import org.unizd.rma.subasic.spacecraft.domain.interfaces.usecases.GetSpaceCraftUseCase


import org.unizd.rma.subasic.spacecraft.domain.interfaces.SpaceCraftRepository
import org.unizd.rma.subasic.spacecraft.domain.models.SpaceCraftResponseModel

class GetSpaceCraftUseCaseImpl constructor(private val spaceCraftRepository: SpaceCraftRepository): GetSpaceCraftUseCase {
    override suspend fun execute(id: Int): SpaceCraftResponseModel? {
        return spaceCraftRepository.getSpaceCraft(id)
    }
}
