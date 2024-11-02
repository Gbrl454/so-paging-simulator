package br.gbrl.sopagingsimulator.algorithms;

import br.gbrl.sopagingsimulator.dtos.AlgorithmReportDTO;

interface AlgorithmInterface {
    AlgorithmReportDTO run();
}

public abstract class Algorithm implements AlgorithmInterface {
    protected int pageFaults;
    protected Long timeSimulate;

    public int getPageFaults() {
        return this.pageFaults;
    }

    public long getTimeSimulate() {
        return Math.abs(this.timeSimulate);
    }
}


