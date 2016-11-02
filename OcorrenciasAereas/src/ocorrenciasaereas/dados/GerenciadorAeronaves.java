package ocorrenciasaereas.dados;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ocorrenciasaereas.Aeronave;
import ocorrenciasaereas.Ocorrencia;

/**
 * Implementação de um {@link Gerenciador} para a classe {@link Aeronave}.
 *
 * Sua utilização requer um endereço de uma URL
 */
public class GerenciadorAeronaves extends GerenciadorBasico {

    private List<Aeronave> aeronaves;

    public GerenciadorAeronaves() {
        this.aeronaves = new ArrayList<>();
    }

    public List<Aeronave> getAeronaves() {
        return aeronaves;
    }

    public void setAeronaves(List<Aeronave> aeronaves) {
        this.aeronaves = aeronaves;
    }

    @Override
    public void parseFromInputStream(CSVReader reader) throws IOException {
        System.out.println("parse aeronave");

        String[] line;
        reader.readNext();//Ignora a primeira de cabeçalho

        while ((line = reader.readNext()) != null) {
            Aeronave aeronave = new Aeronave(line);
            this.aeronaves.add(aeronave);
        }
    }
    
    /**
     * Retorna as Aeronaves da Ocorrência informada.
     *
     * @param ocorrencia
     *   Ocorreêcia
     *
     * @return
     *   Lista com as Aeronaves da Ocorrência informada
     */
    public List<Aeronave> obterAeronavesDaOcorrencia(Ocorrencia ocorrencia) {
        Integer codigoOcorrencia = ocorrencia.getCodigoOcorrencia();

        List<Aeronave> aeronavesEnvolvidas = new ArrayList<>();
        for (Aeronave aeronave : this.aeronaves) {
            if (aeronave.getCodigoOcorrencia().equals(codigoOcorrencia)) {
                aeronavesEnvolvidas.add(aeronave);
            }
        }

        return aeronavesEnvolvidas;
    }
}
