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

    val xv = 2
    val isEven = if(xv %2 == 0) "짝" else "홀"
    println(isEven)

    for(i in 0..9){
        println(i)
    }

    val numbers = listOf(1, 2, 3, 4, 5)
    for(i in numbers){
        println(i)
    }

    var xw = 1

    when (xw) {
        1 -> println("1입니다")
        2 -> println("2입니다")
        3, 4, 5 -> println("3이나 4나 5입니다")
        in 6..20 -> println("6부터 20 사이의 값입니다")
        !in 8..10 -> println("")
        else -> println("else")
    }

    val person = Person("홍길동")
}

fun myMethod(a: Int, b: Int): Int = a + b

class Person(name: String) {
    init{
        println(name)
    }
}