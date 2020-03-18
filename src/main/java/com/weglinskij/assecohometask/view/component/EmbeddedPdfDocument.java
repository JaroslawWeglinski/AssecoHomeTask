package com.weglinskij.assecohometask.view.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.server.StreamResource;

import java.io.InputStream;

@Tag("object")
public class EmbeddedPdfDocument extends Component implements HasSize {

    public EmbeddedPdfDocument(StreamResource resource) {
        this();
        getElement().setAttribute("data", resource);
    }

    public EmbeddedPdfDocument(String url) {
        this();
        getElement().setAttribute("data", url);
    }

    public EmbeddedPdfDocument(InputStream inputStream) {
        this();
        StreamResource resource = new StreamResource("filename",
                () -> inputStream);
        resource.setContentType("application/pdf");
        getElement().setAttribute("data", resource);
    }

    protected EmbeddedPdfDocument() {
        getElement().setAttribute("type", "application/pdf");
        setSizeFull();
    }
}
