package vn.chuyenviet.sdk.web.demo.api

import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import vn.chuyenviet.sdk.web.demo.controller.ControllerBase
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
class APIController : ControllerBase() {

    @Autowired
    lateinit var accountService: AccountServiceImpl

    @ResponseBody
    @RequestMapping(value = ["/public/login"], method = [RequestMethod.POST], produces = [AppConfig.APPLICATION_CONSUMES])
    fun login(request: HttpServletRequest, @RequestBody account: AccountLogin): ResponseEntity<SuccessMessage?>? {
        var successMessage = SuccessMessage()
        var accountDTO: AccountDTO? = accountService.login(account)
        if (accountDTO == null) {
            successMessage.code = MessageStatus.ERROR.index
            successMessage.message = ApplicationMessage.LOGIN_FAIL
        } else {
            successMessage.code = MessageStatus.SUCCESS.index
            successMessage.message = ApplicationMessage.SUCCESS
            successMessage.data = Gson().toJson(accountDTO)
            request.session.setAttribute("username", account.username)
        }
        return ResponseEntity<SuccessMessage?>(successMessage, HttpStatus.OK)
    }

    @ResponseBody
    @RequestMapping(value = ["/public/logout"], method = [RequestMethod.POST], produces = [AppConfig.APPLICATION_CONSUMES])
    fun login(request: HttpServletRequest): ResponseEntity<SuccessMessage?>? {
        var successMessage = SuccessMessage()
        successMessage.code = MessageStatus.SUCCESS.index
        successMessage.message = ApplicationMessage.SUCCESS
        request.session.setAttribute("username", "")
        return ResponseEntity<SuccessMessage?>(successMessage, HttpStatus.OK)
    }

    @ResponseBody
    @RequestMapping(value = ["/public/register"], method = [RequestMethod.POST], produces = [AppConfig.APPLICATION_CONSUMES])
    fun register(@RequestBody account: Account): ResponseEntity<SuccessMessage?>? {
        var successMessage = SuccessMessage()
        var accountOld: AccountDTO? = accountService.findByUsername(account.username)
        if (accountOld == null) {
            if (!accountService.save(account)) {
                successMessage.code = MessageStatus.SUCCESS.index
                successMessage.message = MessageStatus.SUCCESS.message
            }
        } else {
            successMessage.code = MessageStatus.ERROR.index
            successMessage.message = ApplicationMessage.REGISTER_FAIL
        }
        return ResponseEntity<SuccessMessage?>(successMessage, HttpStatus.OK)
    }

    @ResponseBody
    @RequestMapping(value = ["public/check_event/{selectUserID}"], method = [RequestMethod.GET], produces = [AppConfig.APPLICATION_CONSUMES])
    fun check(@PathVariable selectUserID: String): ResponseEntity<SuccessMessage?>? {
        var successMessage = SuccessMessage()
        if (selectUserID == "") {
            successMessage.code = MessageStatus.ERROR.index
            successMessage.message = ApplicationMessage.DUPLICATE_EMAIL
        }
        var userID = selectUserID.toInt()
        var accountOld: AccountDTO? = accountService.findById(userID)
        if (accountOld != null) {
            successMessage.code = MessageStatus.SUCCESS.index
            successMessage.message = MessageStatus.SUCCESS.message
            successMessage.data = Gson().toJson(accountOld)
        } else {
            successMessage.code = MessageStatus.ERROR.index
            successMessage.message = ApplicationMessage.ERROR
        }
        return ResponseEntity<SuccessMessage?>(successMessage, HttpStatus.OK)
    }

}