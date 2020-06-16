package vn.chuyenviet.sdk.web.demo.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import vn.chuyenviet.sdk.web.demo.SdkSocket
import vn.chuyenviet.sdk.web.demo.model.Account
import vn.chuyenviet.sdk.web.demo.model.AccountLogin
import vn.chuyenviet.sdk.web.demo.service.impl.AccountServiceImpl
import vn.chuyenviet.sdk.web.demo.utils.ApplicationMessage
import javax.servlet.http.HttpServletRequest

@Controller
class HomeController :ControllerBase(){

    @Autowired
    lateinit var accountService: AccountServiceImpl

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

    @RequestMapping(path = ["/login"], method = [RequestMethod.POST])
    fun doLogin(request: HttpServletRequest, @RequestParam username: String, @RequestParam password: String): String {
        try {
            var dto = AccountLogin(username, password)
            var acc: Account = accountService.login(dto)
            request.session.setAttribute("username", acc.username)
            request.session.setAttribute("name", acc.name)
        } catch (e: Exception) {
            request.session.setAttribute("messageError", ApplicationMessage.LOGIN_FAIL)
            return "login"
        }
        return "redirect:/"
    }

    @RequestMapping( "/register", method = [RequestMethod.GET])
    fun showLoginPageRegister(): String {
        return "register.html"
    }

    @RequestMapping(path = ["/register"], method = [RequestMethod.POST])
    fun register(request: HttpServletRequest, @RequestParam username: String, @RequestParam password: String,
                 @RequestParam name:String, @RequestParam address:String): String {
        try {
            var dto = Account()
            dto.username = username
            dto.password = password;
            dto.name = name;
            dto.address = address;
            var accountOld: Account = accountService.findByUsername(username)
            if (accountOld != null) {
                accountService.save(dto)
                return "register"
            }
        } catch (e: Exception) {
            request.session.setAttribute("messageError", ApplicationMessage.REGISTER_FAIL)
        }

        return "redirect:/login"
    }
}