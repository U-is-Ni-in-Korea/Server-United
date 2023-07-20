package com.universe.uni.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import io.sentry.ITransaction;
import io.sentry.Sentry;
import io.sentry.SentryEvent;
import io.sentry.SentryLevel;
import io.sentry.protocol.Message;
import io.sentry.protocol.Request;
import io.sentry.protocol.SentryException;
import io.sentry.protocol.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class SentryEventSender {

	private final SentryEvent sentryEvent;

	private SentryEventSender(
		Exception exception,
		HttpHeaders header,
		HttpStatus status,
		ServletWebRequest request
	) {
		final User sentryUser = getUser(request);
		final Request sentryRequest = getRequest(request.getRequest(), header);
		final String requestMessage = makeTransactionMessage(request.getRequest(), status);
		final ITransaction sentryTransaction = Sentry.startTransaction(requestMessage, "request-api");
		Sentry.configureScope(scope -> {
			scope.setRequest(sentryRequest);
			scope.setUser(sentryUser);
		});
		final SentryEvent event = new SentryEvent();
		event.setUser(sentryUser);
		event.setRequest(sentryRequest);
		event.setLevel(SentryLevel.ERROR);
		event.setTransaction(sentryTransaction.getName());
		event.setMessage(makeSentryMessage(requestMessage));
		event.setExceptions(List.of(makeSentryException(exception)));
		this.sentryEvent = event;
	}

	public static SentryEventSender of(
		Exception exception,
		HttpHeaders headers,
		HttpStatus status,
		WebRequest request
	) {
		return new SentryEventSender(
			exception,
			headers,
			status,
			(ServletWebRequest)request
		);
	}

	private User getUser(ServletWebRequest request) {
		final String userId = getUserIdByPrincipal(request);
		final String requestIpAddress = request.getRequest().getRemoteAddr();
		log.info(String.format("user Id : " + userId));
		log.info(String.format("request Ip Address : " + requestIpAddress));
		final User user = new User();
		user.setId(Optional.ofNullable(userId).orElse("Unknown"));
		user.setIpAddress(requestIpAddress);
		return user;
	}

	private String getUserIdByPrincipal(ServletWebRequest request) {
		try {
			Principal principal = request.getUserPrincipal();
			return Objects.requireNonNull(principal).getName();
		} catch (NullPointerException exception) {
			return null;
		}
	}

	private Request getRequest(HttpServletRequest request, HttpHeaders headers) {
		final String requestUrl = extractRequestUri(request);
		final String requestMethod = extractRequestMethod(request);
		final String requestBody = extractRequestBody(request);
		final Request sentryRequest = new Request();
		sentryRequest.setUrl(requestUrl);
		sentryRequest.setMethod(requestMethod);
		sentryRequest.setData(requestBody);
		sentryRequest.setQueryString(request.getQueryString());
		sentryRequest.setHeaders(headers.toSingleValueMap());
		return sentryRequest;
	}

	private String extractRequestUri(HttpServletRequest request) {
		return request.getRequestURI();
	}

	private String extractRequestMethod(HttpServletRequest request) {
		return request.getMethod();
	}

	private String extractRequestBody(HttpServletRequest request) {
		try {
			final BufferedReader reader = request.getReader();
			StringBuilder requestBody = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				requestBody.append(line);
			}
			return requestBody.toString();
		} catch (IOException exception) {
			return "";
		}
	}

	private String makeTransactionMessage(HttpServletRequest request, HttpStatus status) {
		return String.format(
			"%s %s %s",
			status.toString(),
			extractRequestMethod(request),
			extractRequestUri(request)
		);
	}

	private Message makeSentryMessage(String requestMessage) {
		final Message message = new Message();
		message.setMessage(requestMessage);
		return message;
	}

	private SentryException makeSentryException(Exception exception) {
		final StringWriter stringWriter = new StringWriter();
		exception.printStackTrace(new PrintWriter(stringWriter));
		final String exceptionAsString = stringWriter.toString();
		final SentryException sentryException = new SentryException();
		sentryException.setType(exception.getClass().getSimpleName());
		sentryException.setValue(exceptionAsString);
		return sentryException;
	}

	public void sendEvent() {
		Sentry.captureEvent(sentryEvent);
	}
}
