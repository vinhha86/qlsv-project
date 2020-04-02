package com.niit.backend.entities.address;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tinhthanhpho")
@Data
public class Province {
    @Id
    private String matp;

    private String name;

    private String type;
}
