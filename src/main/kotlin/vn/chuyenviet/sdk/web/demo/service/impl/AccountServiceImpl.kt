package vn.chuyenviet.sdk.web.demo.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.Modifying
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import vn.chuyenviet.sdk.web.demo.base.HandlingSQL
import vn.chuyenviet.sdk.web.demo.model.Account
import vn.chuyenviet.sdk.web.demo.dto.AccountDTO
import vn.chuyenviet.sdk.web.demo.dto.AccountLogin
import vn.chuyenviet.sdk.web.demo.utils.ApplicationMessage
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.Query

@Service
class AccountServiceImpl {

    @PersistenceContext
    lateinit var entityManager: EntityManager

    @Autowired
    lateinit var passwordEncoder: BCryptPasswordEncoder

    var findByUsernameSQL = "SELECT id, username, password, name, address, image FROM account WHERE username = "
    var findByIdSQL = "SELECT id, username, password, name, address, image FROM account WHERE id = "
    var findAllSQL = "SELECT id, username, password, name, address, image FROM account "
    var saveSQL = "INSERT INTO account (username, password, name, address, image) VALUES (:username, :password, :name, :address, :image) "

    @Throws(Exception::class)
    @Transactional
    @Modifying
    fun save(account: Account): Boolean {
        val accountOld = findByUsername(account.username)
        if (accountOld != null) {
            throw Exception(ApplicationMessage.EMAIL_DOES_NOT_EXIST)
        }
        val password = passwordEncoder!!.encode(account.password)
        account.password = password
        return updateSQL(account)
    }

    fun findByUsername(username: String): AccountDTO? {
        val sb = StringBuilder(findByUsernameSQL)
        sb.append("'" + username + "'")
        val result = HandlingSQL(entityManager).findOne(sb.toString())
        if (result != null) {
            return convertToAccount(result)
        }
        return null
    }

    fun findById(id: Int): AccountDTO? {
        val result = HandlingSQL(entityManager).findOne(findByIdSQL + id)
        if (result != null) {
            return convertToAccount(result)
        }
        return null
    }

    fun findAll(): List<AccountDTO>? {
        val results = HandlingSQL(entityManager).executeSQL(findAllSQL)
        val lists = ArrayList<AccountDTO>()
        if (results != null) {
            for (item in results){
                lists.add(convertToAccount(item as Array<Any>))
            }
        }
        return lists
    }

    fun convertToAccount(item : Array<Any>): AccountDTO {
        var id:Int = item[0] as Int
        var username:String = item[1].toString()
        var password:String = item[2].toString()
        var name:String = item[3].toString()
        var address:String = item[4].toString()
        var image:String = item[5].toString()

        var account = AccountDTO()
        account.id = id
        account.username = username
        account.password = password
        account.name = name
        account.address = address
        account.image = image

        return account
    }

    fun login(dto: AccountLogin): AccountDTO? {
        var account = findByUsername(dto.username)
        if (account != null && !passwordEncoder!!.matches(dto.password, account.password)) {
            account = null
        }
        return account
    }

    @Transactional
    @Modifying
    fun updateSQL(account: Account): Boolean {
        try {
            var query:Query = entityManager!!.createNativeQuery(saveSQL)
            query.setParameter("username", account.username)
            query.setParameter("password", account.password)
            query.setParameter("name", account.name)
            query.setParameter("address", account.address)
            query.setParameter("image", account.image)
            query.executeUpdate()
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return true
    }
}