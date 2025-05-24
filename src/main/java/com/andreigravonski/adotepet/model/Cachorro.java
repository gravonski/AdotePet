package com.andreigravonski.adotepet.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "tb_cachorros")
public class Cachorro {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String nome;

private int idade;

private String raca;

private Enum status;

private String imagemUrl;

private String descricao;

@ManyToOne
@JoinColumn(name = "cachorros")
private ONG ong;

}
