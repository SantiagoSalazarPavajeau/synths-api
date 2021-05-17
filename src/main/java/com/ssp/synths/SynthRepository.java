package com.ssp.synths;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SynthRepository extends JpaRepository<Synth, Long> {
    List<Synth> findByContainsSignalProcessingAndPolyphonyContains(String signalProcessing, String polyphony);

    Optional<Synth> findByInventoryId(String inventoryId);
}
