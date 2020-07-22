package org.example.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="skill")
public class Skill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String name;

    public Skill() {
    }

    public Skill(String name) {
        this.name = name;
    }

    public Skill(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", skill='" + name + '\'' +
                '}';
    }
}
