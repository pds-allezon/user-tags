package pl.mwisniewski.usertags.adapters.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import pl.mwisniewski.usertags.domain.UserTagEventPublisher;
import pl.mwisniewski.usertags.domain.model.UserTag;

@Component
@Profile("prod")
public class KafkaUserTagEventPublisher implements UserTagEventPublisher {

    @Value("${user-tags.topic.name}")
    private String topicName;

    private final KafkaTemplate<String, UserTag> kafkaTemplate;

    public KafkaUserTagEventPublisher(KafkaTemplate<String, UserTag> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(UserTag userTag) {
        ListenableFuture<SendResult<String, UserTag>> future = kafkaTemplate.send(topicName, userTag);

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                logger.warn("Unable to deliver message {}. {}", userTag, ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, UserTag> result) {
                logger.debug("Message {} delivered with offset {}", userTag, result.getRecordMetadata().offset());
            }
        });
    }

    private final Logger logger = LoggerFactory.getLogger(KafkaUserTagEventPublisher.class);
}
