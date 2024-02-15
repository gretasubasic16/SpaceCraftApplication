package org.unizd.rma.subasic.spacecraft.data.interfaces

import org.unizd.rma.subasic.spacecraft.data.datasources.room.entities.SpaceCraftRoomEntity


import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase

@Database(entities = [SpaceCraftRoomEntity::class], version = 1)
abstract class SpaceCraftDatabase: RoomDatabase() {
    abstract val spaceCraftDao: SpaceCraftDao

    companion object {
        const val DATABASE_NAME = "spacecraft_db"
    }
}

@Dao
interface SpaceCraftDao {
    @Query("SELECT * FROM spacecraft")
    suspend fun getAll(): List<SpaceCraftRoomEntity>

    @Query("SELECT * FROM spacecraft WHERE id = :id")
    suspend fun getById(id: Int): SpaceCraftRoomEntity?

    @Query("DELETE FROM spacecraft WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("UPDATE spacecraft SET ime = :ime, model = :model, brzina = :brzina, slika = :slika, tezina = :tezina, datum = :datum WHERE id = :id")
    suspend fun update(id: Int, ime: String, model: String, brzina: Double, slika: String, tezina: Double, datum: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(spaceCraft: SpaceCraftRoomEntity)
}
