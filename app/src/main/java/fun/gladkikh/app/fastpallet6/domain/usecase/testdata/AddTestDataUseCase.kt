package `fun`.gladkikh.app.fastpallet6.domain.usecase.testdata

import `fun`.gladkikh.app.fastpallet6.common.toSimpleDate
import `fun`.gladkikh.app.fastpallet6.domain.entity.*

import `fun`.gladkikh.app.fastpallet6.repository.createpallet.CreatePalletRepositoryUpdate
import java.util.*

class AddTestDataUseCase(private val createPalletRepositoryUpdate: CreatePalletRepositoryUpdate) {


    fun save() {

        var countBox = 0

        val listDocuments =
            (0..3).map {
                CreatePallet(
                    guid = it.toString(),
                    status = Status.LOADED.id,
                    number = it.toString(),
                    guidServer = it.toString(),
                    isWasLoadedLastTime = true,
                    description = "Формирование паллет №${it} от ${Date().toSimpleDate()}",
                    date = Date(),
                    dataChanged = Date(),
                    barcode = it.toString()
                )
            }



        listDocuments.forEach { doc ->
            createPalletRepositoryUpdate.save(doc)
            val listProduct = getListProduct(doc.guid)

            listProduct.forEach { prod ->
                createPalletRepositoryUpdate.save(prod)

                val listPallet = getListPallets(prod.guid)

                listPallet.forEach { pall ->
                    createPalletRepositoryUpdate.save(pall)

                    val listBox = getListBox(pall.guid)
                    createPalletRepositoryUpdate.saveListBox(listBox)
                    countBox += listBox.size
                }
            }
        }

        println(countBox)
    }


    private fun getListProduct(guidDoc: String): List<Product> {
        return (0..9).map {
            Product(
                guid = guidDoc +"_" + it,
                guidDoc = guidDoc,
                nameProduct = "Продукт $it",
                dataChanged = Date(),
                barcode = "3131116165165165",
                number = "1",
                isWasLoadedLastTime = true,
                countBox = 10,
                count = 150f,
                codeProduct = "А00" + it,
                ed = "кг.",
                guidProduct = "jhgkjhkj6455465",
                edCoff = 1f,
                weightBarcode = "12345515454",
                countPallet = 5,
                weightCoffProduct = 0.001f,
                weightStartProduct = 1,
                weightEndProduct = 6
                //pallets = getListPallet(guidDoc + it)
            )
        }
    }

    private fun getListPallets(guidProduct: String): List<Pallet> {
        return (0..99).map {
            Pallet(
                guid = guidProduct +"_" + it,
                guidProduct = guidProduct,
                number = it.toString(),
                barcode = "65465546546548",
                sclad = "Основной"
                //boxes = getListBox(guidProduct + it)
            )
        }
    }

    private fun getListBox(guidPallet: String): List<Box> {
        return (0..99).map {
            Box(
                guid = guidPallet +"_" + it,
                guidPallet = guidPallet,
                barcode = "654656516516516516",
                countBox = 1,
                data = Date(),
                weight = 25.56f
            )
        }
    }
}