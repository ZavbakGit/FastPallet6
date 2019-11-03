package `fun`.gladkikh.app.fastpallet6.repository.createpallet

import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.product.old.ProductCreatePalletQueryDao
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.product.PalletItemCreatePallet
import `fun`.gladkikh.app.fastpallet6.mapping.createpallet.toObject
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

class ProductCreatePalletRepository(private val repository: ProductCreatePalletQueryDao) {
    fun getListPalletItemCreatePalletLiveData(guidProduct: String): LiveData<List<PalletItemCreatePallet>> =
        Transformations.map(
            repository.getListPalletItemCreatePallet(guidProduct)
        ) { list ->
            return@map list.map {
                it.toObject()
            }
        }
}