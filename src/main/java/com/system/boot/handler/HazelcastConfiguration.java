package com.system.boot.handler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;

@Configuration
public class HazelcastConfiguration {
	@Bean
	public Config hazelcastConfig() {
		return new Config().setInstanceName("hazelcast-instance").addMapConfig(new MapConfig().setName("instruments").setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE)).setEvictionPolicy(EvictionPolicy.LRU).setTimeToLiveSeconds(20));
	}
}
