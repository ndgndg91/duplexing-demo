package com.ndgndg91.practice.validation.validator

import com.ndgndg91.practice.validation.annotation.ValidateFutureTimestamp
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.time.LocalDateTime.now
import java.time.ZoneOffset

class ValidateFutureTimestampValidator : DefaultValidator, ConstraintValidator<ValidateFutureTimestamp, Long?> {
    override fun isValid(value: Long?, context: ConstraintValidatorContext): Boolean {
        if (value == null) {
            return true
        }

        val now = now().toEpochSecond(ZoneOffset.UTC)
        val after60Days = now().plusDays(60).toEpochSecond(ZoneOffset.UTC)
        if (value <= now || value > after60Days) {
            return addConstraint(context, "")
        }

        return true
    }

}