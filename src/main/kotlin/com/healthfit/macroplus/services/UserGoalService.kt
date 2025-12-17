package com.healthfit.macroplus.services

import com.healthfit.macroplus.enums.GoalType
import com.healthfit.macroplus.models.UserGoal
import com.healthfit.macroplus.repositories.UserGoalRepository
import com.healthfit.macroplus.repositories.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@Service
class UserGoalService(
	private val userGoalRepository: UserGoalRepository,
	private val userRepository: UserRepository
) {

	@Transactional
	fun addUserGoalToUser(
		userId: UUID,
		goalType: GoalType,
		targetCalories: Int,
		targetProteinGrams: BigDecimal,
		targetCarbsGrams: BigDecimal,
		targetFatsGrams: BigDecimal
	): UserGoal {

		val foundUser = userRepository.findById(userId)
			.orElseThrow { NoSuchElementException("User not found with ID: $userId") }

//		remove previous goal
		val currentActiveGoal = userGoalRepository.findByUserIdAndIsActiveTrue(userId)
		if (currentActiveGoal != null) {
			currentActiveGoal.isActive = false
			currentActiveGoal.endDate = LocalDate.now()
			userGoalRepository.save(currentActiveGoal)
		}

//		isActive true by default
		val newUserGoal = UserGoal(
			foundUser,
			goalType,
			targetCalories,
			targetProteinGrams,
			targetCarbsGrams,
			targetFatsGrams
		)

		return userGoalRepository.save(newUserGoal)
	}

	@Transactional
	fun getUserGoalByUserId(userId: UUID): UserGoal {
		return userGoalRepository.findByUserIdAndIsActiveTrue(userId)
			?: throw NoSuchElementException("No active goal found for user: $userId")
	}

	@Transactional
	open fun checkUserHasActiveGoal(userId: UUID): Boolean {
		return userGoalRepository.existsByUserIdAndIsActiveTrue(userId)
	}
}