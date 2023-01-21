package me.hero.tobywebflux.coroutine.suspend

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 * [suspend]
 * - 일시중단 가능한 함수
 * - suspend 함수는 suspend 함수 혹은 코루틴 스코프 안에서만 호출될 수 있다.
 */
suspend fun main() {
    printHello()
    doSomething()
    runBlocking {
        doSomething()
    }
    playSomethingWithCoroutineScope()
//    playSomethingWithRunBlocking()
}

fun printHello() = println("Hello")


suspend fun doSomething() {
    printHello()
}

/**
 * coroutineScope
 * - 현재 스레드가 블로킹 되지 않고 실행 됨
 */
suspend fun playSomethingWithCoroutineScope() = coroutineScope {
    launch {
        delay(200)
        println("World!!")
        println("Thread : ${Thread.currentThread().name}")
    }
    println("Hello")
    println("Thread : ${Thread.currentThread().name}")
}

suspend fun playSomethingWithRunBlocking() = runBlocking {
    launch {
        delay(200)
        println("World!!")
        println("Thread : ${Thread.currentThread().name}")
    }
    println("Hello")
    println("Thread : ${Thread.currentThread().name}")
}