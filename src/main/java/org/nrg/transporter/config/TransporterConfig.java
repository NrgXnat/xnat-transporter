package org.nrg.transporter.config;

import org.nrg.framework.annotations.XnatModule;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@XnatModule(value = "transporter", description = "XNAT Transporter", config = TransporterConfig.class)
@ComponentScan("org.nrg.transporter")
public class TransporterConfig {
}
