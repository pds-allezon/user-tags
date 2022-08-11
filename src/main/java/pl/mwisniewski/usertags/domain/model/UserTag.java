package pl.mwisniewski.usertags.domain.model;

public record UserTag(Long timestampMs,
                      String cookie,
                      String country,
                      Device device,
                      Action action,
                      String origin,
                      ProductInfo productInfo) {
}
