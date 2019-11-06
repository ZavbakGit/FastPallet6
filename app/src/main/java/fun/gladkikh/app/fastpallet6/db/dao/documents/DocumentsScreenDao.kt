package `fun`.gladkikh.app.fastpallet6.db.dao.documents

import `fun`.gladkikh.app.fastpallet6.db.entity.documents.screen.ItemDocumentsScreenDataDb
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface DocumentsScreenDao {
    @Query("SELECT guid, status, number, date, description,1 as type, dataChanged  FROM CreatePalletDb")
    fun getListItem(): LiveData<List<ItemDocumentsScreenDataDb>>
}