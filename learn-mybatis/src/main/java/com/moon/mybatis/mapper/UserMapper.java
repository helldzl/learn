package com.moon.mybatis.mapper;

import com.moon.mybatis.domain.User;

/**
 * Created at 2021/1/26
 *
 * @author quzile3
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
public interface UserMapper {

    User select(Long id);

    int update(User user);

}
