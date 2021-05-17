package com.ssp.synths;

import org.springframework.http.HttpStatus;
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

    @PostMapping("/api/synths")
    public Synth addSynth(@RequestBody Synth synth){
        return synthsService.addSynth(synth);
    }

    @GetMapping("/api/synths/{inventoryId}")
    public ResponseEntity<Synth> getAuto(@PathVariable String inventoryId) {
        Synth synth;
        try{
            synth = synthsService.getSynth(inventoryId);
        }catch (SynthNotFoundException e){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(synth);
    }

    @PatchMapping("/api/synths/{inventoryId}")
    public ResponseEntity<Synth> updateAuto(@PathVariable String inventoryId,
                                                 @RequestBody UpdateSynthRequest update) {
        Synth synth = synthsService.updateSynth(inventoryId, update.getYear(), update.getName());

        synth.setYear(Integer.parseInt(update.getYear()));
        synth.setName(update.getName());

        return ResponseEntity.ok(synth);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void invalidAuto(InvalidSynthException e){

    }


}
