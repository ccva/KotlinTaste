package com.va.kotlintaste

import org.jsoup.Jsoup
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun testJsoup() {

        val string = "<html><head><title>First parse</title></head><body><p>Parsed HTML into a doc.</p></body></html>"

        var document = Jsoup.parse(string)

        var head = document.head()

        println(head)

        var body = document.body()

        println(body)

        var title = document.title()

        println(title)

        var location = document.location()

        println(location)

    }


    @Test
    fun testJsoupConnect() {

        var list = listOf("a", "b", "c")

        list.forEachIndexed { index, s -> println("position $index is $s") }

        list.filter { it != "b" }.forEach { println(it) }

        list.reversed().forEach { println(it) }

        with(list){



        }


    }

}
