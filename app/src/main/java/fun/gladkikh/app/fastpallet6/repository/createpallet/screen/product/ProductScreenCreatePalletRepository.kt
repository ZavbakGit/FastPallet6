package `fun`.gladkikh.app.fastpallet6.repository.createpallet.screen.product

import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.product.ProductScreenCreatePalletDao
import `fun`.gladkikh.app.fastpallet6.domain.entity.Pallet
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.product.ItemProductScreenCreatePalletData
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.product.ProductScreenCreatePalletData
import `fun`.gladkikh.app.fastpallet6.mapping.createpallet.screen.product.toObject
import `fun`.gladkikh.app.fastpallet6.mapping.createpallet.toObject
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.CreatePalletRepositoryUpdate

class ProductScreenCreatePalletRepository(
    private val productScreenCreatePalletDao: ProductScreenCreatePalletDao,
    private val repositoryUpdate: CreatePalletRepositoryUpdate
) {

    fun getData(guidPallet: String): ProductScreenCreatePalletData {
        return productScreenCreatePalletDao.getData(guidPallet).toObject()
    }

    fun getTotalData(guidPallet: String): ProductScreenCreatePalletData {
        return productScreenCreatePalletDao.getTotalData(guidPallet).toObject()
    }

    fun getListItem(guidPallet: String): List<ItemProductScreenCreatePalletData> {
        return productScreenCreatePalletDao.getListItem(guidPallet).map {
            it.toObject()
        }
    }

    fun getListItemTotal(guidPallet: String): List<ItemProductScreenCreatePalletData> {
        return productScreenCreatePalletDao.getListItemTotal(guidPallet).map {
            it.toObject()
        }
    }

    //fun getPallet1(guid: String) = productScreenCreatePalletDao.getPallet(guid).toObject()
    fun getPallet(guid: String) = repositoryUpdate.getObjectCreatePalletByGuid<Pallet>(guid) as Pallet

    fun getPalletByNumber(number: String):Pallet?{
        val pallet = productScreenCreatePalletDao.getPalletByNumber(number)
        return pallet?.toObject()
    }

    fun deletePallet(pallet: Pallet) = repositoryUpdate.delete(pallet)

    fun savePallet(pallet: Pallet) {
        repositoryUpdate.save(pallet)
    }
}
