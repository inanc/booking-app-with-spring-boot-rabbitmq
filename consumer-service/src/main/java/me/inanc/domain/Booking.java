package me.inanc.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@ToString
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, scope = Booking.class)
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String passengerName;
    private String number;
    private String rating;
    private int price;
    private int numberOfPassengers;

    @Column(columnDefinition = "datetime")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime pickupDate;

    @CreatedDate
    @Type(type = "java.time.LocalDateTime")
    @Column(columnDefinition = "datetime")
    @DateTimeFormat
    private LocalDateTime createdon;

    @LastModifiedDate
    @Type(type = "java.time.LocalDateTime")
    @Column(columnDefinition = "datetime")
    @DateTimeFormat
    private LocalDateTime modifiedon;

    @OneToMany(cascade = CascadeType.ALL)
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