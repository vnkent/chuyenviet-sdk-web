package vn.chuyenviet.sdk.web.demo.controller

import org.springframework.ui.Model;

abstract class ControllerBase() {
    var footerJs = ArrayList<String>()
    var headerJs = ArrayList<String>()
    var listCss = ArrayList<String>()
    fun viewName(htmlName: String): String {
        if (htmlName.isEmpty()) {
            return htmlName
        }
        return pathLayout() + "/" + htmlName;
    }

    fun pathLayout(): String {
        return ""
    }

    fun contentPage(contentHtmlName: String, model: Model,containerPage:String="fixed_sidebar"): String {

        if (footerJs.size>0){
            var listFooterJs = ArrayList<String>()
            listFooterJs.addAll(footerJs)
            model.addAttribute("footerJs",listFooterJs)
        }

        if (headerJs.size>0){
            var listHeaderJs = ArrayList<String>()
            listHeaderJs.addAll(headerJs)
            model.addAttribute("headerJs",listHeaderJs)
        }
        if (listCss.size>0){
            var listcss = ArrayList<String>()
            listcss.addAll(listCss)
            model.addAttribute("listCss",listcss)
        }
        listCss.clear()
        headerJs.clear()
        footerJs.clear()
        model.addAttribute("contentPage","fragments/"+contentHtmlName);
        return viewName(containerPage);
    }

    fun createDefault() {
        this.footerJs.add("/webjars/sockjs-client/sockjs.min.js")
        this.footerJs.add("/webjars/stomp-websocket/stomp.min.js")
        this.footerJs.add("/js/loading.js")
        this.footerJs.add("/js/base.js")
    }
}