package com.example.geektrust.Services;

import com.example.geektrust.DTO.Slot;
import com.example.geektrust.Entities.CCave;
import com.example.geektrust.Entities.DTower;
import com.example.geektrust.Entities.GMansion;

import java.time.LocalTime;
import java.util.List;

public class VacancyService implements IService {
    private final CCave cCave;
    private final DTower dTower;
    private final GMansion gMansion;

    public VacancyService(CCave cCave, DTower dTower, GMansion gMansion) {
        this.cCave = cCave;
        this.dTower = dTower;
        this.gMansion = gMansion;
    }

    @Override
    public String invoke(List<String> tokens) throws Exception {
        String[] start = tokens.get(1).split(":");
        String[] end = tokens.get(2).split(":");
        Slot checkSlot = new Slot(LocalTime.of(Integer.parseInt(start[0]), Integer.parseInt(start[1])), LocalTime.of(Integer.parseInt(end[0]), Integer.parseInt(end[1])));
        if (checkSlot.bufferPeriodCheck()) {
            return "NO_VACANT_ROOM";
        }
        StringBuilder vacantRooms = new StringBuilder();
        if (cCave.vacantSlots(checkSlot)) {
            vacantRooms.append("C-Cave");
        }
        if (dTower.vacantSlots(checkSlot)) {
            vacantRooms.append(" D-Tower");
        }
        if (gMansion.vacantSlots(checkSlot)) {
            vacantRooms.append(" G-Mansion");
        }
        if (vacantRooms.length() == 0) {
            return "NO_VACANT_ROOM";
        }
        return vacantRooms.toString().trim();
    }
}
