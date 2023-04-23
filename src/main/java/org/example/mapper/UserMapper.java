package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {
    User getUserByEmail(String email);
    User getUserById(String user_id);
    List getUserByLike(User user);
    List getUserAll();
    List getTeacherAll();
    int insertUser(User user);
    int updateUser(User user);
    int updateUserPass(User user);
    int updateUserRole(User user);
    int updateUserMoney(User user);
    int updateUserName(User user);
}
