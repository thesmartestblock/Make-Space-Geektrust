package com.example.geektrust.Services;

import com.example.geektrust.DTO.Slot;
import com.example.geektrust.Entities.CCave;
import com.example.geektrust.Entities.DTower;
import com.example.geektrust.Entities.GMansion;

import java.time.LocalTime;
import java.util.List;

public class BookingService implements IService {
    private final CCave cCave;
    private final DTower dTower;
    private final GMansion gMansion;

    public BookingService(CCave cCave, DTower dTower, GMansion gMansion) {
        this.cCave = cCave;
        this.dTower = dTower;
        this.gMansion = gMansion;
    }


    @Override
    public String invoke(List<String> tokens) throws Exception {
        Slot newSlot = getSlot(tokens);
        int capacity = Integer.parseInt(tokens.get(3));
        if (capacity < 2 || capacity > 20) {
            return "NO_VACANT_ROOM";
        } else if (cCave.bookSlot(newSlot, capacity))
            return "C-Cave";
        else if (dTower.bookSlot(newSlot, capacity))
            return "D-Tower";

        else if (gMansion.bookSlot(newSlot, capacity))
            return "G-Mansion";

        return "NO_VACANT_ROOM";
    }

    private static Slot getSlot(List<String> tokens) throws Exception {
        String[] start = tokens.get(1).split(":");
        String[] end = tokens.get(2).split(":");

        Slot newSlot = new Slot(LocalTime.of(Integer.parseInt(start[0]), Integer.parseInt(start[1])), LocalTime.of(Integer.parseInt(end[0]), Integer.parseInt(end[1])));
        newSlot.bufferPeriodCheck();
        return newSlot;

    }
}
