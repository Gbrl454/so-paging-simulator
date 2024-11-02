package br.gbrl.sopagingsimulator.dtos;

public record AlgorithmReportDTO(String name, Integer lackOfPages, Long executionTime) {
    @Override
    public Long executionTime() {
        return Math.abs(executionTime);
    }
}
