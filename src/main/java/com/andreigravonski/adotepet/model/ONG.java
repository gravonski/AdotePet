package com.andreigravonski.adotepet.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.stream.Collectors;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = "cachorros")
@ToString(exclude = "cachorros")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_ongs")
public class ONG implements UserDetails {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String nome;

private String email;

@Column(length = 60)
private String senha;

private String cidade;

private String telefone;

@OneToMany(mappedBy = "ong")
private List<Cachorro> cachorros;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getNome()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_ong_roles",
            joinColumns = @JoinColumn(name = "ong_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
