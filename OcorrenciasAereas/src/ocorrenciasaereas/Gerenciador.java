/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocorrenciasaereas;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.net.URL;

/**
 *
 * @author guilhermen
 */
public interface Gerenciador {
    
    /**
     * Metodo para cuidar do carregamento de conteudo, seja ele local ou de uma URL especifica
     */
    public void carregaConteudo();
    
    /**
     * Carrega o input stream a partir de uma URL
     * 
     * @param url 
     *      URL de onde o input stream será carregado
     * @return 
     *      Input Stream do arquivo do objeto
     * @throws java.io.IOException
     */
    public CSVReader loadFromURL(URL url) throws IOException;
    
    /**
     * Metodo para fazer o parse do input stream e retorna-los parseado
     * @param reader
     *      Input Stream que será utilizado como referencia de conteudo
     * @throws java.io.IOException
     */
    public void parseFromInputStream(CSVReader reader) throws IOException;
    
}
