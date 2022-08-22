package pl.mwisniewski.usertags.domain.model;

public record UserTag(String time,
                      String cookie,
                      String country,
                      Device device,
                      Action action,
                      String origin,
                      ProductInfo productInfo) {
}
