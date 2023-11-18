package com.example.geektrust.Entities;

import com.example.geektrust.DTO.Slot;

import java.util.TreeSet;

public class GMansion implements IMeetingRoom {
    private final Integer capacity;
    private final TreeSet<Slot> slots;

    public GMansion() {
        this.capacity = 20;
        this.slots = new TreeSet<>((s1, s2) -> s1.getStart().isAfter(s2.getStart()) ? 1 : -1);

    }

    public boolean bookSlot(Slot newSlot, int capacity) {
        if (this.capacity < capacity || !vacantSlots(newSlot))
            return false;
        if (slots.stream().anyMatch((s) -> s.equals(newSlot))) return false;
        return slots.add(newSlot);
    }

    @Override
    public boolean vacantSlots(Slot checkSlot) {
        if (slots.isEmpty()) return true;
        Slot first = slots.first();
        if ((checkSlot.getEnd().isBefore(first.getStart()) || checkSlot.getEnd().equals(first.getStart()))) {
            return true;
        }
        return slots.stream().noneMatch((s1) -> s1.isOverLapping(checkSlot));
    }
}
