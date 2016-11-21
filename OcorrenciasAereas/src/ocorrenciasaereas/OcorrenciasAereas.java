package ocorrenciasaereas;

import ocorrenciasaereas.dados.Aeronave;
import ocorrenciasaereas.dados.Ocorrencia;
import ocorrenciasaereas.ui.OcorrenciaDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import ocorrenciasaereas.ui.PrincipalUI;
import ocorrenciasaereas.dados.GerenciadorAeronaves;
import ocorrenciasaereas.dados.GerenciadorOcorrencias;

/**
 * Classe que Controla a lógica da Aplicação.
 */
public class OcorrenciasAereas {

    private List<Aeronave> aeronaves;

    private List<Ocorrencia> ocorrencias;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PrincipalUI principalUI = new PrincipalUI();
    }

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

    public List<OcorrenciaDTO> filtrarCodigoOcorrencia(List<OcorrenciaDTO> ocorrenciasDTOs, int valor) {
        List<OcorrenciaDTO> ocorrenciasFiltradas = new ArrayList<>();

        for (OcorrenciaDTO ocorrenciaDTO : ocorrenciasDTOs) {
            if (valor == ocorrenciaDTO.getCodigoOcorrencia()) {
                ocorrenciasFiltradas.add(ocorrenciaDTO);
            }
        }

        return ocorrenciasFiltradas;
    }

    /**
     * Filtra a Quantidade de Fatalidades das OcorrenciasDTO's informadas.
     *
     * @param ocorrenciasDTOs lista de Ocorrencias a serem filtradas
     * @param valorFiltrar falor a ser filtrado
     * @param tipoComparacao tipo de comparação a ser feita: 0: =, 1: >, 2: <
     *
     * @return lista com os elementos filtrados
     */
    public List<OcorrenciaDTO> filtrarQuantidadeFatalidades(List<OcorrenciaDTO> ocorrenciasDTOs,
            Integer valorFiltrar, int tipoComparacao) {
        List<OcorrenciaDTO> ocorrenciasFiltradas = new ArrayList<>();

        for (OcorrenciaDTO ocorrenciaDTO : ocorrenciasDTOs) {
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

    public List<OcorrenciaDTO> filtrarDataOcorrencia(List<OcorrenciaDTO> ocorrenciasDTOs, Date dataInicial, Date dataFinal) {
        List<OcorrenciaDTO> ocorrenciasFiltradas = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            for (OcorrenciaDTO ocorrenciaDTO : ocorrenciasDTOs) {
                boolean filtrarOcorrencia = false;
                boolean filtrarDataInicial = false;
                boolean filtrarDataFinal = false;
                Date dataOcorrencia = dateFormat.parse(ocorrenciaDTO.getDataOcorrencia());

                if (dataInicial != null) {
                    filtrarDataInicial = dataInicial.compareTo(dataOcorrencia) <= 0;
                }

                if (dataFinal != null) {
                    filtrarDataFinal = dataFinal.compareTo(dataOcorrencia) >= 0;
                }

                filtrarOcorrencia = (dataInicial == null && filtrarDataFinal)
                        || (dataFinal == null && filtrarDataInicial)
                        || (filtrarDataFinal && filtrarDataInicial);

                if (filtrarOcorrencia) {
                    ocorrenciasFiltradas.add(ocorrenciaDTO);
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(OcorrenciasAereas.class.getName()).log(Level.SEVERE, null, ex);
            return ocorrenciasDTOs;
        }

        return ocorrenciasFiltradas;
    }

    public List<OcorrenciaDTO> filtrarTipoOcorrencia(List<OcorrenciaDTO> ocorrenciasDTOs, String tipo) {
        List<OcorrenciaDTO> ocorrenciasFiltradas = new ArrayList<>();

        for (OcorrenciaDTO ocorrenciaDTO : ocorrenciasDTOs) {
            if (ocorrenciaDTO.getTipo() != null && ocorrenciaDTO.getTipo().toUpperCase().contains(tipo.toUpperCase())) {
                ocorrenciasFiltradas.add(ocorrenciaDTO);
            }
        }
        return ocorrenciasFiltradas;
    }

    /**
     * Retorna um mapa contendo a quantidade de ocorrências registradas por ano.
     *
     * @param ocorrenciasFiltradas ocorrências consideradas.
     * @return um SortedMap contendo a quantidade de ocorrências registradas por
     * ano.
     */
    public SortedMap<Integer, Integer> getOcorrenciasPorAno(List<OcorrenciaDTO> ocorrenciasFiltradas) {
        SortedMap<Integer, Integer> mapOcorrenciasPorAno = new TreeMap<>();

        for (OcorrenciaDTO ocorrencia : ocorrenciasFiltradas) {
            Integer ano = Integer.valueOf(ocorrencia.getDataOcorrencia().substring(6, 10));

            Integer ocorrenciasPorAno = mapOcorrenciasPorAno.get(ano);
            if (ocorrenciasPorAno == null) {
                mapOcorrenciasPorAno.put(ano, 1);
            } else {
                mapOcorrenciasPorAno.put(ano, ocorrenciasPorAno + 1);
            }
        }

        return mapOcorrenciasPorAno;
    }
}
