package com.moview

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MoviewApplication

fun main(args: Array<String>) {
	runApplication<MoviewApplication>(*args)
}
