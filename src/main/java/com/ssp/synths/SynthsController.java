package com.ssp.synths;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SynthsController {

    SynthsService synthsService;

    public SynthsController(SynthsService synthsService) {
        this.synthsService = synthsService;
    }

    @GetMapping("/api/synths")
    public SynthsList getSynths(){
        return synthsService.getSynths();
    }

}
