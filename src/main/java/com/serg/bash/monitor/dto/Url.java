package com.serg.bash.monitor.dto;

import com.serg.bash.monitor.Status;
import lombok.Data;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
@Data
public class Url {

    private String id;

    private String url;

    private int periodMonitoring;

    private int responseTime;

    private int responseCode;

    private Status status;

    private String subQuery;

    private String name;

    private String createdBy;

    private Date createdDate;

    private String lastModifiedBy;

    private Date lastModifiedDate;

    private Long version;

    private int minResponseSize;

    private int maxResponseSize;
}
