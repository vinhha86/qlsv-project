package com.niit.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "course", cascade = CascadeType.ALL)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Subject> subjects;
}
