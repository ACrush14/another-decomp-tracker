package com.decomptracker.repository;

import com.decomptracker.model.Snapshot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnapshotRepository extends JpaRepository <Snapshot, Long> {

}