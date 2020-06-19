package vn.chuyenviet.sdk.web.demo.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import vn.chuyenviet.sdk.web.demo.model.Account
import vn.chuyenviet.sdk.web.demo.dto.AccountDTO
import vn.chuyenviet.sdk.web.demo.dto.AccountLogin
import vn.chuyenviet.sdk.web.demo.dto.SuccessMessage
import vn.chuyenviet.sdk.web.demo.enums.MessageStatus
import vn.chuyenviet.sdk.web.demo.service.impl.AccountServiceImpl
import vn.chuyenviet.sdk.web.demo.utils.AppConfig
import vn.chuyenviet.sdk.web.demo.utils.ApplicationMessage
import javax.servlet.http.HttpServletRequest

@Controller
class HomeController : ControllerBase() {

    @Autowired
    lateinit var accountService: AccountServiceImpl

    @RequestMapping(value = ["/","index.html"])
    fun index(request: HttpServletRequest, model: Model): String {
        var username = request.session.getAttribute("username")
        if (username != null) {
            createDefault()
            return contentPage("blank",model)
        }
        return "redirect:/login"
    }

    @RequestMapping("/login", method = [RequestMethod.GET])
    fun showLoginPage(): String {
        return "login.html"
    }

    @RequestMapping( "/register", method = [RequestMethod.GET])
    fun showLoginPageRegister(): String {
        return "register.html"
    }

}