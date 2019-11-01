package `fun`.gladkikh.app.fastpallet6.ui.base

import android.content.Context
import android.text.InputType
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager

fun startConfirmDialog(context: Context, message: String, positiveFun: () -> Unit) {
    AlertDialog.Builder(context)
        .setTitle("Вы уверены!")
        .setMessage(message)
        .setNegativeButton(
            android.R.string.cancel,
            null
        ) // dismisses by default
        .setPositiveButton(android.R.string.ok) { dialog, which ->
            positiveFun()
        }
        .show()
}

fun startEditDialogNumber(
    supportFragmentManager: FragmentManager,
    text: String?,
    title: String,
    positiveFun: (text: String) -> Unit
) {
    val dialog = EditTextDialog.newInstance(
        text = text ?: "",
        hint = title,
        title = title
    )
    dialog.onOk = {
        val text = dialog.editText.text
        positiveFun(text.toString())
    }
    dialog.show(supportFragmentManager, "editDescription")
}

fun startEditDialogDecimal(
    supportFragmentManager: FragmentManager,
    text: String?,
    title: String,
    positiveFun: (text: String) -> Unit
) {
    val dialog = EditTextDialog.newInstance(
        text = text ?: "",
        hint = title,
        title = title,
        inputType = (InputType.TYPE_CLASS_NUMBER+InputType.TYPE_NUMBER_FLAG_DECIMAL)
    )
    dialog.onOk = {
        val text = dialog.editText.text
        positiveFun(text.toString())
    }
    dialog.show(supportFragmentManager, "editDescription")
}

