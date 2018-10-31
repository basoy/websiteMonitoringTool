package com.serg.bash.entity.impl;

import com.serg.bash.entity.BaseEntity;
import com.serg.bash.monitor.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection = "url")
@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class Url extends BaseEntity {

    private String url;

    private int periodMonitoring;

    private int responseTime;

    private int responseCode;

    private Status status;

    private String subQuery;

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
