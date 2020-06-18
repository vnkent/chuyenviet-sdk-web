package vn.chuyenviet.sdk.web.demo.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import vn.chuyenviet.sdk.web.demo.model.Account
import vn.chuyenviet.sdk.web.demo.model.AccountDTO
import vn.chuyenviet.sdk.web.demo.model.AccountLogin
import vn.chuyenviet.sdk.web.demo.service.impl.AccountServiceImpl
import vn.chuyenviet.sdk.web.demo.utils.ApplicationMessage
import javax.servlet.http.HttpServletRequest

@Controller
class UserController:ControllerBase() {

    @Autowired
    lateinit var accountService: AccountServiceImpl

    @RequestMapping(value = ["/users"])
    fun users(model:Model):String{
        createDefault()
        var users = accountService.findAll()
        model.addAttribute("users", users)
        return contentPage("list_user_page", model)
    }

    @RequestMapping(value = ["/user/{userID}/{type}"])
    fun edit(model:Model, @ModelAttribute("userID") userID: String, @ModelAttribute("type") type: Int):String{
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
    fun event(model:Model):String{
        createDefault()
        this.footerJs.add("/js/event.js")
        return contentPage("event", model)
    }

    @RequestMapping(value = ["/event_device/{deviceID}"])
    fun event(@PathVariable("deviceID") deviceID: String):String{
        return "forward:/check_event/" + deviceID
    }

    @RequestMapping(value = ["/check_event/{deviceID}"])
    fun edit(model:Model, @PathVariable("deviceID") deviceID: String):String{
        model.addAttribute("deviceID", deviceID)
        return "check.html"
    }

//    @RequestMapping(value = ["/check/{deviceID}"], method = [RequestMethod.POST])
//    fun registerHandlerPOST(model: Model, @PathVariable("deviceID") deviceID: Int, @ModelAttribute("selectUserID") selectUserID: Int): String {
//        var accountOld: AccountDTO? = accountService.findById(selectUserID)
//        if (accountOld == null) {
//            model.addAttribute("message", ApplicationMessage.ACCOUNT_NOT_FOUND)
//            model.addAttribute("class", "error")
//        } else {
//            model.addAttribute("message", ApplicationMessage.SUCCESS)
//            model.addAttribute("class", "success")
//        }
//        return "/check/" + deviceID
//    }

}