package com.niit.backend.entities.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String chitiet;

    @OneToOne
    @JoinColumn(name = "ma_tinh")
    private Province province;

    @OneToOne
    @JoinColumn(name = "ma_huyen")
    private District district;

    @OneToOne
    @JoinColumn(name = "ma_xa")
    private Ward ward;
}
