package pl.mwisniewski.usertags.domain.model;

public record ProductInfo(String productId,
                          String brandId,
                          String categoryId,
                          int price) {
}
