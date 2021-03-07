package ch.keepcalm.demo.kofu

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KofuDemoApplication

fun main(args: Array<String>) {
	runApplication<KofuDemoApplication>(*args)
}
