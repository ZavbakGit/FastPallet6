package `fun`.gladkikh.app.fastpallet6.repository.createpallet.screen.box

import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.box.BoxScreenCreatePalletDao
import `fun`.gladkikh.app.fastpallet6.domain.entity.Box
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.box.BoxScreenCreatePalletData
import `fun`.gladkikh.app.fastpallet6.mapping.createpallet.screen.box.toObject
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.CreatePalletRepositoryUpdate

class BoxScreenCreatePalletRepository (
    private val boxScreenCreatePalletDao: BoxScreenCreatePalletDao,
    private val repositoryUpdate: CreatePalletRepositoryUpdate
){

    fun getData(guidBox:String): BoxScreenCreatePalletData{
      return  boxScreenCreatePalletDao.getData(guidBox).toObject()
    }

    fun getTotalData(guidBox:String):BoxScreenCreatePalletData{
        return  boxScreenCreatePalletDao.getTotalData(guidBox).toObject()
    }

    fun saveListBox(list: List<Box>) = repositoryUpdate.saveListBox(list)


}
