package org.nrg.transporter;

import org.nrg.xdat.om.base.BaseXnatExperimentdata.UnknownPrimaryProjectException;
import org.nrg.xft.XFTItem;

import java.nio.file.Path;
import java.util.List;

public interface TransportService {
    List<Path> transport(String destinationName, XFTItem... items) throws UnknownPrimaryProjectException;

    List<Path> transport(String destinationName, Path... file);

    void registerDestination(Destination destination);

    Destination getDestinationByName(String destinationName);
}
