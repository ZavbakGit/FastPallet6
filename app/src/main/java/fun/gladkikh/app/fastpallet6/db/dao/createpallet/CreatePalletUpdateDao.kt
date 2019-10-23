package `fun`.gladkikh.app.fastpallet6.db.dao.createpallet

import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.BoxCreatePalletDb
import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.CreatePalletDb
import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.PalletCreatePalletDb
import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.ProductCreatePalletDb
import androidx.room.*

@Dao
interface CreatePalletUpdateDao {


    //region function for Box
    //******************************************************************************************
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIgnore(entity: BoxCreatePalletDb): Long

    @Update
    fun update(entity: BoxCreatePalletDb)


    @Transaction
    fun insertOrUpdateListBox(list: List<BoxCreatePalletDb>) {
        list.forEach {
            if (insertIgnore(it) == -1L) {
                update(it)
            }
        }
    }

    @Transaction
    fun insertOrUpdate(entity: BoxCreatePalletDb) {
        if (insertIgnore(entity) == -1L) {
            update(entity)
        }
    }

    @Delete
    fun delete(entity: BoxCreatePalletDb)

    @Query("SELECT * FROM BoxCreatePalletDb WHERE guid = :guid")
    fun getBoxCreatPalletByGuid(guid:String): BoxCreatePalletDb
    //endregion

    //region function for Pallet
    //******************************************************************************************
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIgnore(entity: PalletCreatePalletDb): Long

    @Update
    fun update(entity: PalletCreatePalletDb)

    @Transaction
    fun insertOrUpdate(entity: PalletCreatePalletDb) {
        if (insertIgnore(entity) == -1L) {
            update(entity)
        }
    }

    @Transaction
    fun insertOrUpdateListPallet(list: List<PalletCreatePalletDb>) {
        list.forEach {
            if (insertIgnore(it) == -1L) {
                update(it)
            }
        }
    }

    @Delete
    fun delete(entity: PalletCreatePalletDb)

    @Query("SELECT * FROM PalletCreatePalletDb WHERE guid = :guid")
    fun getPalletCreatePalletByGuid(guid:String): PalletCreatePalletDb

    @Query("SELECT * FROM PalletCreatePalletDb WHERE guidProduct = :guidProduct")
    fun getListPalletCreatPalletByGuidProduct(guidProduct:String):List<PalletCreatePalletDb>
    //endregion

    //region function for Product
    //******************************************************************************************
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIgnore(entity: ProductCreatePalletDb): Long

    @Update
    fun update(entity: ProductCreatePalletDb)

    @Transaction
    fun insertOrUpdate(entity: ProductCreatePalletDb) {
        if (insertIgnore(entity) == -1L) {
            update(entity)
        }
    }

    @Transaction
    fun insertOrUpdateListProduct(list: List<ProductCreatePalletDb>) {
        list.forEach {
            if (insertIgnore(it) == -1L) {
                update(it)
            }
        }
    }

    @Delete
    fun delete(entity: ProductCreatePalletDb)

    @Query("SELECT * FROM ProductCreatePalletDb WHERE guid = :guid")
    fun getProductCreatePalletByGuid(guid:String): ProductCreatePalletDb

    @Query("SELECT * FROM ProductCreatePalletDb WHERE guidProduct = :guid")
    fun getProductCreatePalletByGuidServer(guid:String): ProductCreatePalletDb

    @Query("SELECT * FROM ProductCreatePalletDb WHERE guidDoc = :guidDoc")
    fun getProductListCreatPalletByGuidDoc(guidDoc:String): List<ProductCreatePalletDb>


    //endregion

    //region function for CreatePallet
    //******************************************************************************************
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIgnore(entity: CreatePalletDb): Long

    @Update
    fun update(entity: CreatePalletDb)

    @Transaction
    fun insertOrUpdate(entity: CreatePalletDb) {
        if (insertIgnore(entity) == -1L) {
            update(entity)
        }
    }

    @Transaction
    fun insertOrUpdateListCreatePallet(list: List<CreatePalletDb>) {
        list.forEach {
            if (insertIgnore(it) == -1L) {
                update(it)
            }
        }
    }

    @Delete
    fun delete(entity: CreatePalletDb)

    @Query("SELECT * FROM CreatePalletDb WHERE guid = :guid")
    fun getCreatePalletByGuid(guid:String): CreatePalletDb

    @Query("SELECT * FROM CreatePalletDb WHERE guidServer = :guidServer")
    fun getCreatePalletByGuidServer(guidServer:String): CreatePalletDb
    //endregion


}