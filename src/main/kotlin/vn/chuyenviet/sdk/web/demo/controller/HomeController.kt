package vn.chuyenviet.sdk.web.demo.controller

import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import vn.chuyenviet.sdk.web.demo.SdkSocket
import vn.chuyenviet.sdk.web.demo.model.Account
import vn.chuyenviet.sdk.web.demo.model.AccountDTO
import vn.chuyenviet.sdk.web.demo.model.AccountLogin
import vn.chuyenviet.sdk.web.demo.service.impl.AccountServiceImpl
import vn.chuyenviet.sdk.web.demo.utils.ApplicationMessage
import javax.servlet.http.HttpServletRequest

@Controller
class HomeController : ControllerBase() {

    @Autowired
    lateinit var accountService: AccountServiceImpl

    @Value("\${socket.address.ip}")
    val addressIP: String = "localhost"

    @Value("\${socket.port}")
    val port: Int = 8080

    @RequestMapping(value = ["/","index.html"])
    fun index(request: HttpServletRequest, model: Model): String {
        val username = request.session.getAttribute("username")
        if (username != null) {
            this.footerJs.add("/js/loading.js")
            this.footerJs.add("/js/base.js")
            return contentPage("blank",model)
        }
        return "redirect:/login"
    }

    @RequestMapping("/login", method = [RequestMethod.GET])
    fun showLoginPage(): String {
        return "login.html"
    }

    @RequestMapping("/logout", method = [RequestMethod.GET])
    fun logout(request: HttpServletRequest): String {
        request.session.setAttribute("username", "")
        return "/login"
    }

    @RequestMapping(path = ["/login"], method = [RequestMethod.POST])
    fun doLogin(request: HttpServletRequest, model: Model, @ModelAttribute("account") account: AccountLogin): String {
        try {
            var accountDTO: AccountDTO = accountService.login(account)
            if (accountDTO == null) {
                model.addAttribute("status", false)
                return "login"
            }
            request.session.setAttribute("username", account.username)
            model.addAttribute("status", true)
            model.addAttribute("account", accountDTO)
        } catch (e: Exception) {
            return "login"
        }
        return "login"
    }

    @RequestMapping( "/register", method = [RequestMethod.GET])
    fun showLoginPageRegister(): String {
        return "register.html"
    }

    @RequestMapping(value = ["/register"], method = [RequestMethod.POST])
    fun registerHandlerPOST(model: Model, @ModelAttribute("account") account: Account): String {
        var accountOld: AccountDTO? = accountService.findByUsername(account.username)
        if (accountOld == null) {
            if (!accountService.save(account)) {
                model.addAttribute("messageError", ApplicationMessage.REGISTER_FAIL)
                return "register"
            }
        }
        return "redirect:/login"
    }

}