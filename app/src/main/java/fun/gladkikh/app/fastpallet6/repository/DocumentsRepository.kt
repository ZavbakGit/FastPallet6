package `fun`.gladkikh.app.fastpallet6.repository

import `fun`.gladkikh.app.fastpallet6.db.dao.DocumentsQueryDao
import `fun`.gladkikh.app.fastpallet6.db.entity.CreatePalletDb
import `fun`.gladkikh.app.fastpallet6.db.entity.ProductCreatePalletDb
import `fun`.gladkikh.app.fastpallet6.domain.entity.CreatePallet
import `fun`.gladkikh.app.fastpallet6.domain.entity.Document
import `fun`.gladkikh.app.fastpallet6.domain.entity.Product
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.documents.DocumentsItem
import `fun`.gladkikh.app.fastpallet6.mapping.createpallet.toObject
import `fun`.gladkikh.app.fastpallet6.mapping.documents.toObject
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.CreatePalletRepositoryUpdate
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import java.util.*

class DocumentsRepository(
    private val documentsQueryDao: DocumentsQueryDao,
    val createPalletRepositoryUpdate: CreatePalletRepositoryUpdate
) {
    fun getDocumentsLiveData(): LiveData<List<DocumentsItem>> = Transformations.map(
        documentsQueryDao.getDocuments()
    ) { list ->
        return@map list.map {
            it.toObject()
        }
    }


    inline fun <reified T : Document> getDocumentByGuid(guid: String): Document {
        return when (T::class) {
            CreatePallet::class ->
                (createPalletRepositoryUpdate
                    .getObjectCreatePalletByGuid<CreatePallet>(guid) as CreatePalletDb).toObject()

            else -> throw Throwable("Из базы запросили нейизвестный тип!")

        }
    }

    fun saveDocument(doсFromServer: Document) {
        when (doсFromServer) {
            is CreatePallet -> {
                val docFromDb =
                    createPalletRepositoryUpdate
                        .getObjectCreatePalletByGuidServer<CreatePallet>(doсFromServer.guidServer!!) as? CreatePallet?


                val doc = docFromDb ?: doсFromServer


                val (listSave, listDell) = mixStringSavaAndDell(doc, doсFromServer)

                //Удаляем ненужные
                listDell.forEach {
                    createPalletRepositoryUpdate.delete(it)
                }


                //Миксуем
                doc.apply {
                    this.status = doсFromServer.status
                    this.barcode = doсFromServer.barcode
                    this.dataChanged = Date()
                    this.description = doсFromServer.description
                    this.isWasLoadedLastTime = true
                    this.number = doсFromServer.number
                }

                createPalletRepositoryUpdate.save(doc)

                listSave.forEach {
                    createPalletRepositoryUpdate.save(it)
                }

            }
        }
    }

    fun dellDocument(document: Document) {
        when (document) {
            is CreatePallet -> {
                createPalletRepositoryUpdate.delete(document)
            }
        }
    }


    /**
     * Возвращает два списка первый для сохранения,
     * второй удалять
     */
    private fun mixStringSavaAndDell(
        doc: CreatePallet,
        docFromServer: CreatePallet
    ): Pair<List<Product>, List<Product>> {

        val list = createPalletRepositoryUpdate.getListProductCreatPalletByGuidDoc(doc.guid)
        val listFromServer = docFromServer.listProduct

        //Если старый пустой, то миксрвать нескем
        if (list.isEmpty()) {
            return Pair(listFromServer, listOf())
        }

        //Загрузим паллеты
        list.forEach {
            it.pallets = createPalletRepositoryUpdate.getListPalletByGuidProduct(it.guid)
        }


        val listDell = list.filter { prod ->
            //Удаляем если нет паллет или не пришел с сервера
            prod.guidProduct !in listFromServer.map { it.guidProduct } && prod.pallets.isEmpty()
        }

        val listSave = list.filter { prod ->
            //Оставляем если есть паллеты или пришел с сервера
            prod.guidProduct in listFromServer.map { it.guidProduct } || prod.pallets.isNotEmpty()
        }

        //Поменяем guid
        val listAdd = listFromServer.map {
            it.apply {
                it.guidDoc = doc.guid
            }
        }.filter {
            //Уберем которые уже есть
            it.guidProduct !in listSave.map { it.guidProduct }
        }

        return Pair(listSave + listAdd, listDell)

    }

    private fun CreatePallet.mixWithDb(createPalletFromDb: CreatePallet): CreatePallet {

        this.guid = createPalletFromDb.guid
        var oldProduct = createPalletRepositoryUpdate.getListProductCreatPalletByGuidDoc(this.guid)

        //Удаляем каскадно все что без паллет
        oldProduct.forEach { prod ->
            prod.apply {
                pallets = createPalletRepositoryUpdate.getListPalletByGuidProduct(prod.guid)
            }
            if (prod.pallets.isEmpty()) {
                createPalletRepositoryUpdate.delete(prod)
            }
        }

        //Читаем Еще раз
        oldProduct = createPalletRepositoryUpdate.getListProductCreatPalletByGuidDoc(this.guid)

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