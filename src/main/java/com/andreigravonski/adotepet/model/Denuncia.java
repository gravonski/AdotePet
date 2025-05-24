package com.andreigravonski.adotepet.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table (name = "tb_denuncia")
public class Denuncia {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String fotoUrl;

private String localizacao;

private String descricao;

private Enum status;

private LocalDateTime dataHora;
}
