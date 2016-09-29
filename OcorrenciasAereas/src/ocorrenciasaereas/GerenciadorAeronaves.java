/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocorrenciasaereas;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guilhermen
 */
public class GerenciadorAeronaves extends GerenciadorBasico {
    private Collection<Aeronave> aeronaves;

    public GerenciadorAeronaves(Collection<Aeronave> aeronaves) {
        this.aeronaves = aeronaves;
    }

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
    public void carregaConteudo() {
        try {
            System.out.println("carrega conteudo aeronaves");
            this.parseFromInputStream(this.loadFromURL(new URL("http://www.cenipa.aer.mil.br/cenipa/Anexos/article/1451/aeronave.csv")));
        } catch (IOException ex) {
            Logger.getLogger(GerenciadorAeronaves.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void parseFromInputStream(CSVReader reader) throws IOException {
        System.out.println("parse aeronave");
        String[] line;
        while((line = reader.readNext()) != null) {
            Aeronave aeronave = new Aeronave(line);
            this.aeronaves.add(aeronave);
        }
    }
}
