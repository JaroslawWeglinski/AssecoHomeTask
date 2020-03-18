package com.weglinskij.assecohometask;

import com.weglinskij.assecohometask.model.Invoice;
import com.weglinskij.assecohometask.repository.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;

@Slf4j
@SpringBootApplication
public class AssecoHomeTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssecoHomeTaskApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(InvoiceRepository invoiceRepository) {
        return (args) -> {
            invoiceRepository.save(new Invoice("FV 1/01/2020", LocalDate.now().minusMonths(2).minusDays(14), new BigDecimal(4399.99)));
            invoiceRepository.save(new Invoice("FV 2/01/2020", LocalDate.now().minusMonths(2).minusDays(13), new BigDecimal(12.00)));
            invoiceRepository.save(new Invoice("FV 3/01/2020", LocalDate.now().minusMonths(2).minusDays(12), new BigDecimal(9.99)));
            invoiceRepository.save(new Invoice("FV 1/02/2020", LocalDate.now().minusMonths(1).minusDays(10), new BigDecimal(3222.22), "/static/pdf/FV_1_02_2020.pdf"));
            invoiceRepository.save(new Invoice("FV 2/02/2020", LocalDate.now().minusMonths(1).minusDays(9), new BigDecimal(31.31)));
            invoiceRepository.save(new Invoice("FV 3/02/2020", LocalDate.now().minusMonths(1).minusDays(8), new BigDecimal(98888.00)));
            invoiceRepository.save(new Invoice("FV 4/02/2020", LocalDate.now().minusMonths(1).minusDays(7), new BigDecimal(122.12)));
            invoiceRepository.save(new Invoice("FV 5/02/2020", LocalDate.now().minusMonths(1).minusDays(6), new BigDecimal(1000.01)));
            invoiceRepository.save(new Invoice("FV 1/03/2020", LocalDate.now().minusDays(3), new BigDecimal(1001.00)));
            invoiceRepository.save(new Invoice("FV 2/03/2020", LocalDate.now().minusDays(2), new BigDecimal(10.99)));

            log.info("Invoices found with findAll():");
            log.info("-------------------------------");
            for (Invoice invoice : invoiceRepository.findAll()) {
                log.info(invoice.toString());
            }
            log.info("");

            Invoice invoice = invoiceRepository.findById(1L).get();
            log.info("Invoice found with findById(1L):");
            log.info("--------------------------------");
            log.info(invoice.toString());
            log.info("");
        };
    }

}
