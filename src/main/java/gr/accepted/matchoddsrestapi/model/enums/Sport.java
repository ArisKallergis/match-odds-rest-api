package gr.accepted.matchoddsrestapi.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum Sport {
    FOOTBALL(1),
    BASKETBALL(2);

    final int id;

    Sport(int id) {
        this.id = id;
    }

    public static Sport from(Integer id) {
        for (Sport sport : Sport.values()) {
            if (sport.getId() == id)
                return sport;
        }

        throw new IllegalArgumentException("No Sport with id " + id);
    }

    @JsonCreator
    public static Sport incoming(Object sport) {
        if (sport instanceof String) {
            for (Sport s : values()) {
                if (s.toString().equals(sport) || s.toString().toLowerCase().equals(sport)) {
                    return s;
                }
            }
            throw new IllegalArgumentException("No Sport with name " + sport);
        } else if (sport instanceof Integer) {
            for (Sport s : values()) {
                if (s.getId() == (Integer) sport) {
                    return s;
                }
            }
            throw new IllegalArgumentException("No Sport with id " + sport);
        } else {
            throw new IllegalArgumentException("sport could not be deserialized");
        }
    }
}