package com.andreigravonski.adotepet.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(exclude = "cachorros")
@ToString(exclude = "cachorros")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_ongs")
public class ONG {

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
}
