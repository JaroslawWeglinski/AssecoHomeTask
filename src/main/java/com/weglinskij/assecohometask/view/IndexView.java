package com.weglinskij.assecohometask.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.weglinskij.assecohometask.model.Invoice;
import com.weglinskij.assecohometask.repository.InvoiceRepository;
import com.weglinskij.assecohometask.view.component.EmbeddedPdfDocument;

@Route("index")
@RouteAlias("main")
@RouteAlias("")
public class IndexView extends VerticalLayout {

    private final InvoiceRepository invoiceRepository;
    final Grid<Invoice> grid;

    public IndexView(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
        this.grid = new Grid<>();
        final EmbeddedPdfDocument[] embeddedPdfDocument = {new EmbeddedPdfDocument("")};
        grid.setItems(invoiceRepository.findAll());
        grid.addColumn(Invoice::getNumber).setHeader("Numer");
        grid.addColumn(Invoice::getDate).setHeader("Data wystawienia");
        grid.addColumn(Invoice::getGrossValue).setHeader("Wartość brutto");
        grid.addComponentColumn(item -> new Button("Podgląd", click -> {
            remove(embeddedPdfDocument[0]);
            embeddedPdfDocument[0] = new EmbeddedPdfDocument(transformPdfName(item.getNumber()));
            add(embeddedPdfDocument[0]);
            if (getHeight() == null || !getHeight().equals("100%")) setHeight("100%");
        }));
        add(grid);

    }

    private String transformPdfName(String invoiceName) {
        return "pdf/FV_" + invoiceName.substring(3, 4) + "_" + invoiceName.substring(5, 7) + "_" + invoiceName.substring(8) + ".pdf";
    }
}
