package ocorrenciasaereas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ocorrenciasaereas.dados.GerenciadorAeronaves;
import ocorrenciasaereas.dados.GerenciadorOcorrencias;

/**
 * Classe que controla a Lógica de Negócios
 */
public class Ocorrencias {

    private List<Aeronave> aeronaves;

    private List<Ocorrencia> ocorrencias;

    /**
     * Carrega o conteúdo de Ocorrências e Aeronaves da URL.
     *
     * Processa os dados obtidos para serem exibidos.
     *
     * @return Lista com os dados das ocorrências processados para exibição
     */
    public List<OcorrenciaDTO> obtemDadosParaExibicao() {
        GerenciadorAeronaves gerenciadorDeAeronaves = new GerenciadorAeronaves();
        GerenciadorOcorrencias gerenciadorDeOcorrencias = new GerenciadorOcorrencias();

        gerenciadorDeOcorrencias.carregaConteudo(
                "http://www.cenipa.aer.mil.br/cenipa/Anexos/article/1451/ocorrencia.csv");
        gerenciadorDeAeronaves.carregaConteudo(
                "http://www.cenipa.aer.mil.br/cenipa/Anexos/article/1451/aeronave.csv");

        aeronaves = gerenciadorDeAeronaves.getAeronaves();
        ocorrencias = gerenciadorDeOcorrencias.getOcorrencia();

        vincularAeronavesComOcorrencias();

        List<OcorrenciaDTO> ocorrenciaDTOs = criarOcorrenciaDTOs();

        return ocorrenciaDTOs;
    }

    private void vincularAeronavesComOcorrencias() {
        Map<Integer, List<Aeronave>> mapOcorrenciaAeronaves = new HashMap<>();
        //vincula aeronave com ocrrencia
        for (Aeronave aeronave : aeronaves) {
            List<Aeronave> aeronavesDaOcorrencia = mapOcorrenciaAeronaves.get(aeronave.getCodigoOcorrencia());

            if (aeronavesDaOcorrencia == null) {
                aeronavesDaOcorrencia = new ArrayList<>();
                aeronavesDaOcorrencia.add(aeronave);
            }

            mapOcorrenciaAeronaves.put(aeronave.getCodigoOcorrencia(), aeronavesDaOcorrencia);
        }

        for (Ocorrencia ocorrencia : ocorrencias) {
            ocorrencia.setAeronavesEnvolvidas(mapOcorrenciaAeronaves.get(ocorrencia.getCodigoOcorrencia()));

        }

    }

    private List<OcorrenciaDTO> criarOcorrenciaDTOs() {
        List<OcorrenciaDTO> ocorrenciaDTOs = new ArrayList<>();
        for (Ocorrencia ocorrencia : ocorrencias) {
            OcorrenciaDTO ocorrenciaDTO = new OcorrenciaDTO(ocorrencia);
            ocorrenciaDTOs.add(ocorrenciaDTO);
        }

        return ocorrenciaDTOs;
    }

    /**
     * Filtra a Quantidade de Fatalidades das OcorrenciasDTO's informadas.
     *
     * @param ocorrenciasDTO lista de Ocorrencias a serem filtradas
     * @param valorFiltrar falor a ser filtrado 
     * @param tipoComparacao tipo de comparação a ser feita: 0: =, 1: >, 2: <
     *
     * @return lista com os elementos filtrados
     */
    public List<OcorrenciaDTO> filtrarQuantidadeFatalidades(List<OcorrenciaDTO> ocorrenciasDTO,
            Integer valorFiltrar, int tipoComparacao) {
        List<OcorrenciaDTO> ocorrenciasFiltradas = new ArrayList<>();

        for (OcorrenciaDTO ocorrenciaDTO : ocorrenciasDTO) {
            boolean filtrarRegistro = false;
            switch (tipoComparacao) {
                case 0:
                    if (ocorrenciaDTO.getQuantidadeFatalidades().equals(valorFiltrar)) {
                        filtrarRegistro = true;
                    }
                    break;
                case 1:
                    if (ocorrenciaDTO.getQuantidadeFatalidades().compareTo(valorFiltrar) > 0) {
                        filtrarRegistro = true;
                    }
                    break;
                case 2:
                    if (ocorrenciaDTO.getQuantidadeFatalidades().compareTo(valorFiltrar) < 0) {
                        filtrarRegistro = true;
                    }
                    break;
                default:
                    if (ocorrenciaDTO.getQuantidadeFatalidades().equals(valorFiltrar)) {
                        filtrarRegistro = true;
                    }
                    break;
            }

            if (filtrarRegistro) {
                ocorrenciasFiltradas.add(ocorrenciaDTO);
            }
        }

        return ocorrenciasFiltradas;
    }
}
