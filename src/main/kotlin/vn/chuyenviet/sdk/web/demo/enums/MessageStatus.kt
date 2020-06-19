package vn.chuyenviet.sdk.web.demo.enums

enum class MessageStatus(var index: String, var message: String) {
    SUCCESS("200", "success"), ERROR("100", ""), UNAUTHORIZED("401", "unauthorized");

}