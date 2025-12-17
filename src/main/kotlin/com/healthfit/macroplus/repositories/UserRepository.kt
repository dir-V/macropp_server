package com.healthfit.macroplus.repositories

import com.healthfit.macroplus.models.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository : JpaRepository<User, UUID> {
	fun findByEmail(email: String): User?

//	returns Boolean for if the email exists
	fun existsByEmail(email: String): Boolean
}