package de.htwberlin.skateplanner.event;

import de.htwberlin.skateplanner.user.UserEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "events")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    @NotEmpty
    private String name;

    private String description;

    @OneToMany()
    private List<UserEntity> test;

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
