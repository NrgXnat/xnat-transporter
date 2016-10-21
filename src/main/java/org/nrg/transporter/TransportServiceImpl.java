package org.nrg.transporter;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.nrg.xdat.XDAT;
import org.nrg.xdat.om.XnatMrsessiondata;
import org.nrg.xdat.om.base.BaseXnatExperimentdata.UnknownPrimaryProjectException;
import org.nrg.xdat.preferences.SiteConfigPreferences;
import org.nrg.xft.XFTItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class TransportServiceImpl implements TransportService {
    private static final Logger log = LoggerFactory.getLogger(TransportServiceImpl.class);

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
        if (log.isDebugEnabled()) {
            log.debug("Transporting all paths to themselves.");
        }
        final Map<Path, Path> sourcePathToDestinationPath = Maps.newHashMap();
        for (final Path file : files) {
            sourcePathToDestinationPath.put(file, file);
        }
        return sourcePathToDestinationPath;
    }

    @Override
    public Path transport(final String destinationName, final Path file) {
        if (log.isDebugEnabled()) {
            log.debug(String.format("Transporting file %s to itself.", file));
        }
        return file;
    }

    @Override
    public Path getWritableDirectory(final String destinationName) {
        // TODO The destination should define where its build space is

        final String buildPathStr = siteConfigPreferences.getBuildPath();

        final String s = UUID.randomUUID().toString();

        final Path buildPath = Paths.get(buildPathStr, s);
        if (log.isDebugEnabled()) {
            log.debug("Creating writable directory " + buildPath.toString());
        }
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
