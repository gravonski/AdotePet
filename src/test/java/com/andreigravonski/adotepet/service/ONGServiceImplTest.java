package com.andreigravonski.adotepet.service;

import com.andreigravonski.adotepet.model.ONG;
import com.andreigravonski.adotepet.repository.ONGRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Habilita o framework Mockito
class ONGServiceImplTest {

    @Mock // Diz ao Mockito: "Crie um dublê falso do ONGRepository"
    private ONGRepository ongRepository;

    @InjectMocks // Diz ao Mockito: "Crie uma instância real do ONGServiceImpl e injete os dublês (@Mock) nela"
    private ONGServiceImpl ongService;

    @Test
        // Marca este método como um caso de teste que o JUnit deve executar
    void deveBuscarOngPorIdComSucesso() {
        // --- ARRANGE (Arrumar o cenário) ---

        // 1. Crie um objeto ONG falso que será o nosso resultado esperado.
        ONG ongFalsa = new ONG();
        ongFalsa.setId(1L);
        ongFalsa.setNome("ONG de Teste");

        // 2. Dê o roteiro para o nosso dublê:
        // "QUANDO (when) o método findById com o ID 1L for chamado no ongRepository..."
        when(ongRepository.findById(1L)).thenReturn(Optional.of(ongFalsa)); // "...ENTÃO (thenReturn) retorne um Optional contendo nossa ONG falsa."

        // --- ACT (Agir / Executar a ação) ---

        // 3. Chame o método real do nosso serviço que queremos testar.
        ONG ongResultado = ongService.buscarPorId(1L);

        // --- ASSERT (Afirmar / Verificar o resultado) ---

        // 4. Verifique se o resultado é o que esperávamos.
        Assertions.assertNotNull(ongResultado); // O resultado não pode ser nulo.
        Assertions.assertEquals("ONG de Teste", ongResultado.getNome()); // O nome deve ser "ONG de Teste".
        Assertions.assertEquals(1L, ongResultado.getId()); // O ID deve ser 1.
    }

    // Futuramente, adicionaremos outro @Test para o cenário de falha (quando o ID não é encontrado).
    @Test
    void deveLancarExcecaoAoBuscarIdInexistente() {
        // --- ARRANGE (Arrumar o cenário) ---

        // 1. Dê o roteiro para o nosso dublê:
        // "QUANDO o método findById com o ID 99L (um ID inexistente) for chamado..."
        when(ongRepository.findById(99L)).thenReturn(Optional.empty()); // "...ENTÃO retorne um Optional VAZIO."

        // --- ACT & ASSERT (Agir e Verificar ao mesmo tempo) ---

        // 2. Aqui está a mágica do teste de exceção:
        // Nós afirmamos (assert) que uma exceção (Throws) do tipo RuntimeException.class SERÁ LANÇADA
        // quando executarmos o código dentro do lambda "() -> ongService.buscarPorId(99L)".
        assertThrows(RuntimeException.class, () -> {
            ongService.buscarPorId(99L);
        });
    }
}