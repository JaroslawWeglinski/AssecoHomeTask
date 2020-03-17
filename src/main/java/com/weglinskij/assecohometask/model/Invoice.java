package com.weglinskij.assecohometask.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue
    private Long id;
    private String number;
    private LocalDate date;
    private BigDecimal grossValue;

    public Invoice(String number, LocalDate date, BigDecimal grossValue) {
        this.number = number;
        this.date = date;
        this.grossValue = grossValue;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", date=" + date +
                ", grossValue=" + grossValue +
                '}';
    }
}
