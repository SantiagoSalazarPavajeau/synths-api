package com.ssp.synths;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SynthRepository extends JpaRepository<Synth, Long> {
}
