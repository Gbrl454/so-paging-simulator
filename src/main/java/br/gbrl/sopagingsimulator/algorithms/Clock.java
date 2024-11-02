package br.gbrl.sopagingsimulator.algorithms;

import br.gbrl.sopagingsimulator.Main;
import br.gbrl.sopagingsimulator.dtos.AlgorithmReportDTO;

import java.util.ArrayList;
import java.util.List;

public class Clock extends Algorithm {
    private final List<Integer> memory;
    private final int[] pages;
    private final int capacity;
    private final int[] clockHand;
    private final boolean[] referenced;
    private int pointer;

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

    public AlgorithmReportDTO run() {
        return new AlgorithmReportDTO("Clock", this.getPageFaults(), this.getTimeSimulate());
    }

    private void simulate() {
        this.timeSimulate = System.currentTimeMillis();
        for (int page : this.pages) {
            Main.sleep();
            if (!memory.contains(page)) {
                if (memory.size() < capacity) {
                    memory.add(page);
                    clockHand[pointer] = page;
                    referenced[pointer] = true;
                    pointer = (pointer + 1) % capacity;
                    pageFaults++;
                } else {
                    while (true) {
                        Main.sleep();
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
}
