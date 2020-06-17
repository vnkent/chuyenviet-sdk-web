package vn.chuyenviet.sdk.web.demo.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import vn.chuyenviet.sdk.web.demo.model.AccountLogin
import vn.chuyenviet.sdk.web.demo.service.impl.AccountServiceImpl

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

    @RequestMapping(value = ["/user/{id}"])
    fun edit(model:Model, @ModelAttribute("id") id: String):String{
        createDefault()
        this.footerJs.add("/js/user.js")
        var user = accountService.findByUsername(id)
        model.addAttribute("user", user)
        return contentPage("user_detail_page", model)
    }


}