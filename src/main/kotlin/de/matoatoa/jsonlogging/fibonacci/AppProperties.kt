package de.matoatoa.jsonlogging.fibonacci

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "de.matoatoa.app")
data class AppProperties(var name: String = "NOT_SET", var version: String = "NOT_SET")