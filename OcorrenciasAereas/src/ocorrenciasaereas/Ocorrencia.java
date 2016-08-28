package ocorrenciasaereas;

import java.util.Date;

/**
 * Classe que representa um registro do arquivo ocorrencia.txt.
 * 
 * TODO: Verificar como tratar os campos que no arquivo são String mas 
 * possivelmente se comportarão como Boolean
 */
public class Ocorrencia {

    private int codigoOcorrencia;

    private String classificacao;

    private String tipo;

    private String localidade;

    private String uf;

    private String pais;

    private String aerodromo;

    private Date diaOcorrencia;

    private String horarioOcorrencia;

    private String seraInvestigada;

    private String comandoInvestigacao;

    private String numeroRelatorio;

    private String relatorioPublicado;

    private Date diaPublicacao;

    private Integer quantidadeRecomendacoes;

    private Integer aeronavesEnvolvidades;

    private Integer saidaPista;

    private Date diaExtracao;

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

    public String getHorarioOcorrencia() {
        return horarioOcorrencia;
    }

    public void setHorarioOcorrencia(String horarioOcorrencia) {
        this.horarioOcorrencia = horarioOcorrencia;
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

    public Integer getAeronavesEnvolvidades() {
        return aeronavesEnvolvidades;
    }

    public void setAeronavesEnvolvidades(Integer aeronavesEnvolvidades) {
        this.aeronavesEnvolvidades = aeronavesEnvolvidades;
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
