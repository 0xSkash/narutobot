package de.skash.narutobot.core.networking;

import java.lang.reflect.Type;

public class Route {
    private final HttpMethod method;
    private final String route;
    private final Type responseType;

    public Route(HttpMethod method, String route, Type responseType) {
        this.method = method;
        this.route = route;
        this.responseType = responseType;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getRoute() {
        return route;
    }

    public Type getResponseType() {
        return responseType;
    }
}
