package com.example.crazyandroid.practice
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

class useKotlinCoroutinesFlow {
}

//这里我们展现了使用flow的一个小demo
fun main() = runBlocking {
    // 创建Flow
    val numbersFlow = flow {
        for (i in 1..5) {
            delay(100)  // 模拟异步操作
            emit(i)
        }
    }

    // 转换Flow
    val squaresFlow = numbersFlow.map { number ->
        number * number
    }

    // 收集Flow
    squaresFlow.collect { square ->
        println("Square: $square")
    }

    // 组合Flow
    val anotherFlow = flow {
        for (i in 6..10) {
            delay(100)
            emit(i)
        }
    }

    val combinedFlow = numbersFlow.combine(anotherFlow) { a, b ->
        a * b
    }

    combinedFlow.collect { sum ->
        println("Sum: $sum")
    }

    // 异常处理
    val errorFlow = flow {
        for (i in 1..3) {
            if (i == 2) {
                throw RuntimeException("Error on $i")
            }
            emit(i)
        }
    }.catch { e ->
        println("Caught exception: $e")
    }

    errorFlow.collect { number ->
        println("Number: $number")
    }
}
