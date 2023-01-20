package me.hero.tobywebflux.function_endpoint

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory
import org.springframework.context.annotation.Bean

@SpringBootApplication
class FunctionEndPointApplication {
    @Bean
    fun nettyReactiveWebServerFactory(): NettyReactiveWebServerFactory = NettyReactiveWebServerFactory()
}

fun main(args: Array<String>) {
    runApplication<FunctionEndPointApplication>(*args)
}
