package com.ssp.synths;

public class SynthsService {

    SynthRepository synthRepository;

    SynthsService(SynthRepository synthRepository){
        this.synthRepository = synthRepository;
    }

    public SynthsList getSynths( ){
        return new SynthsList(synthRepository.findAll());
    }

    public SynthsList getSynths( String signalProcessing, String polyphony){
        return null;
    }

    public Synth addSynth(Synth synth){
        return null;
    }

    public Synth getSynth(String inventoryId){
        return null;
    }

    public Synth updateSynth(String inventoryId, String year, String name) {
        return null;
    }

    public void deleteSynth(String inventoryId){
    }
}
