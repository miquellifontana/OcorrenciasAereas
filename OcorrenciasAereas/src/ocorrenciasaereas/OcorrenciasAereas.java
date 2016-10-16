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

        gerenciadorDeOcorrencias.carregaConteudo(
                "http://www.cenipa.aer.mil.br/cenipa/Anexos/article/1451/ocorrencia.csv");
        gerenciadorDeAeronaves.carregaConteudo(
                "http://www.cenipa.aer.mil.br/cenipa/Anexos/article/1451/ocorrencia.csv");
    }
}
