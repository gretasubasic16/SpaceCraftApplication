package org.unizd.rma.subasic.spacecraft.domain.usecases.spacecraft

import org.unizd.rma.subasic.spacecraft.domain.interfaces.SpaceCraftRepository
import org.unizd.rma.subasic.spacecraft.domain.interfaces.usecases.CreateSpaceCraftUseCase // Dodajte uvoz sučelja
import org.unizd.rma.subasic.spacecraft.domain.models.SpaceCraftRequestModel

class CreateSpaceCraftUseCaseImpl constructor(private val spaceCraftRepository: SpaceCraftRepository) :
    CreateSpaceCraftUseCase { // Provjerite je li sučelje ispravno uvezeno
    override suspend fun execute(spaceCraft: SpaceCraftRequestModel) {
        return spaceCraftRepository.createSpaceCraft(spaceCraft)
    }
}
