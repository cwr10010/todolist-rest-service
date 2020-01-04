package todo.db

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

/**
 * DB configuration class. It just reads db.yaml and provides it to the spring data context.
 */
@Configuration
@PropertySource(value = ["classpath:db.yaml"])
@EnableConfigurationProperties
class DbConfiguration