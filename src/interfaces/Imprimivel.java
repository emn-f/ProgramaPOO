// Pacote para organizar as classes do projeto.
package interfaces;

/**
 * Interface que define um contrato para objetos que podem ser impressos.
 * Garante que qualquer classe que a implemente terá um método para exibir um resumo de suas informações.
 * Isso é útil para o polimorfismo nos relatórios.
 */
public interface Imprimivel {
    /**
     * Método que deve ser implementado para retornar um resumo formatado do objeto.
     */
    void imprimirResumo();
}