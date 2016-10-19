package org.nrg.transporter;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.nrg.xdat.XDAT;
import org.nrg.xdat.om.XnatMrsessiondata;
import org.nrg.xdat.om.base.BaseXnatExperimentdata.UnknownPrimaryProjectException;
import org.nrg.xdat.preferences.SiteConfigPreferences;
import org.nrg.xft.XFTItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class TransportServiceImpl implements TransportService {
    @Autowired private SiteConfigPreferences siteConfigPreferences;

    @Override
    public List<Path> transport(final String destinationName, XFTItem... items) throws UnknownPrimaryProjectException {
        List<Path> paths = Lists.newArrayList();
        for (final XFTItem item : items) {
            if (item.getXSIType().equals("xnat:mrSessionData")) {
                final XnatMrsessiondata session = new XnatMrsessiondata(item);
                    paths.add(Paths.get(session.getRelativeArchivePath()));
            }
        }
        return paths;
    }

    @Override
    public Map<Path, Path> transport(final String destinationName, final Path... files) {
        final Map<Path, Path> sourcePathToDestinationPath = Maps.newHashMap();
        for (final Path file : files) {
            sourcePathToDestinationPath.put(file, file);
        }
        return sourcePathToDestinationPath;
    }

    @Override
    public Path transport(final String destinationName, final Path file) {
        return file;
    }

    @Override
    public Path getWritableDirectory(final String destinationName) {
        // TODO The destination should define where its build space is

        final String buildPathStr = siteConfigPreferences.getBuildPath();

        final String s = UUID.randomUUID().toString();

        final Path buildPath = Paths.get(buildPathStr, s);
        if (buildPath.toFile().mkdirs()) {
            return buildPath;
        }

        // TODO should throw an error here
        return null;
    }

    @Override
    public List<Path> getWritableDirectories(final String destinationName, final int howMany) {
        if (howMany <= 0) {
            return null;
        }
        final List<Path> buildPaths = Lists.newArrayListWithExpectedSize(howMany);
        for (int i=0; i<howMany; i++) {
            buildPaths.add(getWritableDirectory(destinationName));
        }
        return buildPaths;
    }

    @Override
    public void registerDestination(final Destination destination) {

    }

    @Override
    public Destination getDestinationByName(final String destinationName) {
        return null;
    }
}
