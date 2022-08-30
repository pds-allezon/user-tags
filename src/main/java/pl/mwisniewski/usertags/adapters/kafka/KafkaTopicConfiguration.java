package pl.mwisniewski.usertags.adapters.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {
    @Value("${user-tags.topic.name}")
    private String topicName;

    @Bean
    public NewTopic userTagTopic() {
        return TopicBuilder
                .name(topicName)
                .config(TopicConfig.RETENTION_MS_CONFIG, HOUR_MS.toString())
                .build();
    }

    private static final Integer HOUR_MS = 1 * 60 * 60 * 1000;
}
