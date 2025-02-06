package com.ndgndg91.practice.config.resilience4j

import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import java.time.Duration

@Configuration
class Resilience4JConfig {

    companion object {
        const val SMS_CIRCUIT_BREAKER = "sms-circuit-breaker"
    }

    @Component
    @ConfigurationProperties(prefix = "ndgndg91.resilience4j.circuitbreaker")
    class CircuitBreakerProperties {
        var enabled: Boolean = true
        var failureRateThreshold: Float = 50f // 실패율 임계값(백분율)
        var slowCallRateThreshold: Float = 100f // 느린 호출 비율 임계값(백분율)
        var slowCallDurationThreshold: Long = 3_000L // 느린 호출 지속 시간 임계값(ms)
        var permittedNumberOfCallsInHalfOpenState: Int = 10 // half-open 상태에서 허용되는 호출 수
        var maxWaitDurationInHalfOpenState: Long = 5_000 // half-open 상태에서 최대 대기 시간(ms)
        var slidingWindowType: CircuitBreakerConfig.SlidingWindowType = CircuitBreakerConfig.SlidingWindowType.COUNT_BASED
        var slidingWindowSize: Int = 100
        var minimumNumberOfCalls: Int = 100
        var waitDurationInOpenState: Long = 30_000L // open 상태에서 대기 시간(ms)
        var automaticTransitionFromOpenToHalfOpenEnabled: Boolean = false // open 상태에서 half-open 상태로 자동 전환 여부
    }

    @Bean
    fun circuitBreaker(circuitBreakerConfig: CircuitBreakerConfig): CircuitBreaker {
        return CircuitBreaker.of(SMS_CIRCUIT_BREAKER, circuitBreakerConfig)
    }

    @Bean
    fun smsCircuitBreakerConfig(properties: CircuitBreakerProperties): CircuitBreakerConfig? {
        if (!properties.enabled) {
            return null
        }

        return CircuitBreakerConfig.custom()
            .failureRateThreshold(properties.failureRateThreshold)
            .slowCallRateThreshold(properties.slowCallRateThreshold)
            .slowCallDurationThreshold(Duration.ofMillis(properties.slowCallDurationThreshold))
            .permittedNumberOfCallsInHalfOpenState(properties.permittedNumberOfCallsInHalfOpenState)
            .maxWaitDurationInHalfOpenState(Duration.ofMillis(properties.maxWaitDurationInHalfOpenState))
            .slidingWindowType(properties.slidingWindowType)
            .slidingWindowSize(properties.slidingWindowSize)
            .minimumNumberOfCalls(properties.minimumNumberOfCalls)
            .waitDurationInOpenState(Duration.ofMillis(properties.waitDurationInOpenState))
            .automaticTransitionFromOpenToHalfOpenEnabled(properties.automaticTransitionFromOpenToHalfOpenEnabled)
            .build()
    }
}