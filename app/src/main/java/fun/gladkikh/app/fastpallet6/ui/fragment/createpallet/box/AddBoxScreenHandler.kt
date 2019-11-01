package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.box

import `fun`.gladkikh.app.fastpallet6.domain.entity.Box
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.screen.box.BoxScreenCreatePalletRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class AddBoxScreenHandler(
    compositeDisposable: CompositeDisposable,
    private val repository: BoxScreenCreatePalletRepository,
    private val beforeAddFun: (box: Box, buffer: Int) -> Unit,
    private val afterSaveFun: (lastBox: Box, buffer: Int) -> Unit
) {
    private val publishSubject = PublishSubject.create<List<Box>>()
    private val bufferBoxList: MutableList<Box> = mutableListOf()


    init {
        compositeDisposable.add(
            getFlowableBox()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        )
    }

    private fun getFlowableBox(): Flowable<Box> {
        return publishSubject.toFlowable(BackpressureStrategy.BUFFER)
            .debounce(1000, TimeUnit.MILLISECONDS)
            .map {
                val list = bufferBoxList.map {
                    it.copy()
                }

                bufferBoxList.clear()
                repository.saveListBox(list)

                return@map list.last()
            }
            .doOnNext {
                afterSaveFun(it, bufferBoxList.size)
            }
    }

    fun addBox(box: Box) {
        bufferBoxList.add(box)
        beforeAddFun(box, bufferBoxList.size)
        publishSubject.onNext(bufferBoxList)
    }
}