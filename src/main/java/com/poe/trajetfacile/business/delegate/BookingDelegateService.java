package com.poe.trajetfacile.business.delegate;

import java.time.Duration;
import java.time.LocalDateTime;

public class BookingDelegateService {

    public static final int CANCELATION_MAX_DELAY_IN_HOURS = 24;

    /**
     * On ne peut annuler une réservation qu'à certaines conditions.
     */
    public static boolean isCancelable(LocalDateTime desiredCancelationDate, LocalDateTime rideStartDate) {
        boolean isCancelable = true;
        long duation = Duration.between(desiredCancelationDate, rideStartDate).toHours();
        isCancelable = duation > BookingDelegateService.CANCELATION_MAX_DELAY_IN_HOURS;
        return isCancelable;
    }
}
