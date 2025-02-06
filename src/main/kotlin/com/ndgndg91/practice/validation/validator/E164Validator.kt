package com.ndgndg91.practice.validation.validator

import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.ndgndg91.practice.validation.annotation.ValidE164
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class E164Validator: ConstraintValidator<ValidE164, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext): Boolean {
        requireNotNull(value) {
            return addConstraint(context)
        }

        val phoneNumberUtil = PhoneNumberUtil.getInstance()

        return try {
            val parsedNumber = phoneNumberUtil.parse(value, null)
            if (phoneNumberUtil.isValidNumber(parsedNumber) &&
                    phoneNumberUtil.getNumberType(parsedNumber) == PhoneNumberUtil.PhoneNumberType.MOBILE &&
                    phoneNumberUtil.isNumberMatch(parsedNumber, parsedNumber) == PhoneNumberUtil.MatchType.EXACT_MATCH) {
                return true
            } else {
                addConstraint(context)
            }
        } catch (e: NumberParseException) {
            // 파싱 오류가 발생하면 E.164 포맷이 아니라고 간주
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