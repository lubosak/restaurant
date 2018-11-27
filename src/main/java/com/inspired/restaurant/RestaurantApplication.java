package com.inspired.restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//refactoring:
//TODO: getReservationTimeSlots in ReservationmanagerImpl

//features:
//TODO: optimize table selection for party size
//TODO: add smoking/non-smoking feature (TERRACE => smoking)
//TODO: change reservation time slot granularity to 30 minutes
//TODO: extend reservations also to lunch hours

@SpringBootApplication
public class RestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
	}
}
