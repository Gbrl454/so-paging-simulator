package br.gbrl.sopagingsimulator.algorithms;

import br.gbrl.sopagingsimulator.Main;
import br.gbrl.sopagingsimulator.dtos.AlgorithmReportDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Nru extends Algorithm {
    private final int[] pages;
    private final int capacity;
    private final List<Integer> memory;
    private final int[] referenceBits;
    private final int[] dirtyBits;
    private final int resetInterval = 5;
    private int accessCounter;

    public Nru(int[] pages, int capacity) {
        this.pages = pages;
        this.capacity = capacity;
        this.memory = new ArrayList<>();
        this.referenceBits = new int[capacity];
        this.dirtyBits = new int[capacity];
        this.pageFaults = 0;
        this.accessCounter = 0;
        this.simulate();
    }

    public AlgorithmReportDTO run() {
        return new AlgorithmReportDTO("NRU", this.getPageFaults(), this.getTimeSimulate());
    }

    private void simulate() {
        this.timeSimulate = System.currentTimeMillis();
        for (int page : this.pages) {
            Main.sleep();
            if (!this.memory.contains(page)) {
                if (this.memory.size() < this.capacity) this.memory.add(page);
                else this.memory.set(this.findPageToReplace(), page);
                this.pageFaults++;
            }
            this.updateReferenceBits(page);

            this.accessCounter++;
            if (this.accessCounter % this.resetInterval == 0) Arrays.fill(this.referenceBits, 0);
        }
        this.timeSimulate -= System.currentTimeMillis();
    }

    private int findPageToReplace() {
        int minClass = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < this.memory.size(); i++) {
            Main.sleep();
            int currentClass = (this.referenceBits[i] << 1) | this.dirtyBits[i];
            if (currentClass < minClass) {
                minClass = currentClass;
                index = i;
            }
        }
        return index;
    }

    private void updateReferenceBits(int newPage) {
        for (int i = 0; i < this.memory.size(); i++) {
            Main.sleep();
            if (this.memory.get(i) == newPage) this.referenceBits[i] = 1;
        }
    }
}
