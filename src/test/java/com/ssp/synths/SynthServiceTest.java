package com.ssp.synths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SynthServiceTest {

    private SynthsService synthService;

    @Mock
    SynthRepository synthRepository;

    @BeforeEach
    void setUp() {
        synthService = new SynthsService(synthRepository);
    }


    @Test
    void getSynths() {
        Synth synth = new Synth(1970, "Moog Minimoog", "analog", "monophonic", "ABC1");
        when(synthRepository.findAll()).thenReturn(Arrays.asList(synth));
        SynthsList list = synthService.getSynths();
        assertThat(list).isNotNull();
        assertThat(list.isEmpty()).isFalse();
    }

    @Test
    void getSynth_search_returnsList() {
        Synth synth = new Synth(1970, "Moog Minimoog", "analog", "monophonic", "ABC1");
        synth.setPolyphony("16-notes");
        when(synthRepository.findByContainsSignalProcessingAndPolyphonyContains(anyString(), anyString()))
                .thenReturn(Arrays.asList(synth));
        SynthsList autoList = synthService.getSynths("analog", "16-notes");
        assertThat(autoList).isNotNull();
        assertThat(autoList.isEmpty()).isFalse();
    }

    @Test
    void addSynth_acceptsSynth_returnsSynth() {
        Synth synth = new Synth(1970, "Moog Minimoog", "analog", "monophonic", "ABC1");
        synth.setPolyphony("16-notes");
        when(synthRepository.save(any(Synth.class)))
                .thenReturn(synth);
        Synth synth1 = synthService.addSynth(synth);
        assertThat(synth1).isNotNull();
        assertThat(synth1.getName()).isEqualTo("Moog Minimoog");
    }

    @Test
    void updateSynth_acceptsSynth_returnsSynth() {
        Synth synth = new Synth(1970, "Moog Minimoog", "analog", "monophonic", "ABC1");
        synth.setPolyphony("16-notes");
        when(synthRepository.findByInventoryId(anyString()))
                .thenReturn(java.util.Optional.of(synth));
        when(synthRepository.save(any(Synth.class)))
                .thenReturn(synth);
        Synth synth1 = synthService.updateSynth(synth.getInventoryId(), "1980", "Minimoog");
        assertThat(synth1).isNotNull();
        assertThat(synth1.getInventoryId()).isEqualTo(synth1.getInventoryId());
    }






}
