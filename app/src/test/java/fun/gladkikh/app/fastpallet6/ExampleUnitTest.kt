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
        val str:String? = null

      val k =   str.also{
            println("Null")
        }.let{
            it + "ljhjk"
        }

        prinln("111111" + k)


        assertEquals(4, 2 + 2)
    }

   fun String.MyPlus():String{
       return this.plus( "Hi")
   }
}
