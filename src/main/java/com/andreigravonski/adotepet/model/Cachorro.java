package com.andreigravonski.adotepet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_cachorros")
public class Cachorro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private int idade;

    private String raca;

    @Enumerated(EnumType.STRING) // Boa prática: Salva o nome do status, não o número
    private StatusDenuncia status; // Usa o Enum específico

    private String fotoUrl; // Renomeado de imagemUrl para consistência

    @Column(columnDefinition = "TEXT") // Permite textos mais longos
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY) // LAZY é mais performático para relacionamentos ManyToOne
    @JoinColumn(name = "ong_id") // Nome correto da coluna da chave estrangeira
    private ONG ong;

}