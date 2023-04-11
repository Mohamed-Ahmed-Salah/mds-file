package com.example.MDSWebFile.repo;

import com.example.MDSWebFile.models.Contact;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepo  extends JpaRepository<Contact, Long> {
    List<Contact> findByEmail(String email);

    List<Contact> findByStatus(String status, Pageable pageable);

}
