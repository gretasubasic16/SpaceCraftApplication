package org.unizd.rma.subasic.spacecraft.domain.repositories

import org.unizd.rma.subasic.spacecraft.domain.models.SpaceCraftRequestModel

import org.unizd.rma.subasic.spacecraft.data.interfaces.SpaceCraftDataSource
import org.unizd.rma.subasic.spacecraft.domain.interfaces.SpaceCraftRepository
import org.unizd.rma.subasic.spacecraft.domain.models.SpaceCraftResponseModel

class SpaceCraftRepositoryImpl constructor(private val spaceCraftDataSource: SpaceCraftDataSource):
    SpaceCraftRepository {
    override suspend fun getSpaceCrafts(): List<SpaceCraftResponseModel> {
        return spaceCraftDataSource.getAll()
    }

    override suspend fun getSpaceCraft(id: Int): SpaceCraftResponseModel? {
        return spaceCraftDataSource.getOne(id)
    }

    override suspend fun deleteSpaceCraft(id: Int) {
        return spaceCraftDataSource.delete(id)
    }

    override suspend fun updateSpaceCraft(
        id: Int,
        ime: String,
        model: String,
        brzina: Double,
        slika: String,
        tezina: Double,
        datum: String
    ) {
        return spaceCraftDataSource.update(id, ime, model, brzina, slika, tezina, datum)
    }

    override suspend fun createSpaceCraft(data: SpaceCraftRequestModel) {
        return spaceCraftDataSource.create(data)
    }
}
