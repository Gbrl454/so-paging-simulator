package br.gbrl.sopagingsimulator.controllers;

import br.gbrl.sopagingsimulator.Cache;
import br.gbrl.sopagingsimulator.Main;
import br.gbrl.sopagingsimulator.Views;
import br.gbrl.sopagingsimulator.dtos.AlgorithmReportDTO;
import br.gbrl.sopagingsimulator.dtos.AlgorithmReportsDTO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.*;

import java.util.ArrayList;
import java.util.List;

public class SimulationController {
    @FXML
    private PieChart chartTime;
    @FXML
    private BarChart chartLackOfPages;

    @FXML
    protected void initialize() {
        Main.addOnChageScreenListener((newScreen) -> {
            if (Main.currentView.equals(Views.SIMULATION_VIEW)) {
                System.out.println(Cache.algorithmSimulationRequest);

                // TODO execultar algoritimos
                List<AlgorithmReportDTO> algorithmReportList = new ArrayList<>();
                algorithmReportList.add(new AlgorithmReportDTO("A1", 7, 300L));
                algorithmReportList.add(new AlgorithmReportDTO("A2", 8, 350L));
                algorithmReportList.add(new AlgorithmReportDTO("A3", 9, 800L));
                algorithmReportList.add(new AlgorithmReportDTO("A4", 5, 150L));
                AlgorithmReportsDTO algorithmReports = new AlgorithmReportsDTO(algorithmReportList);
                loadChartLackOfPages(algorithmReports);
                loadChartTime(algorithmReports);
            }
        });
    }

    @FXML
    protected void onSimulateButtonClick() {
        System.out.println("AAAAAAAAAAA");
    }

    private void loadChartLackOfPages(AlgorithmReportsDTO algorithmReports) {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Falta de páginas");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Método");

        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        algorithmReports.algorithmReportList().forEach(it -> dataSeries.getData().add(new XYChart.Data<>(it.name() + " (" + it.lackOfPages() + ")", it.lackOfPages())));
        chartLackOfPages.getData().add(dataSeries);
    }

    private void loadChartTime(AlgorithmReportsDTO algorithmReports) {
        chartTime.setTitle("Tempo de execução");
        chartTime.setData(FXCollections.observableArrayList(algorithmReports.algorithmReportList().stream().map(it -> new PieChart.Data(it.name(), it.executionTime())).toList()));
    }
}