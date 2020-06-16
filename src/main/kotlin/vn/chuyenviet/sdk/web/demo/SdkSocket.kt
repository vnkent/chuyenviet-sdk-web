package vn.chuyenviet.sdk.web.demo

import com.google.gson.Gson
import vn.chuyenviet.sdk.web.demo.controller.SdkApiRequest
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class SdkSocket(var address_id: String, var port: Int) {
    var outPut: PrintWriter? = null
    var inPut: BufferedReader? = null
    lateinit var socket: Socket
    var hasConnect = false
    var dataReceivedString:String = ""

    init {
        try {
            this.socket = Socket(address_id, port)
            inPut = BufferedReader(InputStreamReader(socket.getInputStream()))
            outPut = PrintWriter(socket.getOutputStream(), true)
            hasConnect = true
        } catch (e: Exception) {
        }
    }
    @Synchronized
    private fun stop(){
        try {
            if (socket != null)
                socket!!.close()
            if (inPut != null)
                inPut!!.close()
            if (outPut != null)
                outPut!!.close()
        }catch (e:Exception){

        }
    }
    fun requestSdk(dataRequest: SdkApiRequest): SdkSocket {
        if (!hasConnect)return this
        try{
            if (outPut == null) {
                outPut = PrintWriter(socket.getOutputStream(), true)
            }
            outPut!!.println(Gson().toJson(dataRequest))
            try {
                dataReceivedString = inPut!!.readLine()
                System.out.println("data:"+dataReceivedString)
            }catch (e:Exception){

            }


        }catch (e:Exception){

        }
        stop()
        return this
    }
}