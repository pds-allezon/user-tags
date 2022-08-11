package pl.mwisniewski.usertags.adapters.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.mwisniewski.usertags.domain.UserTagEventPublisher;

@RestController
public class UserTagEndpoint {
    private final UserTagEventPublisher userTagEventPublisher;

    public UserTagEndpoint(UserTagEventPublisher userTagEventPublisher) {
        this.userTagEventPublisher = userTagEventPublisher;
    }

    @PostMapping("/user_tags")
    public ResponseEntity<Object> userTag(@RequestBody UserTagRequest userTagRequest) {
        userTagEventPublisher.publish(userTagRequest.toDomain());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
