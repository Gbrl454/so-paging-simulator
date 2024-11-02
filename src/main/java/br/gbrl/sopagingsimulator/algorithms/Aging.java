package br.gbrl.sopagingsimulator.algorithms;

import br.gbrl.sopagingsimulator.Main;
import br.gbrl.sopagingsimulator.dtos.AlgorithmReportDTO;

import java.util.ArrayList;
import java.util.List;

public class Aging extends Algorithm {
    private final int[] pages;
    private final int capacity;
    private final List<Integer> memory;
    private final int[] ageBits;

    public Aging(int[] pages, int capacity) {
        this.pages = pages;
        this.capacity = capacity;
        this.memory = new ArrayList<>();
        this.ageBits = new int[capacity];
        this.pageFaults = 0;
        this.simulate();
    }

    public AlgorithmReportDTO run() {
        return new AlgorithmReportDTO("Aging", this.getPageFaults(), this.getTimeSimulate());
    }

    private void simulate() {
        this.timeSimulate = System.currentTimeMillis();
        for (int page : this.pages) {
            Main.sleep();
            if (!memory.contains(page)) {
                if (memory.size() < capacity) memory.add(page);
                else memory.set(findPageToReplace(), page);
                pageFaults++;
            }
            updateAgeBits();
        }
        this.timeSimulate -= System.currentTimeMillis();
    }

    private int findPageToReplace() {
        int minAge = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < memory.size(); i++) {
            int age = ageBits[i];
            if (age < minAge) {
                minAge = age;
                index = i;
            }
        }
        return index;
    }

    private void updateAgeBits() {
        for (int i = 0; i < memory.size(); i++) {
            Main.sleep();
            ageBits[i] >>= 1;
            if (memory.contains(memory.get(i))) ageBits[i] |= 0x80000000;
        }
    }
}
