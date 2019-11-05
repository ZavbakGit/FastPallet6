package `fun`.gladkikh.app.fastpallet6.db

import `fun`.gladkikh.app.fastpallet6.db.dao.DocumentsQueryDao
import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.CreatePalletUpdateDao
import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.box.BoxScreenCreatePalletDao
import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.doc.DocScreenCreatePalletDao
import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.doc.old.DocCreatePalletQueryDao
import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.pallet.PalletScreenCreatePalletDao
import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.product.ProductScreenCreatePalletDao
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
    abstract fun getPalletScreenCreatePalletDao(): PalletScreenCreatePalletDao
    abstract fun getProductScreenCreatePalletDao(): ProductScreenCreatePalletDao
    abstract fun getDocScreenCreatePalletDao(): DocScreenCreatePalletDao

    abstract fun getCreatePalletUpdateDao(): CreatePalletUpdateDao
    abstract fun getDocumentsQueryDao(): DocumentsQueryDao
    abstract fun getDocCreatePalletQueryDao(): DocCreatePalletQueryDao
}