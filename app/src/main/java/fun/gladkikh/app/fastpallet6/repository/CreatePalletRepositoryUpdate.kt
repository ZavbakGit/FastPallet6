package `fun`.gladkikh.app.fastpallet6.repository

import `fun`.gladkikh.app.fastpallet6.db.dao.CreatePalletUpdateDao
import `fun`.gladkikh.app.fastpallet6.domain.entity.Box
import `fun`.gladkikh.app.fastpallet6.domain.entity.CreatePallet
import `fun`.gladkikh.app.fastpallet6.domain.entity.Pallet
import `fun`.gladkikh.app.fastpallet6.domain.entity.Product

import `fun`.gladkikh.app.fastpallet6.mapping.createpallet.toDb

class CreatePalletRepositoryUpdate(private val createPalletUpdateDao: CreatePalletUpdateDao) {

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