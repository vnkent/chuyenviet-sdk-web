/**
 *
 */
package vn.chuyenviet.sdk.web.demo.dto

import com.fasterxml.jackson.annotation.JsonInclude
import javax.xml.bind.annotation.XmlRootElement

/**
 *
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@XmlRootElement
class SuccessMessage {
    var code: String? = null
    var message: String? = null
    var data: String? = null

    constructor(code: String?, message: String?, data: String?) : super() {
        this.code = code
        this.message = message
        this.data = data
    }

    constructor(code: String?) : super() {
        this.code = code
    }

    constructor() : super() {}

}