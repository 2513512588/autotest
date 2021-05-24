package com.runnersoftware.auto_test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * (User)实体类
 *
 * @author
 * @since 2021-05-12 12:48:39
 */
@Data
@Accessors(chain = true)
public class User implements Serializable {
    private static final long serialVersionUID = -77638927996207466L;

    private Integer id;

    private String username;

    @JsonIgnore
    private String password;

    private Date createTime;

    private String realName;

    private String phone;

    private Integer role;

}
