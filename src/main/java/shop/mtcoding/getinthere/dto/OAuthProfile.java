package shop.mtcoding.getinthere.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OAuthProfile {
    public static Class classO;
    private Long id;
    @JsonProperty("connected_at")
    private String connectedAt;
    @JsonProperty("kakao_account")
    private KaKaoAccount KaKaoAccount;

    @Getter
    @Setter
    public class KaKaoAccount {
        @JsonProperty("has_email")
        private Boolean hasEmail;
        @JsonProperty("email_needs_agreement")
        private Boolean emailNeedsAgreedment;
        @JsonProperty("is_email_valid")
        private Boolean isEmailVaild;
        @JsonProperty("is_email_verified")
        private Boolean isEmailVerified;
        private String email;

    }
}
