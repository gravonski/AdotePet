package com.andreigravonski.adotepet.specification;

import com.andreigravonski.adotepet.model.Cachorro;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CachorroSpecification {

    // Este é o nosso primeiro "bloco de Lego" (ou "molho")
    public static Specification<Cachorro> porRaca(String raca) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("raca")), // Converte a coluna "raca" para minúsculas
                        "%" + raca.toLowerCase() + "%" // Converte o termo de busca e adiciona wildcards
                );
    }

    // Amanhã, adicionaremos mais blocos aqui, como porIdade(), porCidade(), etc.
}