package ocorrenciasaereas.ui;

import java.text.SimpleDateFormat;
import ocorrenciasaereas.dados.Aeronave;
import ocorrenciasaereas.dados.Ocorrencia;

/**
 * Classe que representa o modelo para exibição em tela.
 *
 */
public class OcorrenciaDTO {

    private int codigoOcorrencia;
    private String classificacao;
    private String tipo;
    private String localidade;
    private String uf;
    private String dataOcorrencia;
    private Integer quantidadeFatalidades;

    /**
     * Cria um novo OcorreciaDTO a partir de uma Ocorrência.
     *
     * @param ocorrencia usado para obter os outros atributos :D
     */
    public OcorrenciaDTO(Ocorrencia ocorrencia) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        this.codigoOcorrencia = ocorrencia.getCodigoOcorrencia();
        this.classificacao = ocorrencia.getClassificacao();
        this.tipo = ocorrencia.getTipo();
        this.localidade = ocorrencia.getLocalidade();
        this.uf = ocorrencia.getUf();
        this.dataOcorrencia = dateFormat.format(ocorrencia.getDiaOcorrencia());
        this.quantidadeFatalidades = 0;

        for (Aeronave aeronaveEnvolvida : ocorrencia.getAeronavesEnvolvidas()) {
            quantidadeFatalidades += aeronaveEnvolvida.getQuantidadeFatalidades();
        }
    }

    @Override
    public String toString() {
        return this.codigoOcorrencia
                + "|" + this.classificacao
                + "|" + this.tipo
                + "|" + this.localidade
                + "|" + this.uf
                + "|" + this.dataOcorrencia
                + "|" + this.quantidadeFatalidades;
    }

    public int getCodigoOcorrencia() {
        return codigoOcorrencia;
    }

    public void setCodigoOcorrencia(int codigoOcorrencia) {
        this.codigoOcorrencia = codigoOcorrencia;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getDataOcorrencia() {
        return dataOcorrencia;
    }

    public void setDataOcorrencia(String dataOcorrencia) {
        this.dataOcorrencia = dataOcorrencia;
    }

    public Integer getQuantidadeFatalidades() {
        return quantidadeFatalidades;
    }

    public void setQuantidadeFatalidades(Integer quantidadeFatalidades) {
        this.quantidadeFatalidades = quantidadeFatalidades;
    }

    /**
     * Retorna um Arranjo de Object com os valores de cada atributo do objeto.
     *
     * @return Object[] onde cada posição representa o valor de um atributo da
     * classe
     */
    public Object[] atributosToArray() {
        Object[] campos = new Object[7];

        campos[0] = this.codigoOcorrencia;
        campos[1] = this.classificacao;
        campos[2] = this.tipo;
        campos[3] = this.localidade;
        campos[4] = this.uf;
        campos[5] = this.dataOcorrencia;
        campos[6] = this.quantidadeFatalidades;

        return campos;
    }
}
