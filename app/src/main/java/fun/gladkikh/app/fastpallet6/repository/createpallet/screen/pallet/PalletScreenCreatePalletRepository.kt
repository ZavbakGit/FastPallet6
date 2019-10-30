package `fun`.gladkikh.app.fastpallet6.repository.createpallet.screen.pallet

import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.pallet.PalletScreenCreatePalletDao
import `fun`.gladkikh.app.fastpallet6.domain.entity.Pallet
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.pallet.ItemPalletScreenData
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.pallet.PalletScreenCreatePalletData
import `fun`.gladkikh.app.fastpallet6.mapping.createpallet.screen.pallet.toObject
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

    fun savePallet(pallet: Pallet){
        repositoryUpdate.save(pallet)
    }
}