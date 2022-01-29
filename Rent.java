package com.hammad.jfx1;

public class Rent {
    String rentFname;
    String rentLname;
    String rentLicense;
    String rentCarMake;
    String rentCarModel;
    String rentCarPlate;
    String rentDaysRented;

    public String getRentFname() {
        return rentFname;
    }

    public void setRentFname(String rentFname) {
        this.rentFname = rentFname;
    }

    public String getRentLname() {
        return rentLname;
    }

    public void setRentLname(String rentLname) {
        this.rentLname = rentLname;
    }

    public String getRentLicense() {
        return rentLicense;
    }

    public void setRentLicense(String rentLicense) {
        this.rentLicense = rentLicense;
    }

    public String getRentCarMake() {
        return rentCarMake;
    }

    public void setRentCarMake(String rentCarMake) {
        this.rentCarMake = rentCarMake;
    }

    public String getRentCarModel() {
        return rentCarModel;
    }

    public void setRentCarModel(String rentCarModel) {
        this.rentCarModel = rentCarModel;
    }

    public String getRentCarPlate() {
        return rentCarPlate;
    }

    public void setRentCarPlate(String rentCarPlate) {
        this.rentCarPlate = rentCarPlate;
    }

    public String getRentDaysRented() {
        return rentDaysRented;
    }

    public void setRentDaysRented(String rentDaysRented) {
        this.rentDaysRented = rentDaysRented;
    }



    public Rent(String rentFname, String rentLname, String rentLicense, String rentCarMake, String rentCarModel, String rentCarPlate, String rentDaysRented) {
        this.rentFname = rentFname;
        this.rentLname = rentLname;
        this.rentLicense = rentLicense;
        this.rentCarMake = rentCarMake;
        this.rentCarModel = rentCarModel;
        this.rentCarPlate = rentCarPlate;
        this.rentDaysRented = rentDaysRented;
    }


}
