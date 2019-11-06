package `fun`.gladkikh.app.fastpallet6.repository.documents

import `fun`.gladkikh.app.fastpallet6.db.dao.documents.DocumentsScreenDao
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.documents.ItemDocumentsScreenData
import `fun`.gladkikh.app.fastpallet6.mapping.documents.toObject
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.CreatePalletRepositoryUpdate
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

class DocumentsScreenRepository(
    private val documentsScreenDao: DocumentsScreenDao,
    private val repositoryUpdate: CreatePalletRepositoryUpdate
) {

    fun getDocumentsLiveData(): LiveData<List<ItemDocumentsScreenData>> =
        Transformations.map(
            documentsScreenDao.getListItem()
        ) { list ->
            return@map list.map {
                it.toObject()
            }
        }

}



