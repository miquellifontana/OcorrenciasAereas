package ocorrenciasaereas;

/**
 * TODO: Documentar
 */
public class OcorrenciasAereas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GerenciadorAeronaves gerenciadorDeAeronaves = new GerenciadorAeronaves();
        GerenciadorOcorrencias gerenciadorDeOcorrencias = new GerenciadorOcorrencias();
        
        gerenciadorDeOcorrencias.carregaConteudo();
        gerenciadorDeAeronaves.carregaConteudo();
    }
    
}
