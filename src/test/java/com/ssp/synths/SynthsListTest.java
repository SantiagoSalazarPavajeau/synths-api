package com.ssp.synths;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SynthsListTest {

    @Test
    void test_NoArgsConstructor(){
        //setup
        SynthsList synthsList = new SynthsList();
        //exec
        List<Synth> list = synthsList.getSynths();
        //assert
        assertEquals(new ArrayList<>(), list );

    }

    @Test
    void setSynths() {
        //setup
        SynthsList synthsList = new SynthsList();
        List<Synth> list = new ArrayList<>();
        list.add(new Synth(1970, "Moog Minimoog", "analog", "monophonic", "ABC1"));
        list.add(new Synth(1978, "Sequential Circuits Prophet-5", "analog", "5-notes", "ABC2"));
        //exec
        synthsList.setSynths(list);
        //assert
        assertEquals(list, synthsList.getSynths() );
    }
}