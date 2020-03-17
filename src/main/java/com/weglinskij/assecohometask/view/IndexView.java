package com.weglinskij.assecohometask.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.weglinskij.assecohometask.model.Invoice;
import com.weglinskij.assecohometask.repository.InvoiceRepository;

@Route("index")
@RouteAlias("main")
@RouteAlias("")
public class IndexView extends VerticalLayout {

    private final InvoiceRepository invoiceRepository;
    final Grid<Invoice> grid;

    public IndexView(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
        this.grid = new Grid<>();
        grid.setItems(invoiceRepository.findAll());
        grid.addColumn(Invoice::getNumber).setHeader("Numer");
        grid.addColumn(Invoice::getDate).setHeader("Data wystawienia");
        grid.addColumn(Invoice::getGrossValue).setHeader("Wartość brutto");
        add(grid);
    }
}
