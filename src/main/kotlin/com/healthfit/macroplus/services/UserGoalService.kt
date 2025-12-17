package com.healthfit.macroplus.services

import com.healthfit.macroplus.enums.GoalType
import com.healthfit.macroplus.models.UserGoal
import com.healthfit.macroplus.repositories.UserGoalRepository
import com.healthfit.macroplus.repositories.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

@Service
open class UserGoalService(
	private val userGoalRepository: UserGoalRepository,
	private val userRepository: UserRepository
) {

	@Transactional
	open fun addUserGoalToUser(
		userId: UUID,
		goalType: GoalType,
		targetCalories: Int,
		targetProteinGrams: BigDecimal,
		targetCarbsGrams: BigDecimal,
		targetFatsGrams: BigDecimal
		) : UserGoal {

		val foundUser = userRepository.findById(userId)
			.orElseThrow { NoSuchElementException("User not found with ID: $userId") }

		val newUserGoal: UserGoal = UserGoal(
			foundUser,
			goalType,
			targetCalories,
			targetProteinGrams,
			targetCarbsGrams,
			targetFatsGrams
		)

		return userGoalRepository.save(newUserGoal)
	}
}