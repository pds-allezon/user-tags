package pl.mwisniewski.usertags.adapters.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import pl.mwisniewski.usertags.domain.UserTagEventPublisher;
import pl.mwisniewski.usertags.domain.model.UserTag;

@Component
@Primary
public class KafkaUserTagEventPublisher implements UserTagEventPublisher {

    private final KafkaTemplate<String, UserTag> kafkaTemplate;

    public KafkaUserTagEventPublisher(KafkaTemplate<String, UserTag> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(UserTag userTag) {
        ListenableFuture<SendResult<String, UserTag>> future =
                kafkaTemplate.send(USER_TAG_TOPIC_NAME, userTag);

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                logger.warn("Unable to deliver message {}. {}", userTag, ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, UserTag> result) {
                logger.info("Message {} delivered with offset {}", userTag, result.getRecordMetadata().offset());
            }
        });
    }

    private final Logger logger = LoggerFactory.getLogger(KafkaUserTagEventPublisher.class);
    private static final String USER_TAG_TOPIC_NAME = "user-tag";
}
