package com.weglinskij.assecohometask.view;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.StreamRegistration;
import com.vaadin.flow.server.StreamResource;
import com.weglinskij.assecohometask.model.Invoice;
import com.weglinskij.assecohometask.repository.InvoiceRepository;
import com.weglinskij.assecohometask.view.component.EmbeddedPdfDocument;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;

@Route("index")
@RouteAlias("main")
@RouteAlias("")
public class IndexView extends VerticalLayout {

    private final InvoiceRepository invoiceRepository;
    final Grid<Invoice> grid;
    final EmbeddedPdfDocument[] embeddedPdfDocument = {new EmbeddedPdfDocument("")};
    final Notification notification[] = initNotification("");
    final Random random = new Random();

    public IndexView(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
        this.grid = new Grid<>();
        grid.setItems(invoiceRepository.findAll());
        grid.addColumn(Invoice::getNumber).setHeader("Numer");
        grid.addColumn(Invoice::getDate).setHeader("Data wystawienia");
        grid.addColumn(Invoice::getGrossValue).setHeader("Wartość brutto");
        grid.addComponentColumn(item -> new Button("Podgląd", previewEventListener(item)));
        add(grid);
    }

    private String transformPdfName(String invoiceNumber) {
        return "pdf/FV_" + invoiceNumber.substring(3, 4) + "_" + invoiceNumber.substring(5, 7) + "_" + invoiceNumber.substring(8) + ".pdf";
    }

    private Notification[] initNotification(String invoiceNumber) {
        Div content = new Div();
        content.addClassName("my-style");
        content.setText("Nie masz wymaganych uprawnień do wyświetlenia treści faktury " + invoiceNumber);

        Notification notification = new Notification(content);
        notification.setDuration(3000);
        notification.setPosition(Notification.Position.MIDDLE);

        String styles = ".my-style { "
                + "  color: red;"
                + " }";

        StreamRegistration resource = UI.getCurrent().getSession()
                .getResourceRegistry()
                .registerResource(new StreamResource("styles.css", () -> {
                    byte[] bytes = styles.getBytes(StandardCharsets.UTF_8);
                    return new ByteArrayInputStream(bytes);
                }));
        UI.getCurrent().getPage().addStyleSheet(
                "base://" + resource.getResourceUri().toString());

        return new Notification[]{notification};
    }

    private ComponentEventListener<ClickEvent<Button>> previewEventListener(Invoice item) {
        return (ComponentEventListener<ClickEvent<Button>>) buttonClickEvent -> {
            remove(embeddedPdfDocument[0]);
            if (random.nextInt(2) == 0) {
                if (item.getPreview() != null) {
                    InputStream inputStream;
                    inputStream = new ByteArrayInputStream(item.getPreview());
                    embeddedPdfDocument[0] = new EmbeddedPdfDocument(inputStream);
                } else {
                    embeddedPdfDocument[0] = new EmbeddedPdfDocument(transformPdfName(item.getNumber()));
                }
                add(embeddedPdfDocument[0]);
                if (getHeight() == null || !getHeight().equals("100%")) setHeight("100%");
            } else {
                notification[0] = initNotification(item.getNumber())[0];
                notification[0].open();
                setHeight(null);
            }
        };
    }
}
