package model;

public class Sys {
    private double type;
    private double id;
    private double message;
    private String country;
    private double sunrise;
    private double sunsen;

    public double getType() {
        return type;
    }

    public void setType(double type) {
        this.type = type;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getSunrise() {
        return sunrise;
    }

    public void setSunrise(double sunrise) {
        this.sunrise = sunrise;
    }

    public double getSunset() {
        return sunsen;
    }

    public void setSunsen(double sunsen) {
        this.sunsen = sunsen;
    }
}
