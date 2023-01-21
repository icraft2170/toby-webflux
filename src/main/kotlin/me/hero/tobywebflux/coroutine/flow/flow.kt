package me.hero.tobywebflux.coroutine.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

/**
 * [flow]
 * 다수의 결과 값이 있는 경우 사용하는 컨테이너
 */
fun main() = runBlocking<Unit> {

    val flow = simple()
    // collect 는 Flux 나 Mono 의 subscribe
    //println(flow)
    flow.collect { value -> println("value : $value") }


}

fun simple() : Flow<Int> = flow {
    println("Flow started")

    for (i in 1..3) {
        delay(100)
        emit(i)
    }
}
