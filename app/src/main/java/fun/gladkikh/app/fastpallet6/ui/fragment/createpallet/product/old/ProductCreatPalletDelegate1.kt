package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.product.old

import `fun`.gladkikh.app.fastpallet6.common.getNumberDocByBarCode
import `fun`.gladkikh.app.fastpallet6.common.isPallet
import `fun`.gladkikh.app.fastpallet6.domain.entity.Pallet
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.ValidationResult
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.ProductCreatePalletRepository
import androidx.lifecycle.MutableLiveData
import java.util.*

class ProductCreatPalletDelegate1(
    private val viewModel: ProductCreatePalletViewModel1,
    private val productCreatePalletRepository: ProductCreatePalletRepository,
    private val viewStateLiveData: MutableLiveData<ProductCreatePalletViewState1>
) {

    fun addPallet(barcode: String) {
        val validation = validation(barcode)

        if (!validation.result) {
            viewModel.messageError.postValue(validation.message)
        } else {
            val pallet = Pallet(
                guid = UUID.randomUUID().toString(),
                count = null,
                countBox = null,
                number = getNumberDocByBarCode(barcode),
                barcode = barcode,
                dataChanged = Date(),
                nameProduct = null,
                sclad = null,
                state = null
            )

            //createPalletRepository.savePallet(pallet, liveDataMerger.value?.product?.guid!!)
        }
    }


    fun validation(barcode: String): ValidationResult {

        if (!isPallet(barcode)) return ValidationResult(false, "Этот штрих код не паллеты")

//        if (!checkEditDoc(viewStateLiveData.value?.doc)) return ValidationResult(
//            false,
//            "Нельзя изменять документ с этим статусом"
//        )

        val number: String?

        try {
            number = getNumberDocByBarCode(barcode)
        } catch (e: Exception) {
            return ValidationResult(false, "Ошибка получения номмера паллеты!")
        }


//        if (createPalletRepository.getListPalletByProduct(liveDataMerger.value?.product?.guid!!).find {
//                it.number.equals(
//                    number
//                )
//            } != null) {
//            return ValidationResult(false, "Паллета уже внесена!")
//        }

        return ValidationResult(true)
    }
}