package com.universe.uni.external;

import io.sentry.Scope;
import io.sentry.ScopeCallback;
import io.sentry.Sentry;
import io.sentry.protocol.Request;
import io.sentry.protocol.User;

import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class SentryExceptionSender {

    public static void captureException(Exception exception, WebRequest webRequest) {
        final HttpServletRequest request = getCurrentRequest(webRequest);
        setSentryConfigure(request);
        Sentry.captureException(exception);
    }

    private static HttpServletRequest getCurrentRequest(WebRequest webRequest) {
        return ((ServletWebRequest) webRequest).getRequest();
    }

    private static void setSentryConfigure(HttpServletRequest request) {
        Sentry.configureScope(new RequestConfigure(request));
    }

    private record RequestConfigure(
            HttpServletRequest request
    ) implements ScopeCallback {

        @Override
        public void run(Scope scope) {
            scope.setUser(getUser());
            scope.setRequest(getSentryRequest());
        }

        private User getUser() {
            final User user = new User();
            user.setId(getUserIdByPrincipal());
            user.setIpAddress(request.getRemoteAddr());
            return user;
        }

        private String getUserIdByPrincipal() {
            Principal principal = request.getUserPrincipal();
            return Optional.ofNullable(principal.getName())
                    .orElseGet(() -> "Unknown");
        }

        private Request getSentryRequest() {
            final Request sentryRequest = new Request();
            sentryRequest.setHeaders(getHeader());
            sentryRequest.setMethod(request.getMethod());
            sentryRequest.setUrl(request.getRequestURI());
            sentryRequest.setData(getRequestBody());
            return sentryRequest;
        }

        private Map<String, String> getHeader() {
            final Map<String, String> header = new HashMap<>();
            Collections.list(request.getHeaderNames()).forEach(headerName -> {
                header.put(headerName, request.getHeader(headerName));
            });
            return header;
        }

        private String getQueryString() {
            return Optional.ofNullable(request.getQueryString())
                    .orElseGet(() -> "null");
        }

        private String getRequestBody() {
            try(
                InputStream inputStream = request.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))
            ){
                StringBuilder stringBuilder = new StringBuilder();
                char[] charBuffer = new char[128];
                int bytesRead;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
                return stringBuilder.toString();
            } catch (IOException ex) {
                return "null";
            }
        }
    }
}
