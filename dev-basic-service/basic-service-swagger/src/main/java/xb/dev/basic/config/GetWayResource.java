package xb.dev.basic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Created by huangxb on 2018-9-12 10:26.
 */
@Component
@Primary
public class GetWayResource implements SwaggerResourcesProvider {

    @Autowired
    private final RouteLocator routeLocator;

    public GetWayResource(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList();
        resources.add(this.swaggerResource("default", "/v2/api-docs", "1.0"));
        List<Route> routes = this.routeLocator.getRoutes();
        routes.forEach(route ->
                resources.add(this.swaggerResource(route.getId(), route.getFullPath().replace("**", "v2/api-docs"), "1.0"))
        );
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
