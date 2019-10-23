package `fun`.gladkikh.app.fastpallet6.repository.createpallet

import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.box.BoxCreatePalletQueryDao
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.box.BoxTotalInfoCreatePallet
import `fun`.gladkikh.app.fastpallet6.mapping.createpallet.toObject
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations


class BoxCreatePalletRepository(private val repository: BoxCreatePalletQueryDao) {
    fun getBoxTotalInfoCreatePallet(guidBox: String): LiveData<BoxTotalInfoCreatePallet> =
        Transformations.map(
            repository.getBoxTotalInfoCreatePalletQueryDb(guidBox)
        ) {
            return@map it.toObject()
        }
}