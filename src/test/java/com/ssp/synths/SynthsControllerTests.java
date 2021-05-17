package com.ssp.synths;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SynthsController.class)
public class SynthsControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SynthsService synthsService;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void getSynths_noParameters_returnsSynthsList() throws Exception {
        List<Synth> list = new ArrayList<>();
        list.add(new Synth(1970, "Moog Minimoog", "analog", "monophonic", "ABC1"));
        list.add(new Synth(1978, "Sequential Circuits Prophet-5", "analog", "5-notes", "ABC2"));

        when(synthsService.getSynths()).thenReturn(new SynthsList(list));

        mockMvc.perform(get("/api/synths"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.synths", hasSize(2)));
    }

    @Test
    void getSynths_noParams_non_returnsNoContent() throws Exception {
        when(synthsService.getSynths()).thenReturn(new SynthsList());
        mockMvc.perform(get("/api/synths"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void getSynths_searchParms_exists_ReturnsSynthList() throws Exception {
        List<Synth> list = new ArrayList<>();
        list.add(new Synth(1970, "Moog Minimoog", "analog", "monophonic", "ABC1"));
        list.add(new Synth(1978, "Sequential Circuits Prophet-5", "analog", "5-notes", "ABC2"));
        list.add(new Synth(1990, "Korg Wavestation", "vector", "32-notes", "ABC3"));

        when(synthsService.getSynths(anyString(), anyString())).thenReturn(new SynthsList(Arrays.asList(list.get(0))));
        mockMvc.perform(get("/api/synths?signalProcessing=analog&polyphony=monophonic"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.synths", hasSize(1)));
    }

    @Test
    void addSynth_valid_returnsSynth() throws Exception {
        Synth synth = new Synth(1970, "Moog Minimoog", "analog", "monophonic", "ABC1");

        when(synthsService.addSynth(any(Synth.class))).thenReturn(synth);

        mockMvc.perform(post("/api/synths")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(synth)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Moog Minimoog"));
    }

    @Test
    void addSynth_invalid_returnsInvalidSynthException() throws Exception {

        when(synthsService.addSynth(any(Synth.class))).thenThrow(InvalidSynthException.class);
        String jsonNoYear = "{\"name\":\"Moog Minimoog\",\"signalProcessing\":\"analog\",\"polyphony\":\"monophonic\",\"inventoryId\":\"ABC1\"}";
        mockMvc.perform(post("/api/synths")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonNoYear))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void getSynth_withItemId_returnsSynth() throws Exception {
        Synth synth = new Synth(1970, "Moog Minimoog", "analog", "monophonic", "ABC1");

        when(synthsService.getSynth(anyString())).thenReturn(synth);

        mockMvc.perform(get("/api/synths/"+synth.getInventoryId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("inventoryId").value(synth.getInventoryId()));
    }

    @Test
    void getSynth_nonExistingItemId_returnsSynthNotFoundException() throws Exception {
        when(synthsService.getSynth(anyString())).thenThrow(SynthNotFoundException.class);

        mockMvc.perform(get("/api/synths/A"))
                .andExpect(status().isNoContent());
    }

    @Test
    void updateSynth_withObject_returnsSynth() throws Exception {
        Synth synth = new Synth(1970, "Moog Minimoog", "analog", "monophonic", "ABC1");
        when(synthsService.updateSynth(anyString(), anyString(), anyString())).thenReturn(synth);
        mockMvc.perform(patch("/api/synths/"+synth.getInventoryId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"year\":\"1980\",\"name\":\"Minimoog\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("year").value("1980"))
                .andExpect(jsonPath("name").value("Minimoog"));
    }

}
