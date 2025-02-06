package com.ndgndg91.practice.validation.validator

import jakarta.validation.ConstraintValidatorContext

interface DefaultValidator {
    fun addConstraint(context: ConstraintValidatorContext, errorCode: String): Boolean {
        context.disableDefaultConstraintViolation()
        context.buildConstraintViolationWithTemplate(errorCode)
            .addConstraintViolation()

        return false
    }
}