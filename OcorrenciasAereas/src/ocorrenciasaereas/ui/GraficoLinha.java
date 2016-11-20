package ocorrenciasaereas.ui;

import java.util.Map;
import java.util.SortedMap;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * Classe utilitária para criação de um {@link LineChart}.
 */
public class GraficoLinha {

    private SortedMap<Integer, Integer> sortedMap;

    private String eixoXLabel;

    private String eixoYLabel;

    private String titulo;

    private String nomeSerie;

    private int xMin;

    private int yMin;

    private int xMax;

    private int yMax;

    /**
     * Cria um novo {@link GraficoLinha} a partir de um SortedMap. Seta os
     * valores mínimos e máximos dos eixos x e y.
     *
     * @param sortedMap
     */
    public GraficoLinha(SortedMap<Integer, Integer> sortedMap) {
        this.sortedMap = sortedMap;
        int i = 0;
        int maiorValorX = 0;
        int menorValorX = 0;

        int maiorValorY = 0;
        int manorValorY = -1;
        for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
            if (i == 0) {
                menorValorX = entry.getKey();
            }
            maiorValorX = entry.getKey();
            i++;

            if (entry.getValue() > maiorValorY) {
                maiorValorY = entry.getValue();
            }
            if (manorValorY == -1 || entry.getValue() < manorValorY) {
                manorValorY = entry.getValue();
            }
        }

        this.xMin = menorValorX;
        this.xMax = maiorValorX;
        this.yMin = manorValorY;
        this.yMax = maiorValorY;
    }

    /**
     * Retorna um {@link LineChart} a partir dos atributos da classe.
     *
     * @return LineChart Criado.
     */
    public LineChart criarLineChart() {
        final NumberAxis xAxis = new NumberAxis(
                this.getxMin(), this.getxMax(), 1);
        final NumberAxis yAxis = new NumberAxis(
                Math.max(this.getyMin() - (this.getyMin() % 20), 0),
                this.getyMax() + (this.getyMax() % 20), 20);

        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        xAxis.setLabel(this.getEixoXLabel());
        yAxis.setLabel(this.getEixoYLabel());
        lineChart.setTitle(this.getTitulo());

        XYChart.Series series1 = new XYChart.Series();
        series1.setName(this.getNomeSerie());

        for (int x = this.getxMin(); x < this.getxMax(); ++x) {
            series1.getData().add(new XYChart.Data(x, sortedMap.get(x)));
        }

        lineChart.getData().addAll(series1);
        return lineChart;
    }

    public String getEixoXLabel() {
        return eixoXLabel;
    }

    public void setEixoXLabel(String eixoXLabel) {
        this.eixoXLabel = eixoXLabel;
    }

    public String getEixoYLabel() {
        return eixoYLabel;
    }

    public void setEixoYLabel(String eixoYLabel) {
        this.eixoYLabel = eixoYLabel;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNomeSerie() {
        return nomeSerie;
    }

    public void setNomeSerie(String nomeSerie) {
        this.nomeSerie = nomeSerie;
    }

    public int getxMin() {
        return xMin;
    }

    public void setxMin(int xMin) {
        this.xMin = xMin;
    }

    public int getyMin() {
        return yMin;
    }

    public void setyMin(int yMin) {
        this.yMin = yMin;
    }

    public int getxMax() {
        return xMax;
    }

    public void setxMax(int xMax) {
        this.xMax = xMax;
    }

    public int getyMax() {
        return yMax;
    }

    public void setyMax(int yMax) {
        this.yMax = yMax;
    }
}
