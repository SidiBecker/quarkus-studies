package com.sidneibecker.quarkus.kafka.impl;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import io.smallrye.reactive.messaging.kafka.Record;

@ApplicationScoped
public class UserConsumerImpl {

	private final Logger logger = Logger.getLogger(UserConsumerImpl.class);

	@Incoming("users")
	public void receive(Record<String, String> record) {
		logger.infof("event from user: %s", record.value());
	}

}