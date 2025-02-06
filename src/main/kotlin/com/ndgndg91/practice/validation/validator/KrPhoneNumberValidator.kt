package com.ndgndg91.practice.validation.validator

import com.ndgndg91.practice.validation.annotation.ValidKrPhoneNumber
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class KrPhoneNumberValidator: ConstraintValidator<ValidKrPhoneNumber, String?> {
    private val pattern = """^(\+82)?(0)?(10|11|16|17|18|19)(-?\d{3,4}-?\d{4}|\d{7,8})$""".toRegex()
    override fun isValid(value: String?, context: ConstraintValidatorContext): Boolean {
        if (value == null) return addConstraint(context)
        return if (pattern.matches(value)) {
            true
        } else {
            addConstraint(context)
        }
    }

    private fun addConstraint(context: ConstraintValidatorContext): Boolean {
        context.disableDefaultConstraintViolation()
        context
            .buildConstraintViolationWithTemplate("")
            .addConstraintViolation()
        return false
    }
}