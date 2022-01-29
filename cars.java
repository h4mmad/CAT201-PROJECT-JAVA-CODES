package com.hammad.jfx1;

public class cars {
    String make;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    String model;
    String year;
    String plate;
    String fuelType;

    public Double getCost24hrs() {
        return cost24hrs;
    }

    public void setCost24hrs(Double cost24hrs) {
        this.cost24hrs = cost24hrs;
    }

    Double cost24hrs;



    public cars(String make, String model, String year, String plate, String fuelType, Double cost24hrs){
        this.make = make;
        this.model = model;
        this.year = year;
        this.plate = plate;
        this.fuelType = fuelType;
        this.cost24hrs = cost24hrs;
    }





}
