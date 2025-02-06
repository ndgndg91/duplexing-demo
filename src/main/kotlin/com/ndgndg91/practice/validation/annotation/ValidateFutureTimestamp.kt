package com.ndgndg91.practice.validation.annotation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import com.ndgndg91.practice.validation.validator.ValidateFutureTimestampValidator
import kotlin.reflect.KClass


@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [ValidateFutureTimestampValidator::class])
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@MustBeDocumented
annotation class ValidateFutureTimestamp(
    val message:String = "300000",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)
