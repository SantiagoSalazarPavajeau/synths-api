package com.ssp.synths;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "synths")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Synth {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int year;
    private String name;
    private String signalProcessing;
    private String polyphony;
    @Column(unique = true)
    private String inventoryId;

    public Synth(){

    }

    public Synth(int year, String name, String signalProcessing, String polyphony, String inventoryId) {
        this.year = year;
        this.name = name;
        this.signalProcessing = signalProcessing;
        this.polyphony = polyphony;
        this.inventoryId = inventoryId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSignalProcessing() {
        return signalProcessing;
    }

    public void setSignalProcessing(String signalProcessing) {
        this.signalProcessing = signalProcessing;
    }

    public String getPolyphony() {
        return polyphony;
    }

    public void setPolyphony(String polyphony) {
        this.polyphony = polyphony;
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }


}
