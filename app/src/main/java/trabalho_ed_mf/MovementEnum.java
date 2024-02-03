package trabalho_ed_mf;

/**
 * Enumeração que representa os diferentes tipos de movimentos disponíveis para os bots.
 */
public enum MovementEnum {
    /**
     * Caminho mais curto em direção a um destino.
     */
    SHORTESTPATH,
    /**
     * Arvore geradora de custo mínimo.
     */
    SHORTESTCONEECTEDPATH,
    /**
     * Caminho aleatório.
     */
    RANDOMPATH
}
