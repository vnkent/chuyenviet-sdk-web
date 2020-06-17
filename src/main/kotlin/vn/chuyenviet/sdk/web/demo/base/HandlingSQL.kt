package vn.chuyenviet.sdk.web.demo.base

import com.google.gson.Gson
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.Query
import javax.transaction.Transactional

open class HandlingSQL {
    lateinit var entityManager: EntityManager

    constructor(entityManager: EntityManager) {
        this.entityManager = entityManager
    }

    public fun executeSQL(sql: String): List<Array<Any>?>? {
        try {
            var query: Query = entityManager!!.createNativeQuery(sql)
            return query.resultList as List<Array<Any>>
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    @Transactional
    open fun updateSQL(sql: String): Boolean {
        try {
            var query: Query = entityManager!!.createNativeQuery(sql)
            query.executeUpdate()
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return true
    }

    fun findOne(sql: String): Array<Any>? {
        var query: Query = entityManager!!.createNativeQuery(sql)
        val lists: List<Array<Any>?> = query.resultList as List<Array<Any>?>
        return if (lists.size > 0) {
            return lists[0]
        } else null
    }
}