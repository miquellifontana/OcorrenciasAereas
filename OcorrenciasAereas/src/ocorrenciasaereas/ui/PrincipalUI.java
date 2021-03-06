package ocorrenciasaereas.ui;

import java.awt.Cursor;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.SortedMap;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javax.swing.JDialog;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;
import javax.swing.text.NumberFormatter;
import ocorrenciasaereas.OcorrenciasAereas;

/**
 * Interface Gráfica Principal.
 */
public class PrincipalUI extends javax.swing.JFrame {

    private List<OcorrenciaDTO> ocorrenciaDTOs;

    private OcorrenciasAereas ocorrenciasAereas;

    private ResourceBundle bundle;

    /**
     * NumberFormatter para ser utilizado em campos numéricos.
     */
    private final NumberFormatter numberFormatter = new NumberFormatter(NumberFormat.getInstance()) {
        @Override
        public Object stringToValue(String string)
                throws ParseException {
            if (string == null || string.length() == 0) {
                return null;
            }
            return super.stringToValue(string);
        }
    };

    /**
     * DateFormatter utilizado em campos de data.
     */
    private final DateFormatter dateFormatter = new DateFormatter(new SimpleDateFormat("dd/MM/yyyy")) {
        @Override
        public Object stringToValue(String text) throws ParseException {
            if (text == null || text.length() == 0) {
                return null;
            }
            return super.stringToValue(text);
        }
    };

    public PrincipalUI() {
        initComponents();
        init();
    }

    private void init() {
        bundle = OcorrenciasAereas.bundle;

        ocorrenciasAereas = new OcorrenciasAereas();
        ocorrenciaDTOs = new ArrayList<>();
        setLocationRelativeTo(null);

        comboComparacaoQtdFatalidades.addItem("=");
        comboComparacaoQtdFatalidades.addItem(">");
        comboComparacaoQtdFatalidades.addItem("<");

        numberFormatter.setValueClass(Integer.class);
        numberFormatter.setAllowsInvalid(false);

        dateFormatter.setValueClass(Date.class);
        preencherUIComProperties();

        fillTable();
        setVisible(true);

    }

    private void preencherUIComProperties() {
        this.setTitle(bundle.getString("PrincipalUI.windowTitle"));
        labelSubtitulo.setText(bundle.getString("PrincipalUI.subtitulo"));
        menuSair.setText(bundle.getString("PrincipalUI.menuSair"));

        labelFiltros.setText(bundle.getString("PrincipalUI.labelFiltros"));
        labelFiltroCodigo.setText(bundle.getString("PrincipalUI.labelFiltroCodigo"));
        labelFiltroQuantidadeFatalidades.setText(bundle.getString("PrincipalUI.labelFiltroQuantidadeFatalidades"));
        labelFiltroData.setText(bundle.getString("PrincipalUI.labelFiltroData"));
        labelFiltroDataFinal.setText(bundle.getString("PrincipalUI.labelFiltroDataFinal"));
        labelFiltroTipo.setText(bundle.getString("PrincipalUI.labelFiltroTipo"));

        buttonAtualizar.setText(bundle.getString("PrincipalUI.buttonAtualizar"));
        buttonPlotarGraficoFatalidades.setText(bundle.getString("PrincipalUI.buttonPlotarGraficoFatalidades"));
        buttonPlotarGraficoOcorrencias.setText(bundle.getString("PrincipalUI.buttonPlotarGraficoOcorrencias"));
    }

    private void atualizar() {
        ocorrenciaDTOs = ocorrenciasAereas.obtemDadosParaExibicao();
        filtrarOcorrencias();
        fillTable();
    }

    private void filtrarOcorrencias() {
        // Filtro Código Ocorrencia
        if (filtroCodigo.getText() != null && !filtroCodigo.getText().trim().equals("")) {
            ocorrenciaDTOs = ocorrenciasAereas.filtrarCodigoOcorrencia(ocorrenciaDTOs,
                    Integer.valueOf(filtroCodigo.getValue().toString()));
        }

        // Filtro Data
        if (filtroDataFinal.getValue() != null || filtroDataInicial.getValue() != null) {
            ocorrenciaDTOs = ocorrenciasAereas.filtrarDataOcorrencia(
                    ocorrenciaDTOs, (Date) filtroDataInicial.getValue(), (Date) filtroDataFinal.getValue());
        }
        // Filtro de Quantidade de fatalidades
        if (filtroQuantidadeFatalidades.getValue() != null) {
            ocorrenciaDTOs = ocorrenciasAereas.filtrarQuantidadeFatalidades(
                    ocorrenciaDTOs, (Integer) filtroQuantidadeFatalidades.getValue(),
                    comboComparacaoQtdFatalidades.getSelectedIndex());
        }

        // Filtro de Tipo de Ocorrencia
        if (filtroTipoOcorrencia.getText() != null && !filtroTipoOcorrencia.getText().trim().equals("")) {
            ocorrenciaDTOs = ocorrenciasAereas.filtrarTipoOcorrencia(ocorrenciaDTOs, filtroTipoOcorrencia.getText());
        }
    }

    private void fillTable() {
        String columnNames[] = new String[]{
            bundle.getString("tabelaOcorrencia.codigo"), bundle.getString("tabelaOcorrencia.classificacao"),
            bundle.getString("tabelaOcorrencia.tipo"), bundle.getString("tabelaOcorrencia.localidade"),
            bundle.getString("tabelaOcorrencia.UF"), bundle.getString("tabelaOcorrencia.data"),
            bundle.getString("tabelaOcorrencia.fatalidades")
        };

        Object[][] tableRows = new Object[this.ocorrenciaDTOs.size()][columnNames.length];

        this.setCursor(new Cursor(Cursor.WAIT_CURSOR)); // **CURSOR DE ESPERA

        for (int i = 0; i < tableRows.length; i++) {
            tableRows[i] = ocorrenciaDTOs.get(i).atributosToArray();
        }
        tableOcorrencia.setModel(new DefaultTableModel(tableRows, columnNames));

        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // **CURSOR DEFAULT
    }

    private void plotarGraficoOcorrenciasPorAno() {
        atualizar();
        SortedMap<Integer, Integer> ocorrenciasPorAno = ocorrenciasAereas.getOcorrenciasPorAno(ocorrenciaDTOs);

        GraficoLinha graficoLinha = new GraficoLinha(ocorrenciasPorAno);

        graficoLinha.setEixoXLabel(bundle.getString("graficoOcorrenciasPorAno.eixoxLabel"));
        graficoLinha.setEixoYLabel(bundle.getString("graficoOcorrenciasPorAno.eixoyLabel"));
        graficoLinha.setTitulo(bundle.getString("graficoOcorrenciasPorAno.titulo"));
        graficoLinha.setNomeSerie(bundle.getString("graficoOcorrenciasPorAno.nomeSerie"));

        JDialog janPl = new JDialog();
        JFXPanel fxPanel = new JFXPanel();
        janPl.add(fxPanel);
        janPl.setSize(1200, 720);
        janPl.setTitle(bundle.getString("graficoOcorrenciasPorAno.windowTitle"));
        janPl.setVisible(true);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                fxPanel.setScene(new Scene(graficoLinha.criarLineChart(), 1200, 720));
            }
        });
    }

    private void plotarGraficoFatalidadesPorAno() {
        atualizar();
        SortedMap<Integer, Integer> fatalidadesPorAno = ocorrenciasAereas.getFatalidadesPorAno(ocorrenciaDTOs);

        GraficoLinha graficoLinha = new GraficoLinha(fatalidadesPorAno);

        graficoLinha.setEixoXLabel(bundle.getString("graficoFatalidadesPorAno.eixoxLabel"));
        graficoLinha.setEixoYLabel(bundle.getString("graficoFatalidadesPorAno.eixoyLabel"));
        graficoLinha.setTitulo(bundle.getString("graficoFatalidadesPorAno.titulo"));
        graficoLinha.setNomeSerie(bundle.getString("graficoFatalidadesPorAno.nomeSerie"));

        JDialog janPl = new JDialog();
        JFXPanel fxPanel = new JFXPanel();
        janPl.add(fxPanel);
        janPl.setSize(1200, 720);
        janPl.setTitle(bundle.getString("graficoFatalidadesPorAno.windowTitle"));
        janPl.setVisible(true);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                fxPanel.setScene(new Scene(graficoLinha.criarLineChart(), 1200, 720));
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        scrollPaneOcorrencia = new javax.swing.JScrollPane();
        tableOcorrencia = new javax.swing.JTable();
        panelFiltros = new javax.swing.JPanel();
        labelFiltros = new javax.swing.JLabel();
        labelFiltroQuantidadeFatalidades = new javax.swing.JLabel();
        filtroQuantidadeFatalidades = new javax.swing.JFormattedTextField(numberFormatter);
        comboComparacaoQtdFatalidades = new javax.swing.JComboBox<>();
        labelFiltroCodigo = new javax.swing.JLabel();
        filtroCodigo = new javax.swing.JFormattedTextField(numberFormatter);
        labelFiltroData = new javax.swing.JLabel();
        filtroDataInicial = new javax.swing.JFormattedTextField(dateFormatter);
        labelFiltroDataFinal = new javax.swing.JLabel();
        filtroDataFinal = new javax.swing.JFormattedTextField(dateFormatter);
        labelFiltroTipo = new javax.swing.JLabel();
        filtroTipoOcorrencia = new javax.swing.JTextField();
        buttonAtualizar = new javax.swing.JButton();
        buttonPlotarGraficoOcorrencias = new javax.swing.JButton();
        buttonPlotarGraficoFatalidades = new javax.swing.JButton();
        labelSubtitulo = new javax.swing.JLabel("", SwingConstants.CENTER);
        menuBarPrincipal = new javax.swing.JMenuBar();
        menuSair = new javax.swing.JMenu();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableOcorrencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollPaneOcorrencia.setViewportView(tableOcorrencia);

        labelFiltros.setText("Filtros:");

        labelFiltroQuantidadeFatalidades.setText("Quantidade de Fatalidades");

        filtroQuantidadeFatalidades.setColumns(5);

        labelFiltroCodigo.setText("Código");

        filtroCodigo.setColumns(10);
        filtroCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtroCodigoActionPerformed(evt);
            }
        });

        labelFiltroData.setText("Data");

        filtroDataInicial.setColumns(10);

        labelFiltroDataFinal.setText("até");

        filtroDataFinal.setColumns(10);

        labelFiltroTipo.setText("Tipo");

        filtroTipoOcorrencia.setColumns(10);

        javax.swing.GroupLayout panelFiltrosLayout = new javax.swing.GroupLayout(panelFiltros);
        panelFiltros.setLayout(panelFiltrosLayout);
        panelFiltrosLayout.setHorizontalGroup(
            panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltrosLayout.createSequentialGroup()
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelFiltroTipo)
                    .addComponent(labelFiltroData)
                    .addGroup(panelFiltrosLayout.createSequentialGroup()
                        .addComponent(labelFiltros)
                        .addGap(85, 85, 85)
                        .addComponent(labelFiltroCodigo))
                    .addComponent(labelFiltroQuantidadeFatalidades))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFiltrosLayout.createSequentialGroup()
                        .addComponent(filtroDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelFiltroDataFinal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filtroDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(569, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFiltrosLayout.createSequentialGroup()
                        .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(filtroTipoOcorrencia, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelFiltrosLayout.createSequentialGroup()
                                .addComponent(comboComparacaoQtdFatalidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filtroQuantidadeFatalidades))
                            .addComponent(filtroCodigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE))
                        .addGap(530, 530, 530))))
        );
        panelFiltrosLayout.setVerticalGroup(
            panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltrosLayout.createSequentialGroup()
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFiltrosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelFiltros))
                    .addGroup(panelFiltrosLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelFiltroCodigo)
                            .addComponent(filtroCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filtroQuantidadeFatalidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboComparacaoQtdFatalidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFiltroQuantidadeFatalidades))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFiltroData)
                    .addComponent(filtroDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFiltroDataFinal)
                    .addComponent(filtroDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filtroTipoOcorrencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFiltroTipo))
                .addContainerGap())
        );

        buttonAtualizar.setText("buttonAtualizar");
        buttonAtualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonAtualizarMouseClicked(evt);
            }
        });

        buttonPlotarGraficoOcorrencias.setText("buttonGraficoOcorrencias");
        buttonPlotarGraficoOcorrencias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonPlotarGraficoOcorrenciasOcorrenciasMouseClicked(evt);
            }
        });

        buttonPlotarGraficoFatalidades.setText("buttonGraficoFatalidades");
        buttonPlotarGraficoFatalidades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonPlotarGraficoFatalidadesMouseClicked(evt);
            }
        });

        labelSubtitulo.setFont(new java.awt.Font("Noto Sans", 0, 24)); // NOI18N
        labelSubtitulo.setText("labelSubtitulo");
        labelSubtitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        menuSair.setText("Sair");
        menuSair.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                menuSairMenuSelected(evt);
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
        });
        menuBarPrincipal.add(menuSair);

        setJMenuBar(menuBarPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelSubtitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scrollPaneOcorrencia)
                        .addGap(12, 12, 12))
                    .addComponent(panelFiltros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(buttonPlotarGraficoOcorrencias, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonPlotarGraficoFatalidades, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(labelSubtitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAtualizar)
                    .addComponent(buttonPlotarGraficoOcorrencias)
                    .addComponent(buttonPlotarGraficoFatalidades))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneOcorrencia, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuSairMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_menuSairMenuSelected
        dispose();
    }//GEN-LAST:event_menuSairMenuSelected

    private void buttonAtualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonAtualizarMouseClicked
        atualizar();
    }//GEN-LAST:event_buttonAtualizarMouseClicked

    private void filtroCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtroCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filtroCodigoActionPerformed

    private void buttonPlotarGraficoOcorrenciasOcorrenciasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonPlotarGraficoOcorrenciasOcorrenciasMouseClicked
        plotarGraficoOcorrenciasPorAno();
    }//GEN-LAST:event_buttonPlotarGraficoOcorrenciasOcorrenciasMouseClicked

    private void buttonPlotarGraficoFatalidadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonPlotarGraficoFatalidadesMouseClicked
        plotarGraficoFatalidadesPorAno();
    }//GEN-LAST:event_buttonPlotarGraficoFatalidadesMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PrincipalUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAtualizar;
    private javax.swing.JButton buttonPlotarGraficoFatalidades;
    private javax.swing.JButton buttonPlotarGraficoOcorrencias;
    private javax.swing.JComboBox<String> comboComparacaoQtdFatalidades;
    private javax.swing.JFormattedTextField filtroCodigo;
    private javax.swing.JFormattedTextField filtroDataFinal;
    private javax.swing.JFormattedTextField filtroDataInicial;
    private javax.swing.JFormattedTextField filtroQuantidadeFatalidades;
    private javax.swing.JTextField filtroTipoOcorrencia;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JLabel labelFiltroCodigo;
    private javax.swing.JLabel labelFiltroData;
    private javax.swing.JLabel labelFiltroDataFinal;
    private javax.swing.JLabel labelFiltroQuantidadeFatalidades;
    private javax.swing.JLabel labelFiltroTipo;
    private javax.swing.JLabel labelFiltros;
    private javax.swing.JLabel labelSubtitulo;
    private javax.swing.JMenuBar menuBarPrincipal;
    private javax.swing.JMenu menuSair;
    private javax.swing.JPanel panelFiltros;
    private javax.swing.JScrollPane scrollPaneOcorrencia;
    private javax.swing.JTable tableOcorrencia;
    // End of variables declaration//GEN-END:variables
}
