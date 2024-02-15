package org.unizd.rma.subasic.spacecraft.domain.usecases.spacecraft


import org.unizd.rma.subasic.spacecraft.domain.interfaces.SpaceCraftRepository
import org.unizd.rma.subasic.spacecraft.domain.interfaces.usecases.UpdateSpaceCraftUseCase

class UpdateSpaceCraftUseCaseImpl constructor(private val spaceCraftRepository: SpaceCraftRepository):
    UpdateSpaceCraftUseCase {
    override suspend fun execute(
        id: Int,
        ime: String,
        model: String,
        brzina: Double,
        slika: String,
        tezina: Double,
        datum: String
    ) {
        return spaceCraftRepository.updateSpaceCraft(id, ime, model, brzina, slika, tezina, datum);
    }

}
