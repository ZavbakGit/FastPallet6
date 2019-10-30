package `fun`.gladkikh.app.fastpallet6.repository.createpallet

import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.box.old.BoxCreatePalletQueryDao
import `fun`.gladkikh.app.fastpallet6.domain.entity.Box
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.box.old.BoxScreenCreatePallet
import `fun`.gladkikh.app.fastpallet6.mapping.createpallet.toObject


class BoxCreatePalletRepository(private val repository: BoxCreatePalletQueryDao,
                                private val repositoryUpdate: CreatePalletRepositoryUpdate) {

    fun getBoxScreenCreatePalletTotal(guidBox: String): BoxScreenCreatePallet {
        return repository.getBoxScreenCreatePalletTotalDb(guidBox).toObject()
    }

    fun getBoxScreenCreatePallet(guidBox: String): BoxScreenCreatePallet {
        return repository.getBoxScreenCreatePalletDb(guidBox).toObject()
    }


    fun saveListBox(list: List<Box>) = repositoryUpdate.saveListBox(list)

}