package vn.chuyenviet.sdk.web.demo.model

import javax.persistence.*

@Entity
@Table(name = "account")
class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Int = 0
    lateinit var username: String
    lateinit var password: String
    lateinit var name: String
    var address: String = ""
    var image: String = ""
}