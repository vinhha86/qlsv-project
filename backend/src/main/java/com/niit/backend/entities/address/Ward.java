package com.niit.backend.entities.address;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "xaphuongthitran")
@Data
public class Ward {
    @Id
    private String xaid;

    private String name;

    private String type;

    private String maqh;
}
