package com.sidneibecker.quarkus.startup;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class Startup {

	void onStart(@Observes StartupEvent ev) {
		System.out.println("Running onStart...");
		//DatabaseListener.init();
	}

}