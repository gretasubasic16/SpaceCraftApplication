package org.unizd.rma.subasic.spacecraft.data.datasources.room

import org.unizd.rma.subasic.spacecraft.data.datasources.room.entities.toSpaceCraftResponseModel
import org.unizd.rma.subasic.spacecraft.data.datasources.room.entities.toSpaceCraftRoomEntity
import org.unizd.rma.subasic.spacecraft.data.interfaces.SpaceCraftDao
import org.unizd.rma.subasic.spacecraft.data.interfaces.SpaceCraftDataSource
import org.unizd.rma.subasic.spacecraft.domain.models.SpaceCraftRequestModel
import org.unizd.rma.subasic.spacecraft.domain.models.SpaceCraftResponseModel

class RoomSpaceCraftDataSource constructor(private val dao: SpaceCraftDao) : SpaceCraftDataSource {
    override suspend fun getAll(): List<SpaceCraftResponseModel> {
        return dao.getAll().map {
            it.toSpaceCraftResponseModel()
        }
    }

    override suspend fun getOne(id: Int): SpaceCraftResponseModel? {
        val entity = dao.getById(id)
        return entity?.toSpaceCraftResponseModel()
    }

    override suspend fun delete(id: Int) {
        dao.deleteById(id)
    }

    override suspend fun update(
        id: Int,
        ime: String,
        model: String,
        brzina: Double,
        slika: String,
        tezina: Double,
        datum: String
    ) {
        dao.update(id, ime, model, brzina, slika, tezina, datum)
    }

    override suspend fun create(data: SpaceCraftRequestModel) {
        dao.insert(data.toSpaceCraftRoomEntity())
    }
}