package com.sidneibecker.quarkus.startup;

import java.sql.Statement;

import com.impossibl.postgres.api.jdbc.PGConnection;
import com.impossibl.postgres.api.jdbc.PGNotificationListener;
import com.impossibl.postgres.jdbc.PGDataSource;
import com.sidneibecker.quarkus.util.Util;

public class DatabaseListener {

	public static void init() {
		System.out.println("Starting database listeners...");

		PGDataSource dataSource = new PGDataSource();
		dataSource.setHost(Util.getApplicationProperty("quarkus.datasource.host"));
		dataSource.setPort(Integer.parseInt(Util.getApplicationProperty("quarkus.datasource.port")));
		dataSource.setDatabaseName(Util.getApplicationProperty("quarkus.datasource.name"));
		dataSource.setUser(Util.getApplicationProperty("quarkus.datasource.username"));
		dataSource.setPassword(Util.getApplicationProperty("quarkus.datasource.password"));

		try (PGConnection connection = (PGConnection) dataSource.getConnection()) {

			Statement statement = connection.createStatement();
			statement.execute("LISTEN database_events");
			statement.close();
			connection.addNotificationListener(new PGNotificationListener() {
				public void notification(int processId, String channelName, String payload) {
					System.out.println("Received Notification: process id: " + processId + ", channel: " + channelName
							+ ", message: " + payload);
				}

				public void closed() {
					System.out.println("Database listeners closed");
				}

			});
			System.out.println("Database listeners started");
			while (true) {
				Thread.sleep(500);
			}
		} catch (Exception e) {
			System.err.println(e);
		}

	}

}
