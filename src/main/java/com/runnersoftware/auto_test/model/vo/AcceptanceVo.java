package com.runnersoftware.auto_test.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * (Acceptance)实体类
 *
 * @author
 * @since 2021-05-24 11:35:29
 */
@Data
@Accessors(chain = true)
public class AcceptanceVo implements Serializable {
    private static final long serialVersionUID = -18105260244831596L;
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

    private String username;

    private String phone;

}
