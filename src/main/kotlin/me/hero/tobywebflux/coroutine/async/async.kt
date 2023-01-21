package me.hero.tobywebflux.coroutine.async

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun sum(a: Int, b: Int) = a + b

/**
 * [async/await] 의 경우 응답 값을 받을 수 있다.
 * launch 가 Runnable 이라면 async 는 Callable
 *
 * JavaScript 의 async await 키워
 */
fun main() = runBlocking<Unit> {

    val result: Deferred<Int> = async {
        delay(100)
        sum(1, 3)
    }

    // 값을 받을 때는 await 의 사용이 필요하다.
    println("result1 : ${result.await()}")

    val result2: Deferred<Int> = async {
        delay(100)
        sum(2, 5)
    }

    println("result2 : ${result2.await()}")

}