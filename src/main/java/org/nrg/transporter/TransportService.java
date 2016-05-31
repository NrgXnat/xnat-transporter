package org.nrg.transporter;

import org.nrg.xdat.om.base.BaseXnatExperimentdata.UnknownPrimaryProjectException;
import org.nrg.xft.XFTItem;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface TransportService {
    List<Path> transport(String destinationName, XFTItem... items) throws UnknownPrimaryProjectException;

    Map<Path, Path> transport(String destinationName, Path... file);

    Path transport(String destinationName, Path file);

    Path getWritableDirectory(String destinationName);

    List<Path> getWritableDirectories(String destinationName, final int howMany);

    void registerDestination(Destination destination);

    Destination getDestinationByName(String destinationName);
}
