package br.gbrl.sopagingsimulator.algorithms;

import br.gbrl.sopagingsimulator.dtos.AlgorithmReportDTO;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Fifo {
    private final int[] pages;
    private final int capacity;
    private final Queue<Integer> pageQueue;
    private int pageFaults;
    private Long timeSimulate;

    public Fifo(int[] pages, int capacity) {
        this.pages = pages;
        this.capacity = capacity;
        this.pageQueue = new LinkedList<>();
        this.pageFaults = 0;
        this.simulate();
    }

    public static AlgorithmReportDTO run(List<Integer> pages, int capacity) {
        Fifo fifo = new Fifo(pages.stream().mapToInt(Integer::intValue).toArray(), capacity);
        return new AlgorithmReportDTO("FIFO", fifo.getPageFaults(), fifo.getTimeSimulate());
    }

    private void simulate() {
        this.timeSimulate = System.currentTimeMillis();
        for (int page : this.pages) {
            if (!pageQueue.contains(page)) {
                if (pageQueue.size() == capacity) pageQueue.poll();
                pageQueue.offer(page);
                pageFaults++;
            }
        }
        this.timeSimulate -= System.currentTimeMillis();
    }

    public int getPageFaults() {
        return this.pageFaults;
    }

    public long getTimeSimulate() {
        return Math.abs(this.timeSimulate);
    }
}
