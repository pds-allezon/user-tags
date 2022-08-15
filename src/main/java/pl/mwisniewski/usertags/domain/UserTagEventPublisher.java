package pl.mwisniewski.usertags.domain;

import pl.mwisniewski.usertags.domain.model.UserTag;

public interface UserTagEventPublisher {
    void publish(UserTag userTag);
}
