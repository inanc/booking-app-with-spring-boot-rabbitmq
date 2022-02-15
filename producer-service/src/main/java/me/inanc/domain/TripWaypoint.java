package me.inanc.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, scope = TripWaypoint.class)
public class TripWaypoint implements Serializable {

    private Long id;

    public TripWaypoint(String locality) {
        this.locality = locality;
    }

    private String locality;

    private String latitude;

    private String longitude;


}
