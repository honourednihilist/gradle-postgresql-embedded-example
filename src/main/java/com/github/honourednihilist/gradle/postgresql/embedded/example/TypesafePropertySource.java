package com.github.honourednihilist.gradle.postgresql.embedded.example;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;

import org.springframework.core.env.PropertySource;

public class TypesafePropertySource extends PropertySource<Config> {

	public TypesafePropertySource(String name, Config source) {
		super(name, source);
	}

	@Override
	public Object getProperty(String name) {
		try {
			return source.getAnyRef(name);
		} catch (ConfigException ignored) {
			return null;
		}
	}
}
