package `fun`.gladkikh.app.fastpallet6.repository.createpallet

import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.doc.DocCreatePalletQueryDao
import `fun`.gladkikh.app.fastpallet6.domain.entity.CreatePallet
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.doc.ProductItemCreatePallet
import `fun`.gladkikh.app.fastpallet6.mapping.createpallet.toObject
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

class DocCreatePalletRepository(private val docCreatePalletQueryDao: DocCreatePalletQueryDao) {
    fun getListProductCreatePalletLiveData(guidDoc: String): LiveData<List<ProductItemCreatePallet>> =
        Transformations.map(
            docCreatePalletQueryDao.getListProductCreatePalletLiveData(guidDoc)
        ) { list ->
            return@map list.map {
                it.toObject()
            }
        }

    fun getDocCreatePalletByGuidLiveData(guidDoc: String): LiveData<CreatePallet> =
        Transformations.map(
            docCreatePalletQueryDao.getDocCreatePalletByGuidLiveData(guidDoc)
        ) { doc ->
            return@map doc.toObject()
        }
}
