package vn.chuyenviet.sdk.web.demo.controller

import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import vn.chuyenviet.sdk.web.demo.dto.AccountDTO
import vn.chuyenviet.sdk.web.demo.dto.SuccessMessage
import vn.chuyenviet.sdk.web.demo.enums.MessageStatus
import vn.chuyenviet.sdk.web.demo.model.Account
import vn.chuyenviet.sdk.web.demo.service.impl.AccountServiceImpl
import vn.chuyenviet.sdk.web.demo.utils.AppConfig
import vn.chuyenviet.sdk.web.demo.utils.ApplicationMessage

@Controller
class UserController : ControllerBase() {

    @Autowired
    lateinit var accountService: AccountServiceImpl

    @RequestMapping(value = ["/users"])
    fun users(model: Model): String {
        createDefault()
        var users = accountService.findAll()
        model.addAttribute("users", users)
        return contentPage("list_user_page", model)
    }

    @RequestMapping(value = ["/user/{userID}/{type}"])
    fun edit(model: Model, @ModelAttribute("userID") userID: String, @ModelAttribute("type") type: Int): String {
        this.footerJs.add("/../webjars/sockjs-client/sockjs.min.js")
        this.footerJs.add("/../webjars/stomp-websocket/stomp.min.js")
        this.footerJs.add("/../js/loading.js")
        this.footerJs.add("/../js/base.js")
        this.footerJs.add("/../js/user.js")
        model.addAttribute("userID", userID)
        model.addAttribute("type", type)
        return contentPage("user_detail_page", model)
    }

    @RequestMapping(value = ["/event_device"])
    fun event(model: Model): String {
        createDefault()
        this.footerJs.add("/js/event.js")
        return contentPage("event", model)
    }

    @RequestMapping(value = ["/check_event/{deviceID}"])
    fun edit(model: Model, @PathVariable("deviceID") deviceID: String): String {
        model.addAttribute("deviceID", deviceID)
        return "/check"
    }

}