package br.gbrl.sopagingsimulator.algorithms;

import br.gbrl.sopagingsimulator.Main;
import br.gbrl.sopagingsimulator.dtos.AlgorithmReportDTO;

import java.util.LinkedList;
import java.util.Queue;

public class Fifo extends Algorithm {
    private final int[] pages;
    private final int capacity;
    private final Queue<Integer> pageQueue;

    public Fifo(int[] pages, int capacity) {
        this.pages = pages;
        this.capacity = capacity;
        this.pageQueue = new LinkedList<>();
        this.pageFaults = 0;
        this.simulate();
    }

    public AlgorithmReportDTO run() {
        return new AlgorithmReportDTO("FIFO", this.getPageFaults(), this.getTimeSimulate());
    }

    private void simulate() {
        this.timeSimulate = System.currentTimeMillis();
        for (int page : this.pages) {
            Main.sleep();
            if (!pageQueue.contains(page)) {
                if (pageQueue.size() == capacity) pageQueue.poll();
                pageQueue.offer(page);
                pageFaults++;
            }
        }
        this.timeSimulate -= System.currentTimeMillis();
    }
}
