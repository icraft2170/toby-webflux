package me.hero.tobywebflux.coroutine.runblocking

import kotlinx.coroutines.runBlocking

/**
 * [runBlocking]
 * 해당 블록 안에 코드는 블로킹으로 작동한다.
 * 다른 라이브러리에서 코루틴을 지원하지 않는 경우 사용할 수 있다.
 */
fun main() {
    // 코루틴 빌더
    runBlocking {
        println("Thread Name : ${Thread.currentThread().name}")
        println("Hello")
    }

    println("Word")
    println("Thread Name : ${Thread.currentThread().name}")
}