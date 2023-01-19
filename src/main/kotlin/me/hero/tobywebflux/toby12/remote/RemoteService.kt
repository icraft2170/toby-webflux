package me.hero.tobywebflux.toby12.remote

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.embedded.tomcat.TomcatReactiveWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RemoteController{
    @GetMapping("/service")
    fun service1(request: String): String {
        Thread.sleep(1000)
        return "$request/service"
    }

    @GetMapping("/service2")
    fun service2(request: String): String {
        Thread.sleep(1000)
        return "$request/service2"
    }
}

@SpringBootApplication
class RemoteService() {
    @Bean
    fun tomcatReactiveWebServerFactory(): TomcatReactiveWebServerFactory =
        TomcatReactiveWebServerFactory()
}


fun main(args: Array<String>) {
    runApplication<RemoteService>(*args) {
        this.setDefaultProperties(
            mapOf("server.port " to "8081", "server.tomcat.threads.max" to "1000")
        )
    }
}