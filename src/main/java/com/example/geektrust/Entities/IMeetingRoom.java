package com.example.geektrust.Entities;

import com.example.geektrust.DTO.Slot;

public interface IMeetingRoom {
    boolean bookSlot(Slot newSlot, int capacity);

    boolean vacantSlots(Slot checkSlot);
}
