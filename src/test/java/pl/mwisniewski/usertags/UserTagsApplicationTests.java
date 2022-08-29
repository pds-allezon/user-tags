package pl.mwisniewski.usertags;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import pl.mwisniewski.usertags.adapters.api.ProductInfoRequest;
import pl.mwisniewski.usertags.adapters.api.UserTagEndpoint;
import pl.mwisniewski.usertags.adapters.api.UserTagRequest;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserTagsApplicationTests {
    @Autowired
    private UserTagEndpoint userTagEndpoint;

    @Test
    void userTagEndpointShouldReturnNoContent() {
        // given
        ProductInfoRequest productInfoRequest = new ProductInfoRequest(
                "productId", "brandId", "categoryId", 123);
        UserTagRequest userTagRequest = new UserTagRequest(
                "2022-03-22T12:15:12.345Z", "cookie", "country", "PC", "BUY",
                "origin", productInfoRequest);

        // when
        ResponseEntity<Object> response = userTagEndpoint.userTag(userTagRequest);

        // then
        assert response.getStatusCode() == HttpStatus.NO_CONTENT;
    }

}
