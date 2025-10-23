package com.andreigravonski.adotepet.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

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

@Lob
@Column
private String fotoUrl;

private String localizacao;

private String descricao;

@Enumerated(EnumType.STRING)
private StatusDenuncia status;

private LocalDateTime dataHora;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "ong_id")
private ONG ong;
}
