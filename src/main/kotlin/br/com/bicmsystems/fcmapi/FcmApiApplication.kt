package br.com.bicmsystems.fcmapi

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@EnableAutoConfiguration
@ComponentScan

open class FcmApiApplication

fun main(args: Array<String>) {
	runApplication<FcmApiApplication>(*args)
}

