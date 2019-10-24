package `fun`.gladkikh.app.fastpallet6.ui.activity

import `fun`.gladkikh.app.fastpallet6.R
import `fun`.gladkikh.app.fastpallet6.repository.SettingsRepository
import `fun`.gladkikh.app.fastpallet6.ui.base.BaseActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.gladkikh.mylibrary.BarcodeHelper
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.progress_overlay.*
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity() {
    override val layoutRes = R.layout.activity_main
    lateinit var navController: NavController

    private val settingsRepository: SettingsRepository by inject()

    private var barcodeHelper: BarcodeHelper? = null

    private val barcodeObserver = Observer<String> {
        if (isShowProgress.value != true) {
            barcodeLiveData.postValue(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        barcodeHelper = BarcodeHelper(
            this,
            BarcodeHelper.TYPE_TSD.getTypeTSD(settingsRepository.getSettings().typeTsd)
        )

        barcodeHelper?.getBarcodeLiveData()?.observe(this, barcodeObserver)


        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.documentsFragment) {

                barcodeHelper?.getBarcodeLiveData()?.removeObserver(barcodeObserver)

                barcodeHelper = BarcodeHelper(
                    this,
                    BarcodeHelper.TYPE_TSD.getTypeTSD(settingsRepository.getSettings().typeTsd)
                )
                barcodeHelper?.getBarcodeLiveData()?.observe(this, barcodeObserver)
            }
        }

    }

    fun showMessage(text: CharSequence) {
        Snackbar.make(root, text, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }

    fun showErrorMessage(text: CharSequence) {
        Snackbar.make(root, text, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }

    fun showProgress() {
        progressView.visibility = View.VISIBLE
        isShowProgress.postValue(true)
    }

    fun hideProgress() {
        progressView.visibility = View.GONE
        isShowProgress.postValue(false)
    }

}
