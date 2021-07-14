package com.everis.service;

import com.everis.model.BootCoinRequest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBootCoinRequestService extends ICRUDService<BootCoinRequest, String> {
	Mono<BootCoinRequest> findByTransactionNumber(String transactionNumber);
	Mono<BootCoinRequest> validateAndCreate(BootCoinRequest request);
	Flux<BootCoinRequest> findByBootCoinBuyerIdentityTypeAndBootCoinBuyerIdentityNumber(String identityType, String identityNumber);
}
