package com.healthfit.macroplus.models

import com.healthfit.macroplus.models.Food
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UuidGenerator
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "food_logs")
open class FoodLog(
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	var user: User,

	@ManyToOne
	@JoinColumn(name = "food_id", nullable = false)
	var food: Food,

	@Column(name = "quantity_g", nullable = false)
	var quantityGrams: BigDecimal,

	@Column(name = "logged_at", nullable = false)
	var loggedAt: LocalDateTime? = null,

) {

	protected constructor() : this(User(), Food(), BigDecimal.ZERO, null)


	@Id
	@GeneratedValue
	@UuidGenerator
	@Column(name = "id", updatable = false, nullable = false)
	var id: UUID? = null

	@Column(name = "calories" , nullable = false)
	var calories: Int? = null

	@Column(name = "protein_g" , nullable = false)
	var proteinGrams: BigDecimal? = null


	@Column(name = "carbs_g" , nullable = false)
	var carbsGrams: BigDecimal? = null


	@Column(name = "fats_g" , nullable = false)
	var fatsGrams: BigDecimal? = null


	@CreationTimestamp
	@Column(name = "created_at", updatable = false, nullable = false)
	var createdAt: LocalDateTime? = null

}