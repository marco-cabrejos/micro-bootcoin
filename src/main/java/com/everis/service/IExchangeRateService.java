package com.everis.service;

import com.everis.model.ExchangeRate;

import reactor.core.publisher.Mono;

public interface IExchangeRateService extends ICRUDService<ExchangeRate, String> {
	Mono<ExchangeRate> disablePreviousAndCreate(ExchangeRate exchangeRate);
	Mono<ExchangeRate> findByDisabled(Boolean disabled);
}
