package me.inanc.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(hidden = true)
    private Long id;

    private String locality;

    private String latitude;
    private String longitude;

    public TripWaypoint(String locality, String latitude, String longitude) {
        this.locality = locality;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
