package com.greplr.libcabmeter;

/**
 * Created by championswimmer on 7/10/15.
 */
public class CabFareOps {

    public static float calcFare(float kiloMeters, CabFare fare) {
        float totalFare = 0;
        totalFare += fare.getBookingFee();
        totalFare += fare.getMinFare();

        float chargeableKm = kiloMeters - fare.getFixedUnmeteredKm();
        chargeableKm = (chargeableKm>0) ? chargeableKm : 0;

        totalFare += chargeableKm * fare.getRatePerKm();

        return totalFare;
    }
}
