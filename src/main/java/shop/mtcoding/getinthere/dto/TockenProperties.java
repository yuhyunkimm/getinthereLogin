package shop.mtcoding.getinthere.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TockenProperties {
    private String accessToken;// access_tocken 언더스코어 설정 하기
    private String tokenType;
    private String refreshToken;
    private int expiresIn;
    private String scope;
    private int refreshTokenExpiresIn;
}
