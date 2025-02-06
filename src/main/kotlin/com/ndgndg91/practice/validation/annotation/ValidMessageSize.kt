package com.ndgndg91.practice.validation.annotation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import com.ndgndg91.practice.validation.validator.MessageSizeValidator
import kotlin.reflect.KClass


@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [MessageSizeValidator::class])
@MustBeDocumented
annotation class ValidMessageSize(
    val message: String = "300000",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val maxBytes: Int,
    val nullable: Boolean = false
)