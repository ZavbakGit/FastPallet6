package `fun`.gladkikh.app.fastpallet6.domain

fun getCyrillicLetterByNumber(s: String): String {

    return when (s) {
        "01" -> "А"

        "02" -> "Б"

        "03" -> "В"

        "04" -> "Г"

        "05" -> "Д"

        "06" -> "Е"

        "07" -> "Ё"

        "08" -> "Ж"

        "09" -> "З"

        "10" -> "И"

        "11" -> "Й"

        "12" -> "К"

        "13" -> "Л"

        "14" -> "М"

        "15" -> "Н"

        "16" -> "О"

        "17" -> "П"

        "18" -> "Р"

        "19" -> "С"

        "20" -> "Т"

        "21" -> "У"

        "22" -> "Ф"

        "23" -> "Х"

        "24" -> "Ц"

        "25" -> "Ч"

        "26" -> "Ш"

        "27" -> "Щ"

        "28" -> "Ъ"

        "29" -> "Ы"

        "30" -> "Ь"

        "31" -> "Э"

        "32" -> "Ю"

        "33" -> "Я"

        else -> ""
    }
}