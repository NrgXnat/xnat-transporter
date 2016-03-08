package org.nrg.transporter;

import org.nrg.xft.ItemI;

import java.nio.file.Path;
import java.util.List;

public interface TransportService {
    Path transport(ItemI item, String destinationName);

    List<Path> transport(List<ItemI> items, String destinationName);

    void registerDestination(Destination destination);

    Destination getDestinationByName(String destinationName);
}
