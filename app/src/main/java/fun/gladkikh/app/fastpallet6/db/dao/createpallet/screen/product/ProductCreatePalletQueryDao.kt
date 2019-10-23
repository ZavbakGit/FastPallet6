package `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.product



import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.screen.product.PalletItemCreatePalletQueryDb
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface ProductCreatePalletQueryDao {
    @Query("SELECT  " +
                "  Sum(Box.countBox) as boxCount " +
                ", Sum(Round(Box.weight*1000))/1000 as boxWeight " +
                ", Count(Box.guid) as boxRow " +
                ", Pal.guid as palGuid " +
                ", MAX(Prod.nameProduct) as prodName " +
                ", MAX(Pal.number) as palNumber  " +
                "from BoxCreatePalletDb Box " +
                "    left join PalletCreatePalletDb Pal on Pal.guid = Box.guidPallet " +
                "    left join ProductCreatePalletDb Prod on Prod.guid = Pal.guidProduct " +
                "where Pal.guidProduct = :guidProduct " +
                "GROUP by Pal.guid, Prod.guid;"
    )
    fun getListPalletItemCreatePallet(guidProduct: String): LiveData<List<PalletItemCreatePalletQueryDb>>
}