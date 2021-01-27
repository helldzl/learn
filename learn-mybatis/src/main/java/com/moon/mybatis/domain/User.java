package com.moon.mybatis.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created at 2021/1/26
 *
 * @author quzile3
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
@Getter
@Setter
@ToString
public class User implements Serializable {

    private Long id;

    private String username;

}
