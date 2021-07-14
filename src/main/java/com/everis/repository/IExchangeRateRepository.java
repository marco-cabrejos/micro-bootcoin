package com.everis.repository;

import com.everis.model.ExchangeRate;

import reactor.core.publisher.Mono;

public interface IExchangeRateRepository extends IRepository<ExchangeRate, String> {
	Mono<ExchangeRate> findByDisabled(Boolean disabled);
}