package com.example.javacsv.models;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class IndustryBean {

    @CsvBindByName
    private String year;

    @CsvBindByName
    private String industry_code_ANZSIC;

    @CsvBindByName
    private String industry_name_ANZSIC;

    @CsvBindByName
    private String rme_size_grp;

    @CsvBindByName
    private String variable;

    @CsvBindByName
    private String value;

    @CsvBindByName
    private String unit;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getIndustry_code_ANZSIC() {
        return industry_code_ANZSIC;
    }

    public void setIndustry_code_ANZSIC(String industry_code_ANZSIC) {
        this.industry_code_ANZSIC = industry_code_ANZSIC;
    }

    public String getIndustry_name_ANZSIC() {
        return industry_name_ANZSIC;
    }

    public void setIndustry_name_ANZSIC(String industry_name_ANZSIC) {
        this.industry_name_ANZSIC = industry_name_ANZSIC;
    }

    public String getRme_size_grp() {
        return rme_size_grp;
    }

    public void setRme_size_grp(String rme_size_grp) {
        this.rme_size_grp = rme_size_grp;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
