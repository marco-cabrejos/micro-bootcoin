package com.everis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.everis.model.BootCoinRequest;
import com.everis.model.ExchangeRate;
import com.everis.repository.IBootCoinRequestRepository;
import com.everis.repository.IRepository;
import com.everis.service.IBootCoinRequestService;
import com.everis.service.IExchangeRateService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class BootCoinRequestServiceImpl extends CRUDServiceImpl<BootCoinRequest, String> implements IBootCoinRequestService {

	@Autowired
	private IBootCoinRequestRepository repository;
	@Autowired
	private IExchangeRateService exchangeService;

	@Override
	protected IRepository<BootCoinRequest, String> getRepository() {
		return repository;
	}

	@Override
	public Mono<BootCoinRequest> findByTransactionNumber(String transactionNumber){
		log.info("findByAccountNumber");
		return repository.findByTransactionNumber(transactionNumber);
	}

	@Override
	public Mono<BootCoinRequest> validateAndCreate(BootCoinRequest request) {
		log.info("validating and then create");
		Mono<ExchangeRate> monoExchange = exchangeService.findByDisabled(false)
				.switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "No existe un tipo de cambio, comuníquese con el administrador.")));
		Mono<BootCoinRequest> monoRequest = Mono.just(request);
		return monoRequest.zipWith(monoExchange,(a,b)->{
			a.setExchangeRate(b);
			a.setAmountToPay(b.getValue()*a.getAmount());
			return a;
		}).flatMap(r->{
			return create(request);
		});
	}

	@Override
	public Flux<BootCoinRequest> findByBootCoinBuyerIdentityTypeAndBootCoinBuyerIdentityNumber(String identityType,
			String identityNumber) {
		return repository.findByBootCoinSellerIdentityTypeAndBootCoinSellerIdentityNumber(identityType, identityNumber);
	}
	
}