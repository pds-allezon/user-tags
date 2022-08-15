package pl.mwisniewski.usertags.adapters.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.mwisniewski.usertags.domain.model.ProductInfo;

public record ProductInfoRequest(@JsonProperty("product_id") String productId,
                                 @JsonProperty("brand_id") String brandId,
                                 @JsonProperty("category_id") String categoryId,
                                 int price) {
    public ProductInfo toDomain() {
        return new ProductInfo(productId, brandId, categoryId, price);
    }
}
