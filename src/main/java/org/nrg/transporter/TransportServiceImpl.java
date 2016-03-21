package org.nrg.transporter;

import com.google.common.collect.Lists;
import org.nrg.xdat.om.XnatMrsessiondata;
import org.nrg.xft.XFTItem;
import org.nrg.xnat.turbine.utils.ArcSpecManager;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;

@Service
public class TransportServiceImpl implements TransportService {
    @Override
    public List<Path> transport(final String destinationName, XFTItem... items) {
        for (final XFTItem item : items) {
            if (item.getXSIType().equals("xnat:mrSessionData")) {
                final XnatMrsessiondata session = new XnatMrsessiondata(item);

                ArcSpecManager.GetInstance().getArchivePathForProject(session.getProject());
                session.getFileResources()
            }
        }
        return null;
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
