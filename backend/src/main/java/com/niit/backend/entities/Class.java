package com.niit.backend.entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.niit.backend.entities.address.Address;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "lophoc")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Class {
	
	@Id //Đánh dấu là primary key
    @GeneratedValue // Giúp tự động tăng
    private Long id;

    private String malophoc;
    private String tenlophoc;
    private Date ngaykhaigiang;
    private String tinhtrang;
    private String ghichu;
    private int makhoahoc;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // Quan hệ n-n với đối tượng ở dưới (Student)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    
    @JoinTable(name = "nhom", //Tạo ra một join Table tên là "nhom"
            joinColumns = @JoinColumn(name = "idlophoc"),  // TRong đó, khóa ngoại chính là idlophoc trỏ tới class hiện tại (Class)
            inverseJoinColumns = @JoinColumn(name = "idsinhvien") //Khóa ngoại thứ 2 trỏ tới thuộc tính ở dưới (Student)
    )
    
    //@JsonIgnore
    private Collection<Student> students;
	
}
