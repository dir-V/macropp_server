package com.healthfit.macroplus.config

import com.healthfit.macroplus.enums.GoalType
import com.healthfit.macroplus.models.*
import com.healthfit.macroplus.repositories.*
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Component
open class DataSeeder(
	private val userRepository: UserRepository,
	private val userGoalRepository: UserGoalRepository,
	private val foodRepository: FoodRepository,
	private val foodLogRepository: FoodLogRepository
) : CommandLineRunner {

	override fun run(vararg args: String?) {
		println("SEEDING: Starting database population...")

		// delete data - order matters
		foodLogRepository.deleteAll()
		foodRepository.deleteAll()
		userGoalRepository.deleteAll()
		userRepository.deleteAll()

		val user = User(
			email = "tom@example.com",
			heightCm = 175
		)
		val savedUser = userRepository.save(user)

		val goal = UserGoal(
			user = savedUser,
			goalType = GoalType.DEFICIT,
			targetCalories = 1800,
//			macros total 1800 cals
			targetProteinGrams = BigDecimal("130.00"),
			targetCarbsGrams = BigDecimal("185.00"),
			targetFatsGrams = BigDecimal("60.00")
		)
		userGoalRepository.save(goal)

		val chicken = Food(
			user = savedUser,
			name = "Chicken Breast",
			caloriesPer100Grams = 165,
			proteinPer100Grams = BigDecimal("31.00"),
			carbsPer100Grams = BigDecimal("0.00"),
			fatsPer100Grams = BigDecimal("3.60"),
			servingSizeGrams = BigDecimal("100.00"),
			barcode = 123456L
		)

		val rice = Food(
			user = savedUser,
			name = "White Rice",
			caloriesPer100Grams = 130,
			proteinPer100Grams = BigDecimal("2.70"),
			carbsPer100Grams = BigDecimal("28.00"),
			fatsPer100Grams = BigDecimal("0.30"),
			servingSizeGrams = BigDecimal("100.00"),
			barcode = null
		)

		val savedChicken = foodRepository.save(chicken)
		val savedRice = foodRepository.save(rice)


		val log1 = FoodLog(
			user = savedUser,
			food = savedChicken,
			name = "Lunch Chicken",
			quantityGrams = BigDecimal("200.00"),
			loggedAt = LocalDateTime.now().minusHours(4),
			calories = 330,
			proteinGrams = BigDecimal("62.00"),
			carbsGrams = BigDecimal("0.00"),
			fatsGrams = BigDecimal("7.20")
		)

		// Log 2: Quick Add (Snack)
		val log2 = FoodLog(
			user = savedUser,
			food = null,
			name = "Random Cookie",
			quantityGrams = null,
			loggedAt = LocalDateTime.now().minusHours(1),
			calories = 250,
			proteinGrams = BigDecimal.ZERO,
			carbsGrams = BigDecimal.ZERO,
			fatsGrams = BigDecimal.ZERO
		)

		foodLogRepository.save(log1)
		foodLogRepository.save(log2)

		println("SEEDING: Finished seeding 🤯)")
	}
}