package com.ndgndg91.practice.validation.validator

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import com.ndgndg91.practice.validation.annotation.ValidMessageSize

class MessageSizeValidator : ConstraintValidator<ValidMessageSize, String?> {
    private var maxBytes: Int = 256
    private var nullable: Boolean = false

    override fun initialize(constraintAnnotation: ValidMessageSize) {
        maxBytes = constraintAnnotation.maxBytes
        nullable = constraintAnnotation.nullable
    }

    override fun isValid(value: String?, context: ConstraintValidatorContext): Boolean {
        if (nullable && value == null) {
            return true
        }

        val utf8Bytes = value!!.toByteArray(Charsets.UTF_8)

        return if (utf8Bytes.size <= maxBytes) {
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