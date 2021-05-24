package com.runnersoftware.auto_test.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * (Acceptance)实体类
 *
 * @author
 * @since 2021-05-24 15:28:56
 */
@Data
@Accessors(chain = true)
public class Acceptance implements Serializable {
    private static final long serialVersionUID = 177280802726348866L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 项目题目
     */
    private String title;
    /**
     * 测试内容
     */
    private String content;
    /**
     * 测试用户
     */
    private Integer userId;
    /**
     * 测试缺陷
     */
    private String defect;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 测试时间
     */
    private Date testTime;
    /**
     * 状态 1-未测试 2-已测试
     */
    private Integer status;


}
