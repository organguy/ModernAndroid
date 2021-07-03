package com.earlynetworks.modernandroid

fun main(){
    var x = 10
    var str = "Hello"
    var isMarried = true

    println("$str World")
    println(myMethod(10, 20))

    val items = listOf(1, 2, 3, 4, 5) // 불변
    println(items)

    val items2 = arrayListOf(1, 2, 3, 4, 5) // 변경 가능
    items2.add(6)
    items2.remove(1)
    items2[0] = 10

    println(items2)
}

fun myMethod(a: Int, b: Int): Int = a + b