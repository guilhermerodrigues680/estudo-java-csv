package com.example.javacsv.models;

import com.opencsv.bean.CsvBindByName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class GeographicBean {
    @CsvBindByName
    private String anzsic06;

    @CsvBindByName
    private String Area;

    @CsvBindByName
    private int year;

    @CsvBindByName
    private int geo_count;

    @CsvBindByName
    private int ec_count;


}
