package com.example.restapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@EnableCaching
class RestAPIApplication

	fun main(args: Array<String>) {
		runApplication<RestAPIApplication>(*args)
	}
