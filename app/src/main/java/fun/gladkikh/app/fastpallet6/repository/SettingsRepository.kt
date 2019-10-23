package `fun`.gladkikh.app.fastpallet6.repository

import `fun`.gladkikh.app.fastpallet6.domain.entity.SettingsPref
import android.content.Context
import androidx.preference.PreferenceManager

class SettingsRepository(private val context:Context) {

   fun getSettings():SettingsPref{
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)

        val host = "preference_host"
        val login = "preference_login"
        val pass = "preference_pass"
        val code = "preference_code_1c"
        val listTsd = "list_tsd"


        return SettingsPref(
            host = sharedPref.getString(host, "http://172.31.255.150/rmmt/hs/api/"),
            code = sharedPref.getString(code, "333"),
            login = sharedPref.getString(login, "Администратор"),
            pass = sharedPref.getString(pass, ""),
            typeTsd = sharedPref.getString(listTsd, 1.toString()).toIntOrNull()
        )
    }
}