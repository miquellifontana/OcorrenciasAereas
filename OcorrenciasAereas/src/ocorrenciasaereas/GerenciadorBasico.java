package ocorrenciasaereas;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementação Básica de um {@link Gerenciador} para obtenção de dados de um .csv.
 *
 */
public abstract class GerenciadorBasico implements Gerenciador {

    @Override
    public void carregaConteudo(String enderecoURL) {
        try {
            System.out.println("carrega conteudo");
            this.parseFromInputStream(this.loadFromURL(new URL(enderecoURL)));
        } catch (IOException ex) {
            Logger.getLogger(GerenciadorAeronaves.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public CSVReader loadFromURL(URL url) throws IOException {
        Reader source = new InputStreamReader(url.openStream());
        CSVReader reader = new CSVReader(source, ',');
        return reader;
    }
}
