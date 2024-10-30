package br.gbrl.sopagingsimulator.dtos;

import java.util.List;

public record AlgorithmReports(List<AlgorithmReport> algorithmReportList, Integer lackOfPages, Long executionTime) {
}
