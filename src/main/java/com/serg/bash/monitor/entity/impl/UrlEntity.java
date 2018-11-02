package com.serg.bash.monitor.entity.impl;

import com.serg.bash.monitor.Status;
import com.serg.bash.monitor.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "url")
public class UrlEntity extends BaseEntity {

    private String url;

    private int periodMonitoring;

    private int responseTime;

    private int responseCode;

    private Status status;

    private String subQuery;

    private int minResponseSize;

    private int maxResponseSize;

//    sites.period.monitoring=600
//    sites.response.time=15
//    sites.response.code=200

//* Время ответа сервера (отдельные пороги для OK, WARNING, CRITICAL)
//
//* Ожидаемый HTTP response code.
//    Если сервер возвращает какой-то другой код, статус URL-а должен быть CRITICAL.
//
//            * Ожидаемый диапазон размера респонса в байтах (min и max).
//    Если размер контента выходит за пределы допустимого диапазона, статус URL-а
//    должен быть CRITICAL.
//
//* Опционально - подстрока, которая должна содержаться в респонсе.
//    Если подстроки нету, статус URL-а должен быть CRITICAL.
}
