package com.sidneibecker.quarkus.kafka;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.logging.Logger;

import io.smallrye.reactive.messaging.kafka.Record;

@ApplicationScoped
public class UserConsumer {

	private final Logger logger = Logger.getLogger(UserConsumer.class);

	// @Incoming("users")
	public void receive(Record<Integer, String> record) {
		logger.infof("Got a movie: %d - %s", record.key(), record.value());
	}

}