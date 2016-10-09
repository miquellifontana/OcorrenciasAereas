package ocorrenciasaereas;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.net.URL;

/**
 * Interface para gerenciar obtenção de dados de um .csv.
 */
public interface Gerenciador {
    /**
     * Carrega todo o conteúdo do endereço da URL em uma Coleção de objetos.
     *
     * @param enderecoURL
     *   Endereço da URL
     */
    public void carregaConteudo(String enderecoURL);
    
    /**
     * Carrega o input stream a partir de uma URL
     * 
     * @param url 
     *      URL de onde o input stream será carregado
     * @return 
     *      Input Stream do arquivo do objeto
     *
     * @throws java.io.IOException
     */
    public CSVReader loadFromURL(URL url) throws IOException;
    
    /**
     * Metodo para fazer o parse do input stream e retorna-los parseado
     *
     * @param reader
     *      Input Stream que será utilizado como referencia de conteudo
     *
     * @throws java.io.IOException
     */
    public void parseFromInputStream(CSVReader reader) throws IOException;
    
}
