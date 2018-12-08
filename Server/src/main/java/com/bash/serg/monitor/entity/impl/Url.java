package com.bash.serg.monitor.entity.impl;

import com.bash.serg.monitor.Status;
import com.bash.serg.monitor.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Data
@Document(collection = "url")
@EqualsAndHashCode(callSuper = false)
@Component
public class Url extends BaseEntity {

    private String url;

    private int periodMonitoring;

    private int responseTime;

    private int responseCode;

    private Status status;

    private String subQuery;

    private long minResponseSize;

    private long maxResponseSize;
}
