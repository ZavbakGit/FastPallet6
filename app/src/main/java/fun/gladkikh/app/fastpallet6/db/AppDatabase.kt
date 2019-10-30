package `fun`.gladkikh.app.fastpallet6.db

import `fun`.gladkikh.app.fastpallet6.db.dao.DocumentsQueryDao
import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.CreatePalletUpdateDao
import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.box.BoxScreenCreatePalletDao
import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.box.old.BoxCreatePalletQueryDao
import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.doc.DocCreatePalletQueryDao
import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.pallet.PalletCreatePalletQueryDao
import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.product.ProductCreatePalletQueryDao
import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.BoxCreatePalletDb
import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.CreatePalletDb
import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.PalletCreatePalletDb
import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.ProductCreatePalletDb

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

    abstract fun getBoxScreenCreatePalletDao(): BoxScreenCreatePalletDao

    abstract fun getCreatePalletUpdateDao(): CreatePalletUpdateDao
    abstract fun getDocumentsQueryDao(): DocumentsQueryDao
    abstract fun getDocCreatePalletQueryDao(): DocCreatePalletQueryDao
    abstract fun getProductCreatePalletQueryDao(): ProductCreatePalletQueryDao
    abstract fun getPalletCreatePalletQueryDao(): PalletCreatePalletQueryDao
    abstract fun getBoxCreatePalletQueryDao(): BoxCreatePalletQueryDao
}