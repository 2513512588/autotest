package com.runnersoftware.auto_test.model;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * (User)实体类
 *
 * @author
 * @since 2021-05-12 12:48:39
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -77638927996207466L;

    private Integer id;

    @JsonIgnore
    private String password;

    private Date createTime;

    private String username;


}
