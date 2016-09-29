package ocorrenciasaereas;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe para deixar em memória todas as ocorrências do arquivo .csv.
 * 
 */
public class GerenciadorOcorrencias extends GerenciadorBasico {
    private Collection<Ocorrencia> ocorrencias;
    
    public GerenciadorOcorrencias(Collection<Ocorrencia> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }

    public GerenciadorOcorrencias() {
        this.ocorrencias = new ArrayList<>();
    }

    public Collection<Ocorrencia> getOcorrencia() {
        return ocorrencias;
    }

    public void setOcorrencia(Collection<Ocorrencia> ocorrencia) {
        this.ocorrencias = ocorrencia;
    }
    
    @Override
    public void carregaConteudo() {
        try {
            System.out.println("carrega conteudo ocorrencias");
            this.parseFromInputStream(this.loadFromURL(new URL("http://www.cenipa.aer.mil.br/cenipa/Anexos/article/1451/ocorrencia.csv")));
        } catch (IOException ex) {
            Logger.getLogger(GerenciadorOcorrencias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void parseFromInputStream(CSVReader reader) throws IOException{
        System.out.println("parse ocorrencia");
        String[] line;
        while ((line = reader.readNext()) != null) {
            Ocorrencia ocorrencia = new Ocorrencia(line);
            this.ocorrencias.add(ocorrencia);
        }
    }

}
