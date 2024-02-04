package trabalho_ed_mf;

/**
 * Enumeração que representa os diferentes tipos de movimentos disponíveis para os bots.
 */
public enum MovementEnum {
    /**
     * Caminho mais curto por peso em direção a um destino.
     */
    SHORTESTWEIGHTPATH,
    /**
     * Arvore geradora de custo mínimo.
     */
    SHORTESTCONEECTEDPATH,
    /**
     * Caminho mais curto por conexões em direção a um destino.
     */
    SHORTESTCONNECTIONPATH
}
