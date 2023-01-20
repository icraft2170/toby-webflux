package me.hero.tobywebflux.r2dbc

import io.r2dbc.spi.ConnectionFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator

@SpringBootApplication
class ReactiveToDataBaseConnectionApplication {
    @Bean
    fun nettyReactiveWebServerFactory(): NettyReactiveWebServerFactory = NettyReactiveWebServerFactory()

    @Bean
    fun init(connectionFactory: ConnectionFactory) =
        ConnectionFactoryInitializer().apply {
            setConnectionFactory(connectionFactory)
            setDatabasePopulator(ResourceDatabasePopulator(ClassPathResource("schema/schema.sql")))
        }

}

fun main(args: Array<String>) {
    runApplication<ReactiveToDataBaseConnectionApplication>(*args)
}
