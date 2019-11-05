package `fun`.gladkikh.app.fastpallet6.repository.createpallet.screen.doc

import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.doc.DocScreenCreatePalletDao
import `fun`.gladkikh.app.fastpallet6.domain.entity.CreatePallet
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.doc.ItemDocScreenCreatePalletData
import `fun`.gladkikh.app.fastpallet6.mapping.createpallet.screen.doc.toObject
import `fun`.gladkikh.app.fastpallet6.mapping.createpallet.toObject
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.CreatePalletRepositoryUpdate

class DocScreenCreatePalletRepository(
    private val DocScreenCreatePalletDao: DocScreenCreatePalletDao,
    private val repositoryUpdate: CreatePalletRepositoryUpdate
) {

    fun getData(guidDoc: String): CreatePallet {
        return DocScreenCreatePalletDao.getData(guidDoc).toObject()
    }

    fun getListItem(guidDoc: String): List<ItemDocScreenCreatePalletData> {
        return DocScreenCreatePalletDao.getListItem(guidDoc).map {
            it.toObject()
        }
    }

    fun getListItemTotal(guidDoc: String): List<ItemDocScreenCreatePalletData> {
        return DocScreenCreatePalletDao.getListItemTotal(guidDoc).map {
            it.toObject()
        }
    }
}
