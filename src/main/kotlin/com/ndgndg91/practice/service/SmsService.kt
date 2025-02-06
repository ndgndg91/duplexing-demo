package com.ndgndg91.practice.service

import com.ndgndg91.practice.config.resilience4j.Resilience4JConfig.Companion.SMS_CIRCUIT_BREAKER
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.stereotype.Service

@Service
class SmsService(
    private val selector: VendorSelector
){
    @CircuitBreaker(name = SMS_CIRCUIT_BREAKER, fallbackMethod = "sendBySecondary")
    fun send(command: SendSmsCommand) {
        selector.primary().send()
    }

    fun sendBySecondary() {
        selector.secondary().send()
    }
}