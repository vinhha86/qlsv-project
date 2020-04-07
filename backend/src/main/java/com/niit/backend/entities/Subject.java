package com.niit.backend.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "monhoc")
@Data
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ma")
    private String maMonHoc;
    @Column(name = "ten")
    private String tenMonHoc;
    @Column(name = "so_buoi")
    private String soBuoi;

    private String sim;
    private String kit;

    @ManyToOne
    @JoinColumn(name = "ma_khoa_hoc")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Course course;

}
