package com.moon.mybatis.service;

import com.moon.mybatis.domain.User;
import com.moon.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * Created at 2021/1/26
 *
 * @author quzile3
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
public class Reader {

    public static void main(String[] args) throws IOException, InterruptedException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session1 = sqlSessionFactory.openSession(true);
        SqlSession session2 = sqlSessionFactory.openSession(true);
        SqlSession session3 = sqlSessionFactory.openSession(false);
        UserMapper m1 = session1.getMapper(UserMapper.class);
        UserMapper m2 = session2.getMapper(UserMapper.class);
        UserMapper m3 = session2.getMapper(UserMapper.class);

        // first select
        System.out.println(m1.select(1L));
        session1.close();

        // update
//        User user = new User();
//        user.setId(2L);
//        user.setUsername("曲子乐3333331111");
//        int update = m3.update(user);
//        System.out.println("update:" + update);
//        session3.close();

        // second select
        System.out.println(m2.select(1L));
        session2.close();
        //
        TimeUnit.SECONDS.sleep(1);
    }

}
