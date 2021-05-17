package com.ssp.synths;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class SynthsList {

    private List<Synth> synths;

    public SynthsList(){
        this.synths = new ArrayList<>();
    }

    public SynthsList(List<Synth> synths){
        this.synths = synths;
    }

    public List<Synth> getSynths() {
        return synths;
    }

    public void setSynths(List<Synth> synths) {
        this.synths = synths;
    }

    @JsonIgnore
    public boolean isEmpty() {
        return this.synths.isEmpty();
    }
}
