package `fun`.gladkikh.app.fastpallet6.repository.createpallet.screen.box

import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.box.BoxScreenCreatePalletDao
import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.BoxCreatePalletDb
import `fun`.gladkikh.app.fastpallet6.domain.entity.Box
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.box.BoxScreenCreatePalletData
import `fun`.gladkikh.app.fastpallet6.mapping.createpallet.screen.box.toObject
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.CreatePalletRepositoryUpdate

class BoxScreenCreatePalletRepository(
    private val boxScreenCreatePalletDao: BoxScreenCreatePalletDao,
    private val repositoryUpdate: CreatePalletRepositoryUpdate
) {

    fun getData(guidBox: String): BoxScreenCreatePalletData {
        return boxScreenCreatePalletDao.getData(guidBox).toObject()
    }

    fun getTotalData(guidBox: String): BoxScreenCreatePalletData {
        return boxScreenCreatePalletDao.getTotalData(guidBox).toObject()
    }

    fun saveListBox(list: List<Box>) = repositoryUpdate.saveListBox(list)

    fun getBox(guid: String) =
        (repositoryUpdate.getObjectCreatePalletByGuid<Box>(guid)
                as Box)

    fun deleteBox(box: Box) = repositoryUpdate.delete(box)

    fun saveBox(box: Box) = repositoryUpdate.save(box)
}
