package vn.chuyenviet.sdk.web.demo.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import vn.chuyenviet.sdk.web.demo.SdkSocket
import javax.servlet.http.HttpServletRequest

@Controller
class HomeController :ControllerBase(){

    @Value("\${socket.address.ip}")
    val addressIP: String = "localhost"

    @Value("\${socket.port}")
    val port: Int = 8080

    @RequestMapping(value = ["/","index.html"])
    fun index(request: HttpServletRequest, model: Model): String {
//        val username = request.session.getAttribute("username")
//        if (username == null) {
//            return "redirect:/login"
//        }
//        model.addAttribute("username", username)
        var socket = SdkSocket(addressIP , port).requestSdk(SdkApiRequest("getconfig"))
        if (socket.dataReceivedString.isNotBlank()){
            return contentPage("blank",model)
        }else {
            return contentPage("blank",model)
        }
        return contentPage("blank",model)
    }

    @RequestMapping("/login", method = [RequestMethod.GET])
    fun showLoginPage(): String {
        return "login.html"
    }
}