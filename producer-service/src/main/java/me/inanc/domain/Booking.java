package me.inanc.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, scope = Booking.class)
public class Booking implements Serializable {
    @ApiModelProperty(hidden = true)
    private Long id;

    private String passengerName;
    private String number;
    private String rating;
    private int price;
    private int numberOfPassengers;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime pickupDate;

    private Set<TripWaypoint> tripWaypoints;

    public Booking(String passengerName, String number, String rating, int price, int numberOfPassengers, LocalDateTime pickupDate, Set<TripWaypoint> tripWaypoints) {
        this.passengerName = passengerName;
        this.number = number;
        this.rating = rating;
        this.price = price;
        this.numberOfPassengers = numberOfPassengers;
        this.pickupDate = pickupDate;
        this.tripWaypoints = tripWaypoints;
    }


}