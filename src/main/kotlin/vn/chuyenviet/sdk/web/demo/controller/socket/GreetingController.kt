package vn.chuyenviet.sdk.web.demo.controller.socket

import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import org.springframework.web.util.HtmlUtils

@Controller
class GreetingController {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    @Throws(Exception::class)
    fun greeting(message: HelloMessage): Greeting? {
        Thread.sleep(1000) // simulated delay
        return Greeting("Hello, " + HtmlUtils.htmlEscape(message.name) + "!")
    }
    @MessageMapping("/deviceEvent/{driverId}")
    @SendTo("/topic/deviceEvent/{driverId}")
    @Throws(Exception::class)
    fun deviceEvent(message: HelloMessage, @DestinationVariable  driverId:String):Greeting{
        return Greeting("Device Event"+HtmlUtils.htmlEscape(message.name) + "!")
    }
}