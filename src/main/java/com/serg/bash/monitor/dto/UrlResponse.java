package com.serg.bash.monitor.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UrlResponse {

    private int responseCode;

    private long responseSize;
}
