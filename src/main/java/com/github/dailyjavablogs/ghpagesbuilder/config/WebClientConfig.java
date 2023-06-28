package com.github.dailyjavablogs.ghpagesbuilder.config;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.boot.autoconfigure.codec.CodecProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;

@Configuration
public class WebClientConfig {

	@Bean
	WebClient webClient(final CodecProperties codecProperties) throws SSLException {
		final SslContext sslContext = SslContextBuilder
			.forClient()
			.trustManager(InsecureTrustManagerFactory.INSTANCE)
			.build();

		final HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));
		return WebClient.builder()
			.clientConnector(new ReactorClientHttpConnector(httpClient))
			.exchangeStrategies(exchangeStrategies(codecProperties))
			.build();
	}

	private ExchangeStrategies exchangeStrategies(final CodecProperties codecProperties) {
		return ExchangeStrategies.builder()
			.codecs(codecs -> codecs.defaultCodecs()
				.maxInMemorySize(Long.valueOf(codecProperties.getMaxInMemorySize().toBytes()).intValue()))
			.build();
	}
}
