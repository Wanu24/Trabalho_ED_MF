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
     * Caminho aleatório entre as localizações vizinhas.
     */
    RANDOMPATH,
    /**
     * Caminho mais longo do vértice, escolhendo o caminho mais longo entre as localizações vizinhas.
     */
    ATHELETICPATH
}
