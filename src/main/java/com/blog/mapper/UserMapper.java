package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.entity.User;
import com.blog.model.vo.SystemUserVO;
import com.blog.model.vo.UserInfoVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {

    SystemUserVO getById(Object id);

    void updateLoginInfo(@Param("loginId")Object loginId, @Param("ip") String ip, @Param("cityInfo")String cityInfo,
                         @Param("os") String os, @Param("browser") String browser);

    User selectNameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 根据用户名查询
     */
    UserInfoVO selectByUserName(String username);

}
