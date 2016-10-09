package ocorrenciasaereas;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Implementação de um {@link Gerenciador} para a classe {@link Ocorrencia}.
 *
 *  Sua utilização requer um endereço de uma URL
 */
public class GerenciadorOcorrencias extends GerenciadorBasico {

    private Collection<Ocorrencia> ocorrencias;

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
    public void parseFromInputStream(CSVReader reader) throws IOException {
        System.out.println("parse ocorrencia");
        String[] line;
        while ((line = reader.readNext()) != null) {
            Ocorrencia ocorrencia = new Ocorrencia(line);
            this.ocorrencias.add(ocorrencia);
        }
    }
}
