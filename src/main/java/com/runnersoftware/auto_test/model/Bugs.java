package com.runnersoftware.auto_test.model;

import java.util.Date;
import java.io.Serializable;

import lombok.Data;

/**
 * (Bugs)实体类
 *
 * @author
 * @since 2021-05-08 14:47:27
 */
@Data
public class Bugs implements Serializable {


    private static final long serialVersionUID = 853513540510338754L;

    private Object bugId;

    private Object assignedTo;

    private Object bugFileLoc;

    private String bugSeverity;

    private String bugStatus;

    private Date creationTs;

    private Date deltaTs;

    private String shortDesc;

    private String opSys;

    private String priority;

    private Object productId;

    private String repPlatform;

    private Object reporter;

    private String version;

    private Object componentId;

    private String resolution;

    private String targetMilestone;

    private Object qaContact;

    private Object statusWhiteboard;

    private Date lastdiffed;

    private Integer everconfirmed;

    private Integer reporterAccessible;

    private Integer cclistAccessible;

    private Double estimatedTime;

    private Double remainingTime;

    private Date deadline;

    private String alias;


    public Bugs() {
    }

    public static Bugs buildDefault(String shortDesc) {
        Bugs bugs = new Bugs();
        bugs.setShortDesc(shortDesc);
        bugs.setAssignedTo("1");
        bugs.setBugSeverity("trivial");
        bugs.setBugStatus("CONFIRMED");
        bugs.setDeltaTs(new Date());
        bugs.setOpSys("Mac OS");
        bugs.setPriority("1");
        bugs.setProductId("1");
        bugs.setRepPlatform("PC");
        bugs.setReporter("1");
        bugs.setVersion("1");
        bugs.setComponentId("1");
        bugs.setTargetMilestone("---1");
        bugs.setStatusWhiteboard("");
        bugs.setResolution("");
        bugs.setEverconfirmed(1);
        bugs.setReporterAccessible(1);
        bugs.setCclistAccessible(1);
        bugs.setEstimatedTime(0.00d);
        bugs.setRemainingTime(0.00d);
        bugs.setDeadline(new Date());
        bugs.setCreationTs(new Date());
        return bugs;
    }
}
