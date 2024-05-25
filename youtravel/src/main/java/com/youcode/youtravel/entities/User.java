package com.youcode.youtravel.entities;

import com.youcode.youtravel.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @NotBlank(message = "First Name should not be empty")
    private String firstname;

    @NotBlank(message = "Last Name should not be empty")
    private String lastname;

    @NotBlank(message = "Phone should not be empty")
    private String phone;

    @NotBlank(message = "Email should not be empty")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "User Name should not be empty")
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Password should not be empty")
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role type should not be null")
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Journey> journeyList;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Car> carList;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Group> groupList;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Reservation> reservationList;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Join> joinList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.getRole().name()));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
