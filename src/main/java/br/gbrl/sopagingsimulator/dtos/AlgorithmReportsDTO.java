package br.gbrl.sopagingsimulator.dtos;

import java.util.List;

public record AlgorithmReportsDTO(List<AlgorithmReportDTO> algorithmReportList, Integer lackOfPages,
                                  Long executionTime) {
    public AlgorithmReportsDTO(List<AlgorithmReportDTO> algorithmReportList) {
        this(algorithmReportList,
                algorithmReportList.stream().map(AlgorithmReportDTO::lackOfPages).reduce(0, Integer::sum),
                algorithmReportList.stream().map(AlgorithmReportDTO::executionTime).reduce(0L, Long::sum));
    }
}
