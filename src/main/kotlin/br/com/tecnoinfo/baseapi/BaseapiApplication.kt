package br.com.tecnoinfo.baseapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.data.web.config.EnableSpringDataWebSupport
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableFeignClients
@EnableAsync
class BaseapiApplication

fun main(args: Array<String>) {
    runApplication<BaseapiApplication>(*args)
}
