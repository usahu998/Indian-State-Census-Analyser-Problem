package com.bridgelabz.statecensusanalyserproblem;

import com.opencsv.bean.CsvBindByName;

public class CSVStatesCensus {
    public CSVStatesCensus() {
    }

    @CsvBindByName
    private String state;
    @CsvBindByName
    private String population;
    @CsvBindByName
    private String areaInSqKm;
    @CsvBindByName
    private String densityPerSqKm;

    public CSVStatesCensus(String state, String population, String areaInSqKm, String densityPerSqKm) {
        this.state = state;
        this.population = population;
        this.areaInSqKm = areaInSqKm;
        this.densityPerSqKm = densityPerSqKm;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getAreaInSqKm() {
        return areaInSqKm;
    }

    public void setAreaInSqKm(String areaInSqKm) {
        this.areaInSqKm = areaInSqKm;
    }

    public String getDensityPerSqKm() {
        return densityPerSqKm;
    }

    public void setDensityPerSqKm(String densityPerSqKm) {
        this.densityPerSqKm = densityPerSqKm;
    }

    @Override
    public String toString() {
        return "CSVStatesCensus{" +
                "state='" + state + '\'' +
                ", population=" + population +
                ", areaInSqKm=" + areaInSqKm +
                ", densityPerSqKm=" + densityPerSqKm +
                '}';
    }
}
