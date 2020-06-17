package vn.chuyenviet.sdk.web.demo.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class DeviceController:ControllerBase() {
    @RequestMapping(value = ["/search_device.html","/search_device"])
    fun searchDevice(model:Model):String{
        createDefault()
        this.footerJs.add("/js/device.js")
        return contentPage("search_device_page",model)
    }
    @RequestMapping(value = ["event_device.html"])
    fun eventDevice(model:Model):String{
        createDefault()
        this.footerJs.add("/js/event_device.js")
        return contentPage("search_device_page",model)
    }
    @RequestMapping(value = ["/connect_devices.html", "/connect_device"])
    fun connectDevices(model:Model):String{
        createDefault()
        this.footerJs.add("/js/connect_devices.js")
        return contentPage("connect_devices",model)
    }
}