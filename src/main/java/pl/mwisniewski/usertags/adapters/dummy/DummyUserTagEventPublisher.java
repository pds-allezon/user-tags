package pl.mwisniewski.usertags.adapters.dummy;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.mwisniewski.usertags.domain.UserTagEventPublisher;
import pl.mwisniewski.usertags.domain.model.UserTag;

@Component
@Profile("test")
public class DummyUserTagEventPublisher implements UserTagEventPublisher {
    @Override
    public void publish(UserTag userTag) {
        // Do nothing.
    }
}
