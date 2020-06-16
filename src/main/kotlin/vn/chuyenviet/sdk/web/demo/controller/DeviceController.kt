package vn.chuyenviet.sdk.web.demo.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class DeviceController:ControllerBase() {
    @RequestMapping(value = ["search_device.html"])
    fun searchDevice(model:Model):String{
        this.footerJs.add("/webjars/sockjs-client/sockjs.min.js")
        this.footerJs.add("/webjars/stomp-websocket/stomp.min.js")
        this.footerJs.add("/js/loading.js")
        this.footerJs.add("/js/device.js")
        return contentPage("search_device_page",model)
    }
    @RequestMapping(value = ["event_device.html"])
    fun eventDevice(model:Model):String{
        this.footerJs.add("/webjars/sockjs-client/sockjs.min.js")
        this.footerJs.add("/webjars/stomp-websocket/stomp.min.js")
        this.footerJs.add("/js/loading.js")
        this.footerJs.add("/js/event_device.js")
        return contentPage("search_device_page",model)
    }
    @RequestMapping(value = ["connect_devices.html"])
    fun connectDevices(model:Model):String{
        this.footerJs.add("/webjars/sockjs-client/sockjs.min.js")
        this.footerJs.add("/webjars/stomp-websocket/stomp.min.js")
        this.footerJs.add("/js/loading.js")
        this.footerJs.add("/js/connect_devices.js")
        return contentPage("connect_devices",model)
    }
}