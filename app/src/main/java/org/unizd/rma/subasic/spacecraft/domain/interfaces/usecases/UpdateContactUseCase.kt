package org.unizd.rma.subasic.spacecraft.domain.interfaces.usecases


interface UpdateSpaceCraftUseCase {
    suspend fun execute(id: Int, ime:String, model:String, brzina:Double, slika:String, tezina:Double, datum:String)
}
