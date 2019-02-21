package com.bash.serg.monitor.entity.impl;

import com.bash.serg.monitor.Status;
import com.bash.serg.monitor.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;

@Data
@Document(collection = "url")
@EqualsAndHashCode(callSuper = false)
@Component
public class Url extends BaseEntity {

    @URL
    @NotBlank(message = "url is mandatory")
    private String url;

    @Digits(integer = 100000, fraction = 0)
    @NotNull
    private int periodMonitoring;

    @Digits(integer = 100000, fraction = 0)
    @NotNull
    private int responseTime;

    @Digits(integer = 100000, fraction = 0)
    @NotNull
    private int responseCode;

    private Status status;

    private String subQuery;

    @Digits(integer = 100000, fraction = 0)
    @NotNull
    private long minResponseSize;

    @Digits(integer = 100000, fraction = 0)
    @NotNull
    private long maxResponseSize;
}
