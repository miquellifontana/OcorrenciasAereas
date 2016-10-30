package ocorrenciasaereas;

import java.util.Date;

/**
 * Classe que representa um registro do arquivo aeronave.txt.
 *
 * TODO: Verificar como tratar os campos que no arquivo são String mas
 * possivelmente se comportarão como Boolean
 */
public class Aeronave {

    private Integer codigoAeronave; // 4
    private Integer codigoOcorrencia; //45602
    private String matricula; //PPGXE
    private Integer codigoOperador; //241
    private String equipamento; //AVIÃO
    private String fabricante; //"NEIVA INDUSTRIA AERONAUTICA"
    private String modelo; //56-C
    private String tipoMotor; //PISTÃO
    private Integer quantidadeMotores; //1
    private Integer pesoMaximoDecolagem; //660
    private Integer quantidadeAssentos; //2
    private Integer anoFabricacao; //1962
    private String paisRegistro; //BRASIL
    private String categoriaRegistro; //PRI
    private String categoriaAviacao; //INSTRUÇÃO
    private String origemVoo; // SDPW
    private String destinoVoo; //SDPW
    private String faseOperacao; //INDETERMINADA
    private String tipoOperacao; //INSTRUÇÃO
    private String nivelDano; //SUBSTANCIAL
    private Integer quantidadeFatalidades; //NULL
    private Date dia_extracao; //2016-07-30

    public Aeronave(String[] conteudo) {
        this.codigoAeronave = Utilities.convertStringToInteger(conteudo[0]);
        this.codigoOcorrencia = Utilities.convertStringToInteger(conteudo[1]); //45602
        this.matricula = conteudo[2]; //PPGXE
        this.codigoOperador = Utilities.convertStringToInteger(conteudo[3]); //241
        this.equipamento = conteudo[4]; //AVIÃO
        this.fabricante = conteudo[5]; //"NEIVA INDUSTRIA AERONAUTICA"
        this.modelo = conteudo[6]; //56-C
        this.tipoMotor = conteudo[7]; //PISTÃO
        this.quantidadeMotores = Utilities.convertStringToInteger(conteudo[8]); //1
        this.pesoMaximoDecolagem = Utilities.convertStringToInteger(conteudo[9]); //660
        this.quantidadeAssentos = Utilities.convertStringToInteger(conteudo[10]); //2
        this.anoFabricacao = Utilities.convertStringToInteger(conteudo[11]); //1962
        this.paisRegistro = conteudo[12]; //BRASIL
        this.categoriaRegistro = conteudo[13]; //PRI
        this.categoriaAviacao = conteudo[14]; //INSTRUÇÃO
        this.origemVoo = conteudo[15]; // SDPW
        this.destinoVoo = conteudo[16]; //SDPW
        this.faseOperacao = conteudo[17]; //INDETERMINADA
        this.tipoOperacao = conteudo[18]; //INSTRUÇÃO
        this.nivelDano = conteudo[19]; //SUBSTANCIAL
        this.quantidadeFatalidades = Utilities.convertStringToInteger(conteudo[20]);
        this.dia_extracao = Utilities.convertStringToDate(conteudo[21]); //2016-07-30
    }
	@Override
    	public String toString(){
       		return "Aeronave: "
                	+ "|" + this.codigoAeronave
                	+ "|" + this.codigoOcorrencia
                	+ "|" + this.fabricante
                	+ "|" + this.modelo
                	+ "|" + this.anoFabricacao
                	+ "|" + this.quantidadeFatalidades;                
   	}


    public Integer getCodigoAeronave() {
        return codigoAeronave;
    }

    public void setCodigoAeronave(Integer codigoAeronave) {
        this.codigoAeronave = codigoAeronave;
    }

    public Integer getCodigoOcorrencia() {
        return codigoOcorrencia;
    }

    public void setCodigoOcorrencia(Integer codigoOcorrencia) {
        this.codigoOcorrencia = codigoOcorrencia;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Integer getCodigoOperador() {
        return codigoOperador;
    }

    public void setCodigoOperador(Integer codigoOperador) {
        this.codigoOperador = codigoOperador;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipoMotor() {
        return tipoMotor;
    }

    public void setTipoMotor(String tipoMotor) {
        this.tipoMotor = tipoMotor;
    }

    public Integer getQuantidadeMotores() {
        return quantidadeMotores;
    }

    public void setQuantidadeMotores(Integer quantidadeMotores) {
        this.quantidadeMotores = quantidadeMotores;
    }

    public Integer getPesoMaximoDecolagem() {
        return pesoMaximoDecolagem;
    }

    public void setPesoMaximoDecolagem(Integer pesoMaximoDecolagem) {
        this.pesoMaximoDecolagem = pesoMaximoDecolagem;
    }

    public Integer getQuantidadeAssentos() {
        return quantidadeAssentos;
    }

    public void setQuantidadeAssentos(Integer quantidadeAssentos) {
        this.quantidadeAssentos = quantidadeAssentos;
    }

    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Integer anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public String getPaisRegistro() {
        return paisRegistro;
    }

    public void setPaisRegistro(String paisRegistro) {
        this.paisRegistro = paisRegistro;
    }

    public String getCategoriaRegistro() {
        return categoriaRegistro;
    }

    public void setCategoriaRegistro(String categoriaRegistro) {
        this.categoriaRegistro = categoriaRegistro;
    }

    public String getCategoriaAviacao() {
        return categoriaAviacao;
    }

    public void setCategoriaAviacao(String categoriaAviacao) {
        this.categoriaAviacao = categoriaAviacao;
    }

    public String getOrigemVoo() {
        return origemVoo;
    }

    public void setOrigemVoo(String origemVoo) {
        this.origemVoo = origemVoo;
    }

    public String getDestinoVoo() {
        return destinoVoo;
    }

    public void setDestinoVoo(String destinoVoo) {
        this.destinoVoo = destinoVoo;
    }

    public String getFaseOperacao() {
        return faseOperacao;
    }

    public void setFaseOperacao(String faseOperacao) {
        this.faseOperacao = faseOperacao;
    }

    public String getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(String tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public String getNivelDano() {
        return nivelDano;
    }

    public void setNivelDano(String nivelDano) {
        this.nivelDano = nivelDano;
    }

    public Integer getQuantidadeFatalidades() {
        return quantidadeFatalidades;
    }

    public void setQuantidadeFatalidades(Integer quantidadeFatalidades) {
        this.quantidadeFatalidades = quantidadeFatalidades;
    }

    public Date getDia_extracao() {
        return dia_extracao;
    }

    public void setDia_extracao(Date dia_extracao) {
        this.dia_extracao = dia_extracao;
    }
}
