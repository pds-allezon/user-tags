package pl.mwisniewski.usertags.adapters;

import org.springframework.stereotype.Component;
import pl.mwisniewski.usertags.domain.UserTagEventPublisher;
import pl.mwisniewski.usertags.domain.model.UserTag;

@Component
public class DummyUserTagEventPublisher implements UserTagEventPublisher {
    @Override
    public void publish(UserTag userTag) {
        // Do nothing.
    }
}
