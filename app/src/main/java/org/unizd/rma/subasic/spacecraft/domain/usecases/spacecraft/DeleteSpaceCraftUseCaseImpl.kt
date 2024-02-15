package org.unizd.rma.subasic.spacecraft.domain.usecases.spacecraft


import org.unizd.rma.subasic.spacecraft.domain.interfaces.SpaceCraftRepository
import org.unizd.rma.subasic.spacecraft.domain.interfaces.usecases.DeleteSpaceCraftUseCase

class DeleteSpaceCraftUseCaseImpl constructor(private val bookRepository: SpaceCraftRepository) :
    DeleteSpaceCraftUseCase {
    override suspend fun execute(id: Int) {
        return bookRepository.deleteSpaceCraft(id)
    }
}
