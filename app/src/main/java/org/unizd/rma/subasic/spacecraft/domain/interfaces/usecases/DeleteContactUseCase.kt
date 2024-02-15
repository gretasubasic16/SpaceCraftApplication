package org.unizd.rma.subasic.spacecraft.domain.interfaces.usecases


interface DeleteSpaceCraftUseCase {
    suspend fun execute(id: Int)
}
