package com.sidneibecker.quarkus.kafka.impl;

import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import com.sidneibecker.quarkus.kafka.UserProducer;

public class UserProducerImpl implements UserProducer {

	@Inject
	@Channel("users-out")
	Emitter<String> userEmmiter;

	public void emittUser(String message) {
		System.out.println("Sending " + message + " to kafka user topic...");

		try {
			userEmmiter.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}