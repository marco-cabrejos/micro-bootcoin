package com.everis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.model.ExchangeRate;
import com.everis.repository.IExchangeRateRepository;
import com.everis.repository.IRepository;
import com.everis.service.IExchangeRateService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ExchangeRateServiceImpl extends CRUDServiceImpl<ExchangeRate, String> implements IExchangeRateService {

	@Autowired
	private IExchangeRateRepository repository;

	@Override
	protected IRepository<ExchangeRate, String> getRepository() {
		return repository;
	}

	@Override
	public Mono<ExchangeRate> disablePreviousAndCreate(ExchangeRate exchangeRate) {
		log.info("disablePreviousAndCreate");
		Mono<ExchangeRate> monoEx = Mono.just(exchangeRate); 
		return findAll()
				.flatMap(i->{
					i.setDisabled(true);
					return update(i);
				}).collectList().zipWith(monoEx,(a,b)->{
					b.setDisabled(false);
					return b;
				}).flatMap(ex->{
					return create(ex);
				});
	}

	@Override
	public Mono<ExchangeRate> findByDisabled(Boolean disabled) {
		return repository.findByDisabled(disabled);
	}

}