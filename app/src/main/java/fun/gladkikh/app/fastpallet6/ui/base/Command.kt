package `fun`.gladkikh.app.fastpallet6.ui.base

sealed class Command {
    object Close : Command()
    data class OpenForm(val data: Any? = null) : Command()
    data class StartConfirmDialog(val message: String, val data: Any? = null) : Command()
    data class StartEditNumberDialog(
        val title: String,
        val number: Int? = null,
        val field: String? = null
    ) : Command()

    data class StartEditDecimalDialog(
        val title: String,
        val number: Float? = null,
        val field: String? = null
    ) : Command()
}


