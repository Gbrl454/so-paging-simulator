package br.gbrl.sopagingsimulator.controllers;

import br.gbrl.sopagingsimulator.Cache;
import br.gbrl.sopagingsimulator.Main;
import br.gbrl.sopagingsimulator.Views;
import br.gbrl.sopagingsimulator.algorithms.Aging;
import br.gbrl.sopagingsimulator.algorithms.Clock;
import br.gbrl.sopagingsimulator.algorithms.Fifo;
import br.gbrl.sopagingsimulator.algorithms.Nru;
import br.gbrl.sopagingsimulator.dtos.AlgorithmReportDTO;
import br.gbrl.sopagingsimulator.dtos.AlgorithmReportsDTO;
import br.gbrl.sopagingsimulator.enums.Algorithms;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class SimulationController {
    @FXML
    private PieChart chartTime;

    @FXML
    private BarChart chartLackOfPages;

    @FXML
    private Text txtInfo;

    @FXML
    protected void initialize() {
        Main.addOnChageScreenListener((newScreen) -> {
            if (Main.CURRENT_VIEW.equals(Views.SIMULATION_VIEW)) {
                List<AlgorithmReportDTO> algorithmReportList = new ArrayList<>();

                if (Cache.algorithmSimulationRequest.contains(Algorithms.NRU))
                    algorithmReportList.add(Nru.run(Cache.pages, Cache.qPags));
                if (Cache.algorithmSimulationRequest.contains(Algorithms.AGING))
                    algorithmReportList.add(Aging.run(Cache.pages, Cache.qPags));
                if (Cache.algorithmSimulationRequest.contains(Algorithms.CLOCK))
                    algorithmReportList.add(Clock.run(Cache.pages, Cache.qPags));
                if (Cache.algorithmSimulationRequest.contains(Algorithms.FIFO))
                    algorithmReportList.add(Fifo.run(Cache.pages, Cache.qPags));

                AlgorithmReportsDTO algorithmReports = new AlgorithmReportsDTO(algorithmReportList);
                loadChartLackOfPages(algorithmReports);
                loadChartTime(algorithmReports);
                txtInfo.setText("Tempo total de execução: " + converterTime(algorithmReports.executionTime()) + " | Quantidade total de falta de página: " + algorithmReports.lackOfPages());
            }
        });
    }

    @FXML
    protected void goBack() {
        Main.changeViews(Views.INITIAL_VIEW);
    }

    private String converterTime(long milissegundos) {
        if (milissegundos >= 60000) return (milissegundos / 60000) + "min";
        else if (milissegundos >= 1000) return (milissegundos / 1000) + "s";
        else return milissegundos + "ms";
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
        chartTime.setData(FXCollections.observableArrayList(algorithmReports.algorithmReportList().stream().map(it -> new PieChart.Data(it.name() + " (" + converterTime(it.executionTime()) + ")", it.executionTime())).toList()));
    }
}