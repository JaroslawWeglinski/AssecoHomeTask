package com.weglinskij.assecohometask.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private LocalDate date;
    private BigDecimal grossValue;
    private byte[] preview;

    public Invoice(String number, LocalDate date, BigDecimal grossValue) {
        this.number = number;
        this.date = date;
        this.grossValue = grossValue;
    }

    public Invoice(String number, LocalDate date, BigDecimal grossValue, String filename) {
        try {
            this.preview = convertFilenameToByteArray(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.number = number;
            this.date = date;
            this.grossValue = grossValue;
        }
    }

    private byte[] convertFilenameToByteArray(String filename) throws IOException, SQLException {
        InputStream inputStream = new ClassPathResource(filename).getInputStream();
        return IOUtils.toByteArray(inputStream);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", date=" + date +
                ", grossValue=" + grossValue +
                ", preview=" + preview +
                '}';
    }
}
