package com.ssp.synths;

import java.util.List;
import java.util.Optional;

public class SynthsService {

    SynthRepository synthRepository;

    SynthsService(SynthRepository synthRepository){
        this.synthRepository = synthRepository;
    }

    public SynthsList getSynths( ){
        return new SynthsList(synthRepository.findAll());
    }

    public SynthsList getSynths( String signalProcessing, String polyphony){
        List<Synth> synths =  synthRepository.findByContainsSignalProcessingAndPolyphonyContains(signalProcessing, polyphony);
        if(!synths.isEmpty()){
            return new SynthsList(synths);
        }
        return null;
    }

    public Synth addSynth(Synth synth){
        return synthRepository.save(synth);
    }

    public Synth getSynth(String inventoryId){
        return null;
    }

    public Synth updateSynth(String inventoryId, String year, String name) {
        Optional<Synth> optionalSynth =  synthRepository.findByInventoryId(inventoryId);

        if(optionalSynth.isPresent()){
            optionalSynth.get().setYear(Integer.parseInt(year));
            optionalSynth.get().setName(name);
            return synthRepository.save(optionalSynth.get());
        }
        return null;
    }

    public void deleteSynth(String inventoryId){
    }
}
