package vn.chuyenviet.sdk.web.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration

@SpringBootApplication
@Configuration
class DemoApplication

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}
