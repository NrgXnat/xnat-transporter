package org.nrg.transporter.config;

import org.nrg.framework.annotations.XnatPlugin;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@XnatPlugin(value = "transporter", description = "XNAT Transporter")
@ComponentScan("org.nrg.transporter")
public class TransporterConfig {
}
