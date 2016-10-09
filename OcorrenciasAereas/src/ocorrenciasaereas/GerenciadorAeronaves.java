package ocorrenciasaereas;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Implementação de um {@link Gerenciador} para a classe {@link Aeronave}.
 *  
 *  Sua utilização requer um endereço de uma URL
 */
public class GerenciadorAeronaves extends GerenciadorBasico {

    private Collection<Aeronave> aeronaves;

    public GerenciadorAeronaves() {
        this.aeronaves = new ArrayList<>();
    }

    public Collection<Aeronave> getAeronaves() {
        return aeronaves;
    }

    public void setAeronaves(Collection<Aeronave> aeronaves) {
        this.aeronaves = aeronaves;
    }

    @Override
    public void parseFromInputStream(CSVReader reader) throws IOException {
        System.out.println("parse aeronave");
        String[] line;
        while ((line = reader.readNext()) != null) {
            Aeronave aeronave = new Aeronave(line);
            this.aeronaves.add(aeronave);
        }
    }
}
