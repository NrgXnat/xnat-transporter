package org.nrg.transporter;

import com.google.common.collect.Lists;
import org.nrg.xdat.om.XnatMrsessiondata;
import org.nrg.xdat.om.base.BaseXnatExperimentdata.UnknownPrimaryProjectException;
import org.nrg.xft.XFTItem;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class TransportServiceImpl implements TransportService {
    @Override
    public List<Path> transport(final String destinationName, XFTItem... items) throws UnknownPrimaryProjectException {
        List<Path> paths = Lists.newArrayList();
        for (final XFTItem item : items) {
            if (item.getXSIType().equals("xnat:mrSessionData")) {
                final XnatMrsessiondata session = new XnatMrsessiondata(item);
                    paths.add(Paths.get(session.getArchivePath()));
            }
        }
        return paths;
    }

    @Override
    public List<Path> transport(final String destinationName, final Path... files) {
        return Lists.newArrayList(files);
    }

    @Override
    public void registerDestination(final Destination destination) {

    }

    @Override
    public Destination getDestinationByName(final String destinationName) {
        return null;
    }
}
