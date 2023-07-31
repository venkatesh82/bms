package com.sample.bms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter

public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id")
    @JsonIgnore
    private City city;

    @OneToMany(mappedBy = "theater", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Offer> offers = new ArrayList<>();

    @OneToMany(mappedBy = "theater", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Show> shows = new ArrayList<>();

    public Theater(String name, City city) {
        this.name = name;
        this.city = city;
    }

    public Theater() {

    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("city", city)
                .toString();
    }
}
