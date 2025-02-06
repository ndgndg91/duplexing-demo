package com.ndgndg91.practice.config.redis

import com.ndgndg91.practice.service.VendorManager
import com.ndgndg91.practice.service.subscriber.SwapMessageSubscriber
import io.github.resilience4j.circuitbreaker.CircuitBreaker
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter


@Configuration
class RedisConfig {
    @Bean
    fun topic(): ChannelTopic {
        return ChannelTopic("SWAP_PRIMARY")
    }

    @Bean
    fun messageListener(vendorManager: VendorManager, circuitBreaker: CircuitBreaker): MessageListenerAdapter {
        return MessageListenerAdapter(SwapMessageSubscriber(vendorManager, circuitBreaker))
    }

    @Bean
    fun redisContainer(messageListener: MessageListenerAdapter, connectionFactory: LettuceConnectionFactory): RedisMessageListenerContainer {
        val container = RedisMessageListenerContainer()
        container.setConnectionFactory(connectionFactory)
        container.addMessageListener(messageListener, topic())
        return container
    }
}