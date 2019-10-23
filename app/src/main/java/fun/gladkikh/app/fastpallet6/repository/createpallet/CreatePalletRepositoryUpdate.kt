package `fun`.gladkikh.app.fastpallet6.repository.createpallet

import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.CreatePalletUpdateDao
import `fun`.gladkikh.app.fastpallet6.db.entity.ProductCreatePalletDb
import `fun`.gladkikh.app.fastpallet6.domain.entity.*

import `fun`.gladkikh.app.fastpallet6.mapping.createpallet.toDb
import `fun`.gladkikh.app.fastpallet6.mapping.createpallet.toObject

class CreatePalletRepositoryUpdate(val createPalletUpdateDao: CreatePalletUpdateDao) {

    fun getListPalletByGuidProduct(guidProduct:String):List<Pallet>{
        return createPalletUpdateDao.getListPalletCreatPalletByGuidProduct(guidProduct).map {
            it.toObject()
        }
    }

    fun getListProductCreatPalletByGuidDoc(guidDoc: String): List<Product> {
        return createPalletUpdateDao.getProductListCreatPalletByGuidDoc(guidDoc).map {
            it.toObject()
        }
    }

    inline fun <reified T> getObjectCreatePalletByGuid(guid: String): Any? {
        return when (T::class.java) {
            Box::class.java -> createPalletUpdateDao.getBoxCreatPalletByGuid(guid)?.toObject()
            Pallet::class.java -> createPalletUpdateDao.getPalletCreatePalletByGuid(guid)?.toObject()
            Product::class.java -> createPalletUpdateDao.getProductCreatePalletByGuid(guid)?.toObject()
            CreatePallet::class.java -> createPalletUpdateDao.getCreatePalletByGuid(guid)?.toObject()
            else -> throw Throwable("Объект не в базе!")
        }
    }

    inline fun<reified T> getObjectCreatePalletByGuidServer(guidServer: String): Any? {
        return when (T::class.java) {
            Product::class.java -> createPalletUpdateDao.getProductCreatePalletByGuidServer(guidServer)?.toObject() as? T?
            CreatePallet::class.java -> createPalletUpdateDao.getCreatePalletByGuidServer(guidServer)?.toObject() as? T?
            else -> throw Throwable("Объект не в базе!")
        }
    }


    fun <T> save(intety: T) {
        when (intety) {
            is Box -> createPalletUpdateDao.insertOrUpdate(intety.toDb())
            is Pallet -> createPalletUpdateDao.insertOrUpdate(intety.toDb())
            is Product -> createPalletUpdateDao.insertOrUpdate(intety.toDb())
            is CreatePallet -> createPalletUpdateDao.insertOrUpdate(intety.toDb())
            else -> throw Throwable("Этот объект нельзя сохранить! ${intety.toString()}")
        }
    }

    fun <T> delete(intety: T) {
        when (intety) {
            is Box -> createPalletUpdateDao.delete(intety.toDb())
            is Pallet -> createPalletUpdateDao.delete(intety.toDb())
            is Product -> createPalletUpdateDao.delete(intety.toDb())
            is CreatePallet -> createPalletUpdateDao.delete(intety.toDb())
            else -> throw Throwable("Этот объект нельзя удалять! ${intety.toString()}")
        }
    }

    fun saveListBox(list: List<Box>) =
        createPalletUpdateDao.insertOrUpdateListBox(list.map { it.toDb() })

}