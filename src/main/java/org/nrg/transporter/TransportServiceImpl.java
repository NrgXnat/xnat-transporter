package org.nrg.transporter;

import org.nrg.xft.ItemI;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;

@Service
public class TransportServiceImpl implements TransportService {
    @Override
    public Path transport(final ItemI item, final String destinationName) {
        return null;
    }

    @Override
    public List<Path> transport(final List<ItemI> items, final String destinationName) {
        return null;
    }

    @Override
    public void registerDestination(final Destination destination) {

    }

    @Override
    public Destination getDestinationByName(final String destinationName) {
        return null;
    }
}
