package me.hero.tobywebflux.toby12.main

import me.hero.tobywebflux.util.Logger.log
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono
import java.util.concurrent.CompletableFuture

@SpringBootApplication
@EnableAsync
class TobyWebfluxApplication {
    @Bean
    fun nettyReactiveWebServerFactory(): NettyReactiveWebServerFactory = NettyReactiveWebServerFactory()
}


@RestController
class MyController(
    private val myService: MyService,
) {

    val client: WebClient =  WebClient.create()

    /**
     * Mono는 subscribe() 호출 시 그 내용이 실행되는데 subscribe는 Return 했을 때 스프링 컨테이너에서
     * 자동으로 호ㅗ해준다.
     */
//    @GetMapping("/rest")
//    fun rest(idx: Int): Mono<String> {
//        return client.get().uri(URI1, idx).exchangeToMono { it.bodyToMono<String>() }
//    }

    /**
     * Mono는 단일 요소를 담고있다.
     * flatMap 을 통해 Mono<String>에서 String 타입의 str 만 가지고 나와서 처리할 수 있다.
     */
    @GetMapping("/rest")
    fun rest(idx: Int): Mono<String> {
        return client.get().uri(URI1, idx).exchangeToMono { it.bodyToMono<String>() }
            .doOnNext { log.info { "Hello $it" } }
            .flatMap {str ->
                client.get().uri(URI2, str).exchangeToMono { it.bodyToMono<String>() }
            }
            .doOnNext { log.info { "Hi $it" } }
            .flatMap {
                Mono.fromCompletionStage(myService.work(it))
            }
            .doOnNext { log.info { "Bye $it" } }

    }


    companion object {
        const val URI1: String = "http://localhost:8081/service?request={request}"
        const val URI2: String = "http://localhost:8081/service2?request={request}"
    }

}

@Service
class MyService {

    /**
     * 비동기 처리안해주면 Netty에 Worker Thread를 잡고있다.
     */
    @Async
    fun work(request: String): CompletableFuture<String> {
        return CompletableFuture.completedFuture("$request/asyncwork")
    }
}

fun main(args: Array<String>) {
    runApplication<TobyWebfluxApplication>(*args) {
        this.setDefaultProperties(
            mapOf("reactor.ipc.netty.workerCount" to 1, "reactor.ipc.netty.pool.maxConnections" to 2000)
        )
    }
}
