/*
 * Copyright (c) 2016, the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * License for more details.
 */

package name.abhijitsarkar.feign.persistence;

import name.abhijitsarkar.feign.persistence.service.MongoDbRecordingService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Abhijit Sarkar
 */
@Configuration
@ComponentScan
public class FeignPersistenceAutoConfiguration {
    @Bean
    @ConditionalOnProperty(prefix = "feign.recording", name = "disable",
            havingValue = "false", matchIfMissing = true)
    MongoDbRecordingService recordingService() {
        return new MongoDbRecordingService();
    }
}