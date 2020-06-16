package vn.chuyenviet.sdk.web.demo.controller.socket

import org.springframework.messaging.simp.stomp.StompClientSupport

class Greeting {
    var content: String = "Greeting"

    constructor(content:String){

        this.content = content
    }
}