package com.ndgndg91.practice.service.model

data class PhoneNumber(
    val countryCode: Int,
    val number: Long
) {
    fun string(): String {
        return "+$countryCode$number"
    }
}