package shop.mtcoding.getinthere.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import shop.mtcoding.getinthere.dto.TockenProperties;
import shop.mtcoding.getinthere.util.Fetch;

@Controller
public class UserController {

    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/callback")
    public @ResponseBody <T> String callback(String code) throws JsonProcessingException {

        // 1. code 값 존재 유무 확인
        if (code == null || code.isEmpty()) {
            return "bad request";
        }
        // 2. code 값을 카카오에게 전달(재인증을 위해) -> access token 받기
        // 카카오 문서 확인하기(Request/Response Parameter 문서를 보고 넣어 주기)
        String kakaoUrl = "https://kauth.kakao.com/oauth/token";
        RestTemplate rt = new RestTemplate(); // request 요청을 할 수 있다

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // body
        MultiValueMap<String, String> xForm = new LinkedMultiValueMap<>();
        xForm.add("grant_type", "authorization_code");
        xForm.add("client_id", "63404b992b07996d8135f990f10c2911");
        xForm.add("redirect_uri", "http://localhost:8080/callback"); // 2차 검증
        xForm.add("code", code); // 핵심

        HttpEntity<?> httpEntity = new HttpEntity<>(xForm, headers);

        ResponseEntity<String> responseEntity = rt.exchange(kakaoUrl, HttpMethod.POST, httpEntity, String.class);

        // 3. access token으로 카카오의 홍길동 resource 접근 가능해짐 (3/4/5 같이 묶어서 email 파싱하고 정보 받기)
        // String responseBody = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        TockenProperties tockenProperties = mapper.readValue(responseEntity.getBody(), TockenProperties.class);

        // 4. access token을 파싱 하고
        ResponseEntity<String> tokenEntity = Fetch.kakao("https://kapi.kakao.com/v2/user/me", HttpMethod.POST,
                tockenProperties.getAccessToken());

        // 5. access token으로 email 정보 받기 (ex>ssar@gmail.com / pwUUID로)

        // 6. 해당 email로 회원가입되어 있는 user 정보가 있는지 DB 조회 (x)

        // 7. 있으면 그 user 정보로 session 만들어주고(자동 로그인) (x)

        // 8. 없으면 강제 회원가입 시키고, 그 정보로 session 만들어 주고(자동 로그인)

        return tokenEntity.getBody();

    }
}
