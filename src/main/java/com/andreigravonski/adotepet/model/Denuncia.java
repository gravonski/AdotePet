package com.andreigravonski.adotepet.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
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

@NotBlank(message = "A localização não pode estar vazia.")
private String localizacao;

@NotBlank(message = "A descrição deve ser preenchida para podermos ajudar!")
private String descricao;

@Enumerated(EnumType.STRING)
private StatusDenuncia status;

private LocalDateTime dataHora;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "ong_id")
private ONG ong;
}
