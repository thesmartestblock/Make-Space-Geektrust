package com.example.geektrust.DTO;

import java.time.LocalTime;
import java.util.Objects;

public class Slot {
    private final LocalTime start;
    private final LocalTime end;

    public Slot(LocalTime start, LocalTime end) {
//        validTime(start,end);
        this.start = start;
        this.end = end;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public boolean bufferPeriodCheck() throws Exception {
        if (start.getMinute() % 15 != 0 || end.getMinute() % 15 != 0 || start.isAfter(end))
            throw new Exception("INCORRECT_INPUT");
        Slot s1 = new Slot(LocalTime.of(9, 0, 0), LocalTime.of(9, 15, 0));
        Slot s2 = new Slot(LocalTime.of(13, 15, 0), LocalTime.of(13, 45, 0));
        Slot s3 = new Slot(LocalTime.of(18, 45, 0), LocalTime.of(19, 0, 0));
        Slot s4 = new Slot(LocalTime.of(23, 45, 0), LocalTime.of(0, 0, 0));

        return (isOverLapping(s1) || isOverLapping(s2) || isOverLapping(s3) || isOverLapping(s4));
    }

    public boolean isOverLapping(Slot slot) {
        return (slot.getStart().isAfter(start) && slot.getStart().isBefore(end)) ||
                (slot.getEnd().isBefore(end) && slot.getEnd().isAfter(start));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Slot)) return false;
        Slot slot = (Slot) o;
        return Objects.equals(getStart(), slot.getStart()) && Objects.equals(getEnd(), slot.getEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStart(), getEnd());
    }
}
