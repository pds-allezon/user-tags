package pl.mwisniewski.usertags.adapters.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.mwisniewski.usertags.domain.model.Action;
import pl.mwisniewski.usertags.domain.model.Device;
import pl.mwisniewski.usertags.domain.model.UserTag;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public record UserTagRequest(String time,
                             String cookie,
                             String country,
                             String device,
                             String action,
                             String origin,
                             @JsonProperty("product_info") ProductInfoRequest productInfo) {
    public UserTag toDomain() {
        return new UserTag(
                ZonedDateTime.parse(time).toInstant().toEpochMilli(),
                cookie,
                country,
                Device.valueOf(device),
                Action.valueOf(action),
                origin,
                productInfo.toDomain()
        );
    }
}
