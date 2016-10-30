package ocorrenciasaereas;

import java.util.Date;

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
    private Date dataOcorrencia;
    private Integer quantidadeFatalidades;

    /**
     *
     * @param aeronave usado para obter as quantidades de fatalidades
     * @param ocorrencia usado para obter os outros atributos :D
     */
    public OcorrenciaDTO(Aeronave aeronave, Ocorrencia ocorrencia) {
        this.codigoOcorrencia = ocorrencia.getCodigoOcorrencia();
        this.classificacao = ocorrencia.getClassificacao();
        this.tipo = ocorrencia.getTipo();
        this.localidade = ocorrencia.getLocalidade();
        this.uf = ocorrencia.getUf();
        this.quantidadeFatalidades = aeronave.getQuantidadeFatalidades();
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

    public Date getDataOcorrencia() {
        return dataOcorrencia;
    }

    public void setDataOcorrencia(Date dataOcorrencia) {
        this.dataOcorrencia = dataOcorrencia;
    }

    public Integer getQuantidadeFatalidades() {
        return quantidadeFatalidades;
    }

    public void setQuantidadeFatalidades(Integer quantidadeFatalidades) {
        this.quantidadeFatalidades = quantidadeFatalidades;
    }

}
