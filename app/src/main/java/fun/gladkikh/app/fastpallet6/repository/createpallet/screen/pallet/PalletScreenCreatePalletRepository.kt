package `fun`.gladkikh.app.fastpallet6.repository.createpallet.screen.pallet

import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.pallet.PalletScreenCreatePalletDao
import `fun`.gladkikh.app.fastpallet6.domain.entity.Box
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.pallet.ItemPalletScreenData
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.pallet.PalletScreenCreatePalletData
import `fun`.gladkikh.app.fastpallet6.mapping.createpallet.screen.pallet.toObject
import `fun`.gladkikh.app.fastpallet6.mapping.createpallet.toObject
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.CreatePalletRepositoryUpdate

class PalletScreenCreatePalletRepository(
    private val palletScreenCreatePalletDao: PalletScreenCreatePalletDao,
    private val repositoryUpdate: CreatePalletRepositoryUpdate
) {

    fun getData(guidPallet: String): PalletScreenCreatePalletData {
        return palletScreenCreatePalletDao.getData(guidPallet).toObject()
    }

    fun getTotalData(guidPallet: String): PalletScreenCreatePalletData {
        return palletScreenCreatePalletDao.getTotalData(guidPallet).toObject()
    }

    fun getListItem(guidPallet: String): List<ItemPalletScreenData> {
        return palletScreenCreatePalletDao.getListItem(guidPallet).map {
            it.toObject()
        }
    }

    fun getBox(guid: String) =
        (repositoryUpdate.getObjectCreatePalletByGuid<Box>(guid)
                as Box)

    fun deleteBox(box:Box) = repositoryUpdate.delete(box)

    fun saveBox(box: Box){
        repositoryUpdate.save(box)
    }
}
