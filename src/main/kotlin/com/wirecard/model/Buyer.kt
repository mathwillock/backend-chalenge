package com.wirecard.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "wirecard_buyer")
data class Buyer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    var name: String,
    var email: String,
    val cpf: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Buyer

        if (id != other.id) return false
        if (name != other.name) return false
        if (email != other.email) return false
        if (cpf != other.cpf) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + cpf.hashCode()
        return result
    }

    override fun toString(): String {
        return "Buyer(id=$id, name='$name', email='$email', cpf='$cpf')"
    }


}