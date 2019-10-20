package `fun`.gladkikh.app.fastpallet6.db.dao

import `fun`.gladkikh.app.fastpallet6.db.entity.BoxCreatePalletDb
import `fun`.gladkikh.app.fastpallet6.db.entity.CreatePalletDb
import `fun`.gladkikh.app.fastpallet6.db.entity.PalletCreatePalletDb
import `fun`.gladkikh.app.fastpallet6.db.entity.ProductCreatePalletDb
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
    fun insertOrUpdateListCreatPallet(list: List<CreatePalletDb>) {
        list.forEach {
            if (insertIgnore(it) == -1L) {
                update(it)
            }
        }
    }

    @Delete
    fun delete(entity: CreatePalletDb)
    //endregion


}