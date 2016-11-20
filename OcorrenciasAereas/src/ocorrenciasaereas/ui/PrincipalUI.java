package ocorrenciasaereas.ui;

import java.awt.Cursor;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;
import javax.swing.text.NumberFormatter;
import ocorrenciasaereas.OcorrenciaDTO;
import ocorrenciasaereas.Ocorrencias;

/**
 * Interface Gráfica Principal.
 */
public class PrincipalUI extends javax.swing.JFrame {

    private List<OcorrenciaDTO> ocorrenciaDTOs;

    private Ocorrencias ocorrencias;

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
        ocorrenciaDTOs = new ArrayList<>();
        ocorrencias = new Ocorrencias();
        setLocationRelativeTo(null);

        comboComparacaoQtdFatalidades.addItem("=");
        comboComparacaoQtdFatalidades.addItem(">");
        comboComparacaoQtdFatalidades.addItem("<");

        numberFormatter.setValueClass(Integer.class);
        numberFormatter.setAllowsInvalid(false);

        dateFormatter.setValueClass(Date.class);

        fillTable();
        setVisible(true);

    }

    private void atualizar() {
        ocorrenciaDTOs = ocorrencias.obtemDadosParaExibicao();
        filtrarOcorrencias();
        fillTable();

    }

    private void filtrarOcorrencias() {
        // Filtro Código Ocorrencia
        if (filtroCodigo.getText() != null && !filtroCodigo.getText().trim().equals("")) {
            ocorrenciaDTOs = ocorrencias.filtrarCodigoOcorrencia(ocorrenciaDTOs,
                    Integer.valueOf(filtroCodigo.getValue().toString()));
        }

        if (filtroDataFinal.getValue() != null || filtroDataInicial.getValue() != null) {
            ocorrenciaDTOs = ocorrencias.filtrarDataOcorrencia(
                    ocorrenciaDTOs, (Date) filtroDataInicial.getValue(), (Date) filtroDataFinal.getValue());
        }
        // Filtro de Quantidade de fatalidades
        if (filtroQuantidadeFatalidades.getValue() != null) {
            ocorrenciaDTOs = ocorrencias.filtrarQuantidadeFatalidades(
                    ocorrenciaDTOs, (Integer) filtroQuantidadeFatalidades.getValue(),
                    comboComparacaoQtdFatalidades.getSelectedIndex());
        }
    }

    private void fillTable() {
        String columnNames[] = new String[]{
            "Código", "Classificação", "Tipo", "Localidade", "UF", "Data", "Fatalidades"
        };

        Object[][] tableRows = new Object[this.ocorrenciaDTOs.size()][columnNames.length];

        this.setCursor(new Cursor(Cursor.WAIT_CURSOR)); // **CURSOR DE ESPERA

        for (int i = 0; i < tableRows.length; i++) {
            tableRows[i] = ocorrenciaDTOs.get(i).atributosToArray();
        }
        tableOcorrencia.setModel(new DefaultTableModel(tableRows, columnNames));

        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // **CURSOR DEFAULT
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
        buttonAtualizar = new javax.swing.JButton();
        menuBarPrincipal = new javax.swing.JMenuBar();
        menuConfiguracoes = new javax.swing.JMenu();
        menuURLS = new javax.swing.JMenuItem();
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

        javax.swing.GroupLayout panelFiltrosLayout = new javax.swing.GroupLayout(panelFiltros);
        panelFiltros.setLayout(panelFiltrosLayout);
        panelFiltrosLayout.setHorizontalGroup(
            panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltrosLayout.createSequentialGroup()
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelFiltroData)
                    .addGroup(panelFiltrosLayout.createSequentialGroup()
                        .addComponent(labelFiltros)
                        .addGap(85, 85, 85)
                        .addComponent(labelFiltroCodigo))
                    .addComponent(labelFiltroQuantidadeFatalidades))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFiltrosLayout.createSequentialGroup()
                        .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFiltrosLayout.createSequentialGroup()
                                .addComponent(comboComparacaoQtdFatalidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filtroQuantidadeFatalidades))
                            .addComponent(filtroCodigo))
                        .addGap(530, 530, 530))
                    .addGroup(panelFiltrosLayout.createSequentialGroup()
                        .addComponent(filtroDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelFiltroDataFinal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filtroDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addContainerGap(27, Short.MAX_VALUE))
        );

        buttonAtualizar.setText("Atualizar");
        buttonAtualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonAtualizarMouseClicked(evt);
            }
        });

        menuConfiguracoes.setText("Configurações");

        menuURLS.setText("URLS");
        menuConfiguracoes.add(menuURLS);

        menuBarPrincipal.add(menuConfiguracoes);

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
                        .addComponent(scrollPaneOcorrencia)
                        .addGap(12, 12, 12))
                    .addComponent(panelFiltros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelFiltros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonAtualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneOcorrencia, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private javax.swing.JComboBox<String> comboComparacaoQtdFatalidades;
    private javax.swing.JFormattedTextField filtroCodigo;
    private javax.swing.JFormattedTextField filtroDataFinal;
    private javax.swing.JFormattedTextField filtroDataInicial;
    private javax.swing.JFormattedTextField filtroQuantidadeFatalidades;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JLabel labelFiltroCodigo;
    private javax.swing.JLabel labelFiltroData;
    private javax.swing.JLabel labelFiltroDataFinal;
    private javax.swing.JLabel labelFiltroQuantidadeFatalidades;
    private javax.swing.JLabel labelFiltros;
    private javax.swing.JMenuBar menuBarPrincipal;
    private javax.swing.JMenu menuConfiguracoes;
    private javax.swing.JMenu menuSair;
    private javax.swing.JMenuItem menuURLS;
    private javax.swing.JPanel panelFiltros;
    private javax.swing.JScrollPane scrollPaneOcorrencia;
    private javax.swing.JTable tableOcorrencia;
    // End of variables declaration//GEN-END:variables
}
