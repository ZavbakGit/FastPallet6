package `fun`.gladkikh.app.fastpallet6.ui.base

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

abstract class BaseActivity :AppCompatActivity(){
    abstract val layoutRes: Int

    val isShowProgress = MutableLiveData<Boolean>()
    val barcodeLiveData = SingleLiveEvent<String>()

    private val keyDownMutableLiveData = MutableLiveData<Int>()
    val keyDownLiveData = MutableLiveData<Int>()


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (isShowProgress.value != true){
            keyDownMutableLiveData.postValue(keyCode)
        }
        return super.onKeyDown(keyCode, event)
    }

    fun getkeyDownLiveData():LiveData<Int> = keyDownLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
    }
}