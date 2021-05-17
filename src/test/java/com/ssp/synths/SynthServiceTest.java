package com.ssp.synths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
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


}
