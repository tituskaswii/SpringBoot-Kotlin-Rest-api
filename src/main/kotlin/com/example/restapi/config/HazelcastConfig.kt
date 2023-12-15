package com.example.restapi.config

import com.hazelcast.config.Config
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class HazelcastConfig {

    @Bean
    fun hazelcastInstance(): HazelcastInstance {
        val config = Config()
        // Configure Hazelcast instance as needed
        return Hazelcast.newHazelcastInstance(config)
    }
}
