package `fun`.gladkikh.app.fastpallet6.repository.createpallet

import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.pallet.PalletCreatePalletQueryDao
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.pallet.BoxItemCreatePallet
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.pallet.TotalInfoPalletCreatePallet
import `fun`.gladkikh.app.fastpallet6.mapping.createpallet.toObject
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations


class PalletCreatePalletRepository(private val repository: PalletCreatePalletQueryDao) {
    fun getListBoxItemCreatePalletLiveData(guidPallet: String): LiveData<List<BoxItemCreatePallet>> =
        Transformations.map(
            repository.getListBoxCreatePalletLiveData(guidPallet)
        ) { list ->
            return@map list.map {
                it.toObject()
            }
        }

    fun getTotalInfoPalletCreatePallet(guidPallet: String): LiveData<TotalInfoPalletCreatePallet> =
        Transformations.map(
            repository.getTotalInfoPalletCreatePalletQueryDb(guidPallet)
        ) {
            return@map it.toObject()
        }
}