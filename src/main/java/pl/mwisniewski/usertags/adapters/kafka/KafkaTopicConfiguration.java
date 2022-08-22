package pl.mwisniewski.usertags.adapters.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {

    @Bean
    public NewTopic userTagTopic() {
        return TopicBuilder
                .name("user-tag")
                .config(TopicConfig.RETENTION_MS_CONFIG, ONE_DAY_MS.toString())
                .build();
    }

    private static final Long ONE_DAY_MS = 24L * 60L * 60L * 1000L;
}
