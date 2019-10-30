package `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.pallet.old

import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.screen.pallet.old.BoxItemCreatePalletQueryDb
import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.screen.pallet.old.PalletTotalInfoCreatePalletQueryDb
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface PalletCreatePalletQueryDao {

    @Query("SELECT   " +
            " MAX(Prod.guidDoc) AS docGuid  " +
            ",Max(Prod.guid) AS prodGuid  " +
            ",MAX(Pal.guid) AS palGuid  " +
            ",Box.guid AS boxGuid  " +
            ",SUM(Box.countBox) AS boxCount  " +
            ",SUM(Round(Box.weight * 1000) ) / 1000 AS boxWeight  " +
            "FROM ProductCreatePalletDb Prod  " +
            "JOIN PalletCreatePalletDb Pal ON Pal.guidProduct = Prod.guid  " +
            "JOIN BoxCreatePalletDb Box ON Box.guidPallet = Pal.guid  " +
            "WHERE Pal.guid =:guidPallet  " +
            "GROUP By Box.guid")
    fun getListBoxCreatePalletLiveData(guidPallet: String): LiveData<List<BoxItemCreatePalletQueryDb>>


    @Query("Select  " +
            " MAX(Prod.guidDoc) as docGuid " +
            ",Max(Prod.guid) as prodGuid " +
            ",Pal.guid as palGuid " +
            ",MAX(Pal.number) as palNumber " +
            ",COUNT(distinct Pal.guid) as palCount " +
            ",SUM(Box.countBox) as boxCount " +
            ",SUM(Round(Box.weight * 1000) ) / 1000 as boxWeight " +
            ",COUNT(distinct Box.guid) as boxRow " +
            "from ProductCreatePalletDb Prod " +
            "JOIN PalletCreatePalletDb Pal on Pal.guidProduct = Prod.guid " +
            "JOIN BoxCreatePalletDb Box on Box.guidPallet = Pal.guid " +
            "where Pal.guid =:guidPallet " +
            "Group By Pal.guid")
    fun getTotalInfoPalletCreatePalletQueryDb(guidPallet:String): LiveData<PalletTotalInfoCreatePalletQueryDb>
}