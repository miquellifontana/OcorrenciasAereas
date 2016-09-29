/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocorrenciasaereas;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

/**
 *
 * @author guilhermen
 */
public abstract class GerenciadorBasico implements Gerenciador {

    @Override
    public CSVReader loadFromURL(URL url) throws IOException {
        Reader source = new InputStreamReader(url.openStream());
        CSVReader reader = new CSVReader(source, ';');
        return reader;
    }
    
}
