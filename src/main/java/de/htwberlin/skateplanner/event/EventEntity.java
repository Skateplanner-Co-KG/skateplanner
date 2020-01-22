package de.htwberlin.skateplanner.event;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.GregorianCalendar;

@Entity
@Table(name = "events")
public class EventEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String name;

    @NotBlank
    private String type;

    @NotNull
    private String description;

    @NotNull
    private Date date;

    public EventEntity() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String date) {
        if (!date.matches("\\d{2}-\\d{2}-\\d{4}") || date.length() != 10)
            throw new IllegalArgumentException("invalid date format!");
        int year = Integer.parseInt(date.substring(6,10));
        int month = Integer.parseInt(date.substring(3,5));
        int day = Integer.parseInt(date.substring(0,2));
        this.date = new Date(new GregorianCalendar(year,month,day).toInstant().toEpochMilli());
    }
}
