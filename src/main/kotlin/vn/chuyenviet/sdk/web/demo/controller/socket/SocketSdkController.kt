package vn.chuyenviet.sdk.web.demo.controller.socket

import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import org.springframework.web.util.HtmlUtils

@Controller
class SocketSdkController {

    @MessageMapping("/search_device")
    @SendTo("/topic/sdk_search_device")
    @Throws(Exception::class)
    fun searchDevice(): Greeting {
        return Greeting("Hello,search device ")
    }

    @MessageMapping("/result_search_device")
    @SendTo("/topic/device_search_result")
    @Throws(Exception::class)
    fun searchResult(message: HelloMessage): Greeting? {
        return Greeting("Hello, " + HtmlUtils.htmlEscape(message.name) + "!")
    }

    @MessageMapping("/event_device")
    @SendTo("/topic/sdk_event_device")
    @Throws(Exception::class)
    fun eventDevice(): Greeting? {
        return Greeting("Hello,event device ")
    }

    @MessageMapping("/sdk_event_device")
    @SendTo("/topic/device_event_result")
    @Throws(Exception::class)
    fun eventResult(message: HelloMessage): Greeting? {
        return Greeting("Hello, " + HtmlUtils.htmlEscape(message.name) + "!")
    }
    @MessageMapping("/sdkCommand")
    @SendTo("/topic/sdkCommand")
    @Throws(Exception::class)
    fun sdkCommand(sdkCommand: SdkCommand):SdkCommand{
        return sdkCommand
    }
    @MessageMapping("/sdkCommandResult")
    @SendTo("/topic/sdkCommandResult")
    @Throws(Exception::class)
    fun sdkCommandResult(sdkResult: SdkResult):SdkResult{
        return sdkResult
    }
}