package me.hero.tobywebflux.coroutine.launch

import kotlinx.coroutines.*
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

/**
 * [launch]
 *
 */

fun main() = runBlocking<Unit> {

    launch {// Scope 묶
        delay(500) // 스레드를 차단하지 않고 일시중단 한다. delay된 스레드는 다른 작업을 진행한다.
        println("World!")
    }
    println("Hello")


    launch {
        val elapsedTime = measureTimeMillis {
            delay(150)
        }
        println("async task-1 $elapsedTime") //아래 코드보다 늦게 실해됨
    }

    launch {
        val elapsedTime = measureTimeMillis {
            delay(100)
        }
        println("async task-2 $elapsedTime") //먼저 실행 됨

    }




    val job1: Job = launch {
        val elapsedTime = measureTimeMillis {
            delay(150)
        }
        // 취소되어 실행되지 않음 ( 컴퓨터가 말도안되게 느리지 않는 이상 )
        println("async task-1 $elapsedTime")
    }
    job1.cancel()

    // CoroutineStart.Lazy 인 경우 job을 start 해줘야만 실행
    val job2: Job = launch(start = CoroutineStart.LAZY ) {
        val elapsedTime = measureTimeMillis {
            delay(100)
        }
        println("async task-2 $elapsedTime")
    }
    job2.start()


}
