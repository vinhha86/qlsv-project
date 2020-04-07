package com.niit.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.niit.backend.entities.address.Address;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString.Exclude;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "hososinhvien")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ho;
    private String ten;
    private String masv;
    private Timestamp ngaysinh;
    private String socmt;
    private String email;
    private int gioitinh;
    private String sdt_canhan;
    private String sdt_phuhuynh;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private FileResponse avatar;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "diachi")
    private Address address;
    
    @ManyToMany(mappedBy = "students")
    @EqualsAndHashCode.Exclude
    @Exclude
    @JsonIgnore
    private Collection<Class> classes;
}
