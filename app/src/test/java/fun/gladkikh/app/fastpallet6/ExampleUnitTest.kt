package `fun`.gladkikh.app.fastpallet6

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        println(4.plusThree())

        assertEquals(4, 2 + 2)
    }

    fun Int.plusThree():Int{
        return this + 3
    }
}
