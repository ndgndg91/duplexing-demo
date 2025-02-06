package com.ndgndg91.practice.service.subscriber

import com.ndgndg91.practice.service.VendorManager
import io.github.resilience4j.circuitbreaker.CircuitBreaker
import org.slf4j.LoggerFactory
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener

class SwapMessageSubscriber(
    private val vendorManager: VendorManager,
    private val circuitBreaker: CircuitBreaker,
): MessageListener {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun onMessage(message: Message, pattern: ByteArray?) {
        logger.info("Received message {}", message)
        logger.info("previous primary : {} \t secondary : {}", vendorManager.primary(), vendorManager.secondary())
        logger.info("previous metrics : {}", circuitBreaker.metrics.toString())
        logger.info("previous circuit breaker status : {}", circuitBreaker.state)
        circuitBreaker.transitionToClosedState()
        vendorManager.swap()
        logger.info("swap vendor and circuit close complete")
        logger.info("next primary : {} \t secondary : {}", vendorManager.primary(), vendorManager.secondary())
        logger.info("next circuit breaker status : {}", circuitBreaker.state)
    }
}