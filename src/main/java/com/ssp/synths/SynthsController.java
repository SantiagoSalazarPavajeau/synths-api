package com.ssp.synths;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SynthsController {

    SynthsService synthsService;

    public SynthsController(SynthsService synthsService) {
        this.synthsService = synthsService;
    }

    @GetMapping("/api/synths")
    public ResponseEntity<SynthsList> getSynths(@RequestParam(required = false) String signalProcessing,
                                                @RequestParam ( required = false) String polyphony){
        SynthsList searchResults;
        if(signalProcessing == null && polyphony == null){
            searchResults = synthsService.getSynths();
        }else{
            searchResults = synthsService.getSynths(signalProcessing, polyphony);
        }

        if(searchResults.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(searchResults);
    }

    @PostMapping("/api/synth")
    public Synth addSynth(@RequestBody Synth synth){
        return synthsService.addSynth(synth);
    }


}
