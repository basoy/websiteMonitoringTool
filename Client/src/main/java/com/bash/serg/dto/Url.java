package com.bash.serg.dto;

import com.bash.serg.Status;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;


@Data
@Value
public class Url implements Serializable{

    private String id;

    private String url;

    private int periodMonitoring;

    private long responseTime;

    private int responseCode;

    private Status status;

    private String subQuery;

    private String name;

    private String createdBy;

    private Date createdDate;

    private String lastModifiedBy;

    private Date lastModifiedDate;

    private Long version;

    private long minResponseSize;

    private long maxResponseSize;
}
