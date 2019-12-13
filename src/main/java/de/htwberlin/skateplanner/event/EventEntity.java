package de.htwberlin.skateplanner.event;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "events")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    @NotEmpty
    private String name;

    public EventEntity() {
    }

    public EventEntity(String name) {
        this.name = name;
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
}
