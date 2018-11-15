package com.bash.serg.dto;

import com.bash.serg.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
public class Url{

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
