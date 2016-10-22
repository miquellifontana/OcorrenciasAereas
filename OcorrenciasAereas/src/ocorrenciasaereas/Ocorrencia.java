package ocorrenciasaereas;

import java.util.Date;

/**
 * Classe que representa um registro do arquivo ocorrencia.txt.
 *
 * TODO: Verificar como tratar os campos que no arquivo são String mas
 * possivelmente se comportarão como Boolean
 */
public class Ocorrencia {

    private Integer codigoOcorrencia;
    private String classificacao;
    private String tipo;
    private String localidade;
    private String uf;
    private String pais;
    private String aerodromo;
    private Date diaOcorrencia;
    private String horario;
    private String seraInvestigada;
    private String comandoInvestigacao;
    private String statusInvestigacao;
    private String numeroRelatorio;
    private String relatorioPublicado;
    private Date diaPublicacao;
    private Integer quantidadeRecomendacoes;
    private Integer aeronavesEnvolvidas;
    private Integer saidaPista;
    private Date diaExtracao;

    public Ocorrencia(String[] conteudo) {
        this.codigoOcorrencia = Utilities.convertStringToInteger(conteudo[0]);
        this.classificacao = conteudo[1];
        this.tipo = conteudo[2];
        this.localidade = conteudo[3];
        this.uf = conteudo[4];
        this.pais = conteudo[5];
        this.aerodromo = conteudo[6];
        this.diaOcorrencia = Utilities.convertStringToDate(conteudo[7]);
        this.horario = conteudo[8];
        this.seraInvestigada = conteudo[9];
        this.comandoInvestigacao = conteudo[10];
        this.statusInvestigacao = conteudo[11];
        this.numeroRelatorio = conteudo[12];
        this.relatorioPublicado = conteudo[13];
        this.diaPublicacao = Utilities.convertStringToDate(conteudo[14]);
        this.quantidadeRecomendacoes = Utilities.convertStringToInteger(conteudo[15]);
        this.aeronavesEnvolvidas = Utilities.convertStringToInteger(conteudo[16]);
        this.saidaPista = Utilities.convertStringToInteger(conteudo[17]);
        this.diaExtracao = Utilities.convertStringToDate(conteudo[18]);
    }

    @Override
    public String toString() {
        return "Ocorrencia: "
                + "|" + codigoOcorrencia
                + "|" + this.classificacao
                + "|" + this.tipo
                + "|" + this.localidade
                + "|" + this.diaOcorrencia
                + "|" + this.aeronavesEnvolvidas;
    }

    public Integer getCodigoOcorrencia() {
        return codigoOcorrencia;
    }

    public void setCodigoOcorrencia(Integer codigoOcorrencia) {
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getAerodromo() {
        return aerodromo;
    }

    public void setAerodromo(String aerodromo) {
        this.aerodromo = aerodromo;
    }

    public Date getDiaOcorrencia() {
        return diaOcorrencia;
    }

    public void setDiaOcorrencia(Date diaOcorrencia) {
        this.diaOcorrencia = diaOcorrencia;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getSeraInvestigada() {
        return seraInvestigada;
    }

    public void setSeraInvestigada(String seraInvestigada) {
        this.seraInvestigada = seraInvestigada;
    }

    public String getComandoInvestigacao() {
        return comandoInvestigacao;
    }

    public void setComandoInvestigacao(String comandoInvestigacao) {
        this.comandoInvestigacao = comandoInvestigacao;
    }

    public String getStatusInvestigacao() {
        return statusInvestigacao;
    }

    public void setStatusInvestigacao(String statusInvestigacao) {
        this.statusInvestigacao = statusInvestigacao;
    }

    public String getNumeroRelatorio() {
        return numeroRelatorio;
    }

    public void setNumeroRelatorio(String numeroRelatorio) {
        this.numeroRelatorio = numeroRelatorio;
    }

    public String getRelatorioPublicado() {
        return relatorioPublicado;
    }

    public void setRelatorioPublicado(String relatorioPublicado) {
        this.relatorioPublicado = relatorioPublicado;
    }

    public Date getDiaPublicacao() {
        return diaPublicacao;
    }

    public void setDiaPublicacao(Date diaPublicacao) {
        this.diaPublicacao = diaPublicacao;
    }

    public Integer getQuantidadeRecomendacoes() {
        return quantidadeRecomendacoes;
    }

    public void setQuantidadeRecomendacoes(Integer quantidadeRecomendacoes) {
        this.quantidadeRecomendacoes = quantidadeRecomendacoes;
    }

    public Integer getAeronavesEnvolvidas() {
        return aeronavesEnvolvidas;
    }

    public void setAeronavesEnvolvidas(Integer aeronavesEnvolvidas) {
        this.aeronavesEnvolvidas = aeronavesEnvolvidas;
    }

    public Integer getSaidaPista() {
        return saidaPista;
    }

    public void setSaidaPista(Integer saidaPista) {
        this.saidaPista = saidaPista;
    }

    public Date getDiaExtracao() {
        return diaExtracao;
    }

    public void setDiaExtracao(Date diaExtracao) {
        this.diaExtracao = diaExtracao;
    }
}
