package org.unizd.rma.subasic.spacecraft.domain.usecases.spacecraft


import org.unizd.rma.subasic.spacecraft.domain.interfaces.SpaceCraftRepository
import org.unizd.rma.subasic.spacecraft.domain.interfaces.usecases.GetAllSpaceCraftsUseCase
import org.unizd.rma.subasic.spacecraft.domain.models.SpaceCraftResponseModel

class GetAllSpaceCraftsUseCaseImpl constructor(private val spaceCraftRepository: SpaceCraftRepository) : GetAllSpaceCraftsUseCase {
    override suspend fun execute(): List<SpaceCraftResponseModel> {
        return spaceCraftRepository.getSpaceCrafts();
    }
}
