package com.falchus.lib.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

@RequiredArgsConstructor
public class MySQL {

	@Delegate private final HikariDataSource data;
	
	public MySQL(@NonNull String host, int port, @NonNull String database, @NonNull String username, String password) {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("com.mysql.cj.jdbc.Driver");
		config.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + database 
				+ "?useSSL=false&characterEncoding=UTF-8&useUnicode=true");
		config.setUsername(username);
		config.setPassword(password == null ? "" : password);
		data = new HikariDataSource(config);
	}
	
	public void disconnect() {
		if (data != null && !data.isClosed()) {
			data.close();
		}
	}
	
	public boolean isConnected() {
		return data != null && !data.isClosed();
	}
}
