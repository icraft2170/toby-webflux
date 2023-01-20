package me.hero.tobywebflux.annotation_controller

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory
import org.springframework.context.annotation.Bean

@SpringBootApplication
class AnnotationControllerApplication {
    @Bean
    fun nettyReactiveWebServerFactory(): NettyReactiveWebServerFactory = NettyReactiveWebServerFactory()
}

fun main(args: Array<String>) {
    runApplication<AnnotationControllerApplication>(*args)
}
