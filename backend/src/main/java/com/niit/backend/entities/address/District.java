package com.niit.backend.entities.address;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "quanhuyen")
@Data
public class District {
    @Id
    private String maqh;

    private String name;

    private String type;

    private String matp;
}
