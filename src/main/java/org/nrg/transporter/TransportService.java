package org.nrg.transporter;

import org.nrg.xft.XFTItem;

import java.nio.file.Path;
import java.util.List;

public interface TransportService {
    List<Path> transport(String destinationName, XFTItem... items);

    List<Path> transport(String destinationName, Path... file);

    void registerDestination(Destination destination);

    Destination getDestinationByName(String destinationName);
}
