package br.gbrl.sopagingsimulator.algorithms;

import br.gbrl.sopagingsimulator.dtos.AlgorithmReportDTO;

import java.util.ArrayList;
import java.util.List;

public class Aging {
    private final int[] pages;
    private final int capacity;
    private final List<Integer> memory;
    private final int[] ageBits;
    private int pageFaults;
    private Long timeSimulate;

    public Aging(int[] pages, int capacity) {
        this.pages = pages;
        this.capacity = capacity;
        this.memory = new ArrayList<>();
        this.ageBits = new int[capacity];
        this.pageFaults = 0;
        this.simulate();
    }

    public static AlgorithmReportDTO run(List<Integer> pages, int capacity) {
        Aging aging = new Aging(pages.stream().mapToInt(Integer::intValue).toArray(), capacity);
        return new AlgorithmReportDTO("Aging", aging.getPageFaults(), aging.getTimeSimulate());
    }

    private void simulate() {
        this.timeSimulate = System.currentTimeMillis();
        for (int page : this.pages) {
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
            ageBits[i] >>= 1;
            if (memory.contains(memory.get(i))) ageBits[i] |= 0x80000000;
        }
    }

    public Long getTimeSimulate() {
        return timeSimulate;
    }

    public int getPageFaults() {
        return pageFaults;
    }
}
