package br.gbrl.sopagingsimulator.algorithms;

import br.gbrl.sopagingsimulator.dtos.AlgorithmReportDTO;

import java.util.ArrayList;
import java.util.List;

public class Clock {
    private final List<Integer> memory;
    private final int[] pages;
    private final int capacity;
    private final int[] clockHand;
    private final boolean[] referenced;
    private int pointer;
    private int pageFaults;
    private Long timeSimulate;

    public Clock(int[] pages, int capacity) {
        this.pages = pages;
        this.capacity = capacity;
        this.memory = new ArrayList<>();
        this.clockHand = new int[capacity];
        this.referenced = new boolean[capacity];
        this.pointer = 0;
        this.pageFaults = 0;
        this.simulate();
    }

    public static AlgorithmReportDTO run(List<Integer> pages, int capacity) {
        Clock clock = new Clock(pages.stream().mapToInt(Integer::intValue).toArray(), capacity);
        return new AlgorithmReportDTO("Clock", clock.getPageFaults(), clock.getTimeSimulate());
    }

    private void simulate() {
        this.timeSimulate = System.currentTimeMillis();
        for (int page : this.pages) {
            if (!memory.contains(page)) {
                if (memory.size() < capacity) {
                    memory.add(page);
                    clockHand[pointer] = page;
                    referenced[pointer] = true;
                    pointer = (pointer + 1) % capacity;
                    pageFaults++;
                } else {
                    while (true) {
                        if (!referenced[pointer]) {
                            memory.set(pointer, page);
                            clockHand[pointer] = page;
                            referenced[pointer] = true;
                            pointer = (pointer + 1) % capacity;
                            pageFaults++;
                            break;
                        } else {
                            referenced[pointer] = false;
                            pointer = (pointer + 1) % capacity;
                        }
                    }
                }
            } else referenced[memory.indexOf(page)] = true;
        }
        this.timeSimulate -= System.currentTimeMillis();
    }

    public Long getTimeSimulate() {
        return timeSimulate;
    }

    public int getPageFaults() {
        return pageFaults;
    }
}
