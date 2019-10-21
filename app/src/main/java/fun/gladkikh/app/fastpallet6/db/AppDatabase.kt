package `fun`.gladkikh.app.fastpallet6.db

import `fun`.gladkikh.app.fastpallet6.db.dao.DocumentsQueryDao
import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.CreatePalletUpdateDao
import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.DocCreatePalletQueryDao
import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.ProductCreatePalletQueryDao
import `fun`.gladkikh.app.fastpallet6.db.entity.BoxCreatePalletDb
import `fun`.gladkikh.app.fastpallet6.db.entity.CreatePalletDb
import `fun`.gladkikh.app.fastpallet6.db.entity.PalletCreatePalletDb
import `fun`.gladkikh.app.fastpallet6.db.entity.ProductCreatePalletDb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        CreatePalletDb::class,
        ProductCreatePalletDb::class,
        PalletCreatePalletDb::class,
        BoxCreatePalletDb::class], version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getCreatePalletUpdateDao(): CreatePalletUpdateDao
    abstract fun getDocumentsQueryDao(): DocumentsQueryDao
    abstract fun getDocCreatePalletQueryDao(): DocCreatePalletQueryDao
    abstract fun getProductCreatePalletQueryDao(): ProductCreatePalletQueryDao
}