package `fun`.gladkikh.app.fastpallet6.repository

import `fun`.gladkikh.app.fastpallet6.db.dao.DocumentsQueryDao
import `fun`.gladkikh.app.fastpallet6.domain.entity.CreatePallet
import `fun`.gladkikh.app.fastpallet6.domain.entity.Document
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.documents.DocumentsItem
import `fun`.gladkikh.app.fastpallet6.mapping.documents.toObject
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import java.util.*

class DocumentsRepository(private val documentsQueryDao: DocumentsQueryDao) {
    fun getDocumentsLiveData(): LiveData<List<DocumentsItem>> = Transformations.map(
        documentsQueryDao.getDocuments()
    ) { list ->
       return@map list.map {
            it.toObject()
        }
    }


    fun saveDocument(document: Document) {
        when (document) {
            is CreatePallet -> {
                val createPalletFromDb =
                    createPalletRepository.getDocByGuidServer(document.guidServer!!)

                val doc = createPalletFromDb?.apply { document.mixWithDb(this) } ?: document
                doc.apply {
                    this.status = document.status
                    this.barcode = document.barcode
                    this.dataChanged = Date()
                    this.description = document.description
                    this.isWasLoadedLastTime = true
                    this.number = document.number
                }

                createPalletRepository.saveDoc(doc)
                doc.listProduct.forEach {
                    createPalletRepository.saveProduct(it, document.guid)
                }

            }
        }
    }

    fun dellDocument(document: Document) {
        when (document) {
            is CreatePallet -> {
                createPalletRepository.dellDoc(document)
                //var list =  CreatePalletRepository.getPalletAll()
            }
        }
    }

    private fun CreatePallet.mixWithDb(createPalletFromDb: CreatePallet): CreatePallet {

        this.guid = createPalletFromDb.guid
        var oldProduct = createPalletRepository.getListProductByDoc(this.guid)

        //Удаляем каскадно все что без паллет
        oldProduct.forEach { prod ->
            prod.apply {
                pallets = createPalletRepository.getListPalletByProduct(prod.guid)
            }
            if (prod.pallets.isEmpty()) {
                createPalletRepository.dellProduct(
                    product = prod
                    , guidDoc = this.guid
                )
            }
        }

        //Читаем Еще раз
        oldProduct = createPalletRepository.getListProductByDoc(this.guid)

        //Оставили только те которых нет в старом списке
        val newList =
            this.listProduct
                .filter { product ->
                    product.guidProduct !in oldProduct.map { it.guidProduct }
                }

        //Сложили два списка
        createPalletFromDb.listProduct = oldProduct + newList

        return createPalletFromDb

    }

}