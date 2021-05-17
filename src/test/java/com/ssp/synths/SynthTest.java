package com.ssp.synths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SynthTest {

    Synth synth;
    @BeforeEach
    void setUp() {
        synth = new Synth(1970, "Moog Minimoog", "analog", "monophonic", "ABC1");
    }

    @Test
    void testYear_getAndSet() {
        //setup
        int newYear = 1980;
        //exec
        synth.setYear(1980);
        // assert
        assertEquals(newYear, synth.getYear());
    }

    @Test
    void testName_getAndSet() {
        //setup
        String newName = "Minimoog";
        //exec
        synth.setName(newName);
        // assert
        assertEquals(newName, synth.getName());
    }

    @Test
    void testSignalProcessing_getAndSet() {
        //setup
        String newSP = "Digital";
        //exec
        synth.setSignalProcessing(newSP);
        // assert
        assertEquals(newSP, synth.getSignalProcessing());
    }

    @Test
    void testPolyphony_getAndSet() {
        //setup
        String newPoly = "1-note";
        //exec
        synth.setPolyphony(newPoly);
        // assert
        assertEquals(newPoly, synth.getPolyphony());
    }

    @Test
    void testInventoryId_getAndSet() {
        //setup
        String newIId = "AABBCC1";
        //exec
        synth.setInventoryId(newIId);
        // assert
        assertEquals(newIId, synth.getInventoryId());
    }
}