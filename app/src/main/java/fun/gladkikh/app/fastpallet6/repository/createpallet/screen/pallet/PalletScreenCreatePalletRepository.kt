package `fun`.gladkikh.app.fastpallet6.repository.createpallet.screen.pallet

import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.pallet.PalletScreenCreatePalletDao
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.pallet.PalletScreenCreatePalletData
import `fun`.gladkikh.app.fastpallet6.mapping.createpallet.screen.pallet.toObject
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.CreatePalletRepositoryUpdate

class PalletScreenCreatePalletRepository (
    private val palletScreenCreatePalletDao: PalletScreenCreatePalletDao,
    private val repositoryUpdate: CreatePalletRepositoryUpdate
){

    fun getData(guidBox:String): PalletScreenCreatePalletData {
      return  palletScreenCreatePalletDao.getData(guidBox).toObject()
    }

    fun getTotalData(guidBox:String): PalletScreenCreatePalletData {
        return  palletScreenCreatePalletDao.getTotalData(guidBox).toObject()
    }
}
