package me.hero.tobywebflux.fastcampus_annotation_controller

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory
import org.springframework.context.annotation.Bean

@SpringBootApplication
class FastCampusAnnotationControllerApplication {
    @Bean
    fun nettyReactiveWebServerFactory(): NettyReactiveWebServerFactory = NettyReactiveWebServerFactory()
}

fun main(args: Array<String>) {
    runApplication<FastCampusAnnotationControllerApplication>(*args)
}
