package com.microprofile.study.config.config;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/config")
@ApplicationScoped
@Produces(MediaType.TEXT_PLAIN)
public class ConfigTestController {

    @Inject
    @ConfigProperty(name = "injected.value")
    private String injectedValue;

    @Inject
    @ConfigProperty(name = "property")
    private String serverProperty;

    @Inject
    @ConfigProperty(name = "system.property")
    private String systemProperty;

    @Path("/injected")
    @GET
    public String getInjectedConfigValue() {
        return "Config value as Injected by CDI " + injectedValue;
    }

    @Path("/lookup")
    @GET
    public String getLookupConfigValue() {
        Config config = ConfigProvider.getConfig();
        String value = config.getValue("value", String.class);
        return "Config value from ConfigProvider " + value;
    }

    @Path("/server")
    @GET
    public String getServerProperty() {
        return "Config value as Injected by CDI " + serverProperty;
    }

    @Path("/system")
    @GET
    public String getSystemProperty() {
        return "Config value as Injected by CDI " + systemProperty;
    }
}
