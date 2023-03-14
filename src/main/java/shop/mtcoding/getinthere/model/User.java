package shop.mtcoding.getinthere.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private int id;
    private String username; // kakao_ssar@nate.com (중복될 수 있기 때문에 )
    private String password; // UUID
    private String email; // ssar@nate.com
    private String provider; // me, kakao, naver

}
