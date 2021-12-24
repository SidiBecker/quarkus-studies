package com.sidneibecker.quarkus.startup;

import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.impossibl.postgres.api.jdbc.PGConnection;
import com.impossibl.postgres.api.jdbc.PGNotificationListener;
import com.impossibl.postgres.jdbc.PGDataSource;
import com.sidneibecker.quarkus.kafka.UserProducer;
import com.sidneibecker.quarkus.util.Util;

import io.quarkus.runtime.Startup;

@Singleton
@Startup
public class DatabaseListener {

	@Inject
	private UserProducer userProducer;

	@PostConstruct
	public void init() {
		System.out.println("Starting database listeners...");

		PGDataSource dataSource = new PGDataSource();
		dataSource.setHost(Util.getApplicationProperty("custom.datasource.host"));
		dataSource.setPort(Integer.parseInt(Util.getApplicationProperty("custom.datasource.port")));
		dataSource.setDatabaseName(Util.getApplicationProperty("custom.datasource.name"));
		dataSource.setUser(Util.getApplicationProperty("quarkus.datasource.username"));
		dataSource.setPassword(Util.getApplicationProperty("quarkus.datasource.password"));

		try {
			PGConnection connection = (PGConnection) dataSource.getConnection();

			System.out.println("PG CONNECTON: " + connection);
			Statement listenStatement = connection.createStatement();
			listenStatement.execute("LISTEN database_events");
			listenStatement.close();

			/// Do not let this reference go out of scope!
			PGNotificationListener listener = new PGNotificationListener() {

				@Override
				public void notification(int processId, String channelName, String payload) {
					System.out.println("Received Notification from database: " + payload);

					userProducer.emittUser(payload);

				}

				public void closed() {
					System.out.println("Database listeners closed");
				}
			};
			connection.addNotificationListener(listener);

			System.out.println("Database listeners started");

		} catch (Exception e) {
			System.err.println(e);
		}

	}

}
