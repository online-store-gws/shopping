package uz.greenwhite.shopping.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 50, nullable = false)
    private String firstName;
    @Column(length = 50)
    private String lastName;


    @NotNull
    @Column(length = 30, unique = true, nullable = false)
    @Pattern(regexp = "^(?![_0-9])[a-z0-9_]{5,29}$")
    private String username;

    @NotNull
    @Column(length = 60,  unique = true, nullable = false)
    @Size(min = 60, max = 60)
    private String password;


    @Column(length = 10)
    @Enumerated(EnumType.STRING) // ORDINAL - number, STRING - string
    private UserRole role;

    private Boolean active;

    private Integer verificationCode;



    private Date registerDate;
}
