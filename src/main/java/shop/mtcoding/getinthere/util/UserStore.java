package shop.mtcoding.getinthere.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import shop.mtcoding.getinthere.model.User;

public class UserStore {
    private static List<User> userList = new ArrayList<>();

    static {
        userList.add(
                new User(
                        1,
                        "kakao_2705555663",
                        UUID.randomUUID().toString(),
                        "dbgus345@hanmail.net",
                        "kakao"));
    }

    public User findByUsername(String username) {
        // User findUser = userList.stream().filter((user)->
        // user.getUsername().equals(username)).findFirst().orElseThrow(); //filter 한건만
        // 조회
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
