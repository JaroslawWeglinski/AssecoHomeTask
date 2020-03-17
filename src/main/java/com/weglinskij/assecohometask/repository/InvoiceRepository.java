package com.weglinskij.assecohometask.repository;

import com.weglinskij.assecohometask.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
