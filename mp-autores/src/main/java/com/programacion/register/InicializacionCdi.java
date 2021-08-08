package com.programacion.register;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.NewService;

 

@ApplicationScoped
public class InicializacionCdi {

	public static final String ID = UUID.randomUUID().toString();
	@Inject
	@ConfigProperty(name = "configsource.consul.host", defaultValue = "127.0.0.1")
	private String consulHost;

	@ConfigProperty(name = "app.name")
	private String appName;

	@Inject
	@ConfigProperty(name = "quarkus.http.port", defaultValue = "8080")
	private Integer appPort;
	public void init(@Observes @Initialized(ApplicationScoped.class) Object obj) throws UnknownHostException {
		System.out.println("**init");
		
		ConsulClient client = new ConsulClient(consulHost);
		NewService newService = new NewService();
		newService.setId(ID);
		newService.setName(appName);
		newService.setPort(appPort);
		newService.setAddress(InetAddress.getLocalHost().getHostAddress());

		//Health
		NewService.Check check = new NewService.Check();
		check.setMethod("GET");
		check.setHttp("http://" + "127.0.0.1" + ":" + appPort + "/q/health");
		check.setInterval("4s");
		check.setDeregisterCriticalServiceAfter("5s");
		newService.setCheck(check);
		
		// TAGs TRAEFIK
		// RULE: traefik.http.routes.<router_name>.rule=
		String rule = String.format("traefik.http.routers.%s.rule=PathPrefix(`/%s`)", appName, appName);
		String middleware = String.format("traefik.http.middlewares.%s.stripprefix.prefixes=/%s", appName, appName);
		String mids = String.format("traefik.http.routers.%s.middlewares=%s", appName, appName);
		newService.setTags(Arrays.asList(rule,middleware,mids));
		client.agentServiceRegister(newService);
	}

	public void destroy(@Observes @Destroyed(ApplicationScoped.class) Object obj) {
		System.out.println("**destroy");
		ConsulClient client = new ConsulClient(consulHost);
		client.agentServiceDeregister(ID);
	}
	

}