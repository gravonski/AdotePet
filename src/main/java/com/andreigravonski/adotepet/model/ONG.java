package com.andreigravonski.adotepet.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

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

private String senha;

private String cidade;

private String telefone;

@OneToMany(mappedBy = "ong")
private List<Cachorro> cachorros;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
