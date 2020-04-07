package com.niit.backend.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "khoahoc")
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma")
    private String maKhoaHoc;
    @Column(name = "ten")
    private String tenKhoaHoc;
    @Column(name = "kieu")
    private int kieuKhoaHoc;
    @Column(name = "so_buoi", nullable = true)
    private Integer soBuoi;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Subject> subjects;
}
