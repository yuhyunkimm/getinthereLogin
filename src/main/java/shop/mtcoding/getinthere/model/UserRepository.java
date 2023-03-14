package shop.mtcoding.getinthere.model;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
    User getUserByEmail(String email);
}
