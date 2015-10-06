package com.greplr.libcabmeter;

import java.io.Serializable;

/**
 * Created by championswimmer on 7/10/15.
 */
public class CabFare implements Serializable {

    public static final String OP_NAME = "op_name";
    public static final String OP_VAR = "op_var";
    public static final String CITY = "city";
    public static final String RATEKM = "ratekm";
    public static final String MINFARE = "minfare";
    public static final String FIXKM = "fixkm";
    public static final String RATEMIN = "ratemin";
    public static final String FIXMIN = "fixmin";
    public static final String BOOKFEE = "bookfee";

    private String operatorName;
    private String city;
    private String operatorVariant;
    private float ratePerKm;
    private float minFare;
    private float fixedUnmeteredKm;
    private float ratePerMin;
    private float fixedUnmeteredMin;
    private float bookingFee;

    public CabFare(String operatorName, String city, String operatorVariant, float ratePerKm, float minFare, float fixedUnmeteredKm, float ratePerMin, float fixedUnmeteredMin, float bookingFee) {
        this.operatorName = operatorName;
        this.city = city;
        this.operatorVariant = operatorVariant;
        this.ratePerKm = ratePerKm;
        this.minFare = minFare;
        this.fixedUnmeteredKm = fixedUnmeteredKm;
        this.ratePerMin = ratePerMin;
        this.fixedUnmeteredMin = fixedUnmeteredMin;
        this.bookingFee = bookingFee;
    }

    public CabFare(String operatorName, String city, String operatorVariant, float ratePerKm, float minFare, float fixedUnmeteredKm, float bookingFee) {
        this.operatorName = operatorName;
        this.city = city;
        this.operatorVariant = operatorVariant;
        this.ratePerKm = ratePerKm;
        this.minFare = minFare;
        this.fixedUnmeteredKm = fixedUnmeteredKm;
        this.ratePerMin = 0;
        this.fixedUnmeteredMin = 0;
        this.bookingFee = bookingFee;
    }

    public CabFare(String operatorName, String city, float ratePerKm, float minFare, float fixedUnmeteredKm, float ratePerMin, float fixedUnmeteredMin, float bookingFee) {
        this(operatorName, city, "", ratePerKm, minFare, fixedUnmeteredKm, ratePerMin, fixedUnmeteredMin, bookingFee);
    }

    public CabFare(String operatorName, String city, float ratePerKm, float minFare, float fixedUnmeteredKm, float bookingFee) {
        this(operatorName, city, "", ratePerKm, minFare, fixedUnmeteredKm, bookingFee);
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOperatorVariant() {
        return operatorVariant;
    }

    public void setOperatorVariant(String operatorVariant) {
        this.operatorVariant = operatorVariant;
    }

    public float getRatePerKm() {
        return ratePerKm;
    }

    public void setRatePerKm(float ratePerKm) {
        this.ratePerKm = ratePerKm;
    }

    public float getMinFare() {
        return minFare;
    }

    public void setMinFare(float minFare) {
        this.minFare = minFare;
    }

    public float getFixedUnmeteredKm() {
        return fixedUnmeteredKm;
    }

    public void setFixedUnmeteredKm(float fixedUnmeteredKm) {
        this.fixedUnmeteredKm = fixedUnmeteredKm;
    }

    public float getRatePerMin() {
        return ratePerMin;
    }

    public void setRatePerMin(float ratePerMin) {
        this.ratePerMin = ratePerMin;
    }

    public float getFixedUnmeteredMin() {
        return fixedUnmeteredMin;
    }

    public void setFixedUnmeteredMin(float fixedUnmeteredMin) {
        this.fixedUnmeteredMin = fixedUnmeteredMin;
    }

    public float getBookingFee() {
        return bookingFee;
    }

    public void setBookingFee(float bookingFee) {
        this.bookingFee = bookingFee;
    }
}
