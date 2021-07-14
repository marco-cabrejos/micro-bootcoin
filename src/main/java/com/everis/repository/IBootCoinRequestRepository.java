package com.everis.repository;

import com.everis.model.BootCoinRequest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBootCoinRequestRepository extends IRepository<BootCoinRequest, String> {
	
	Mono<BootCoinRequest> findByTransactionNumber(String transactionNumber);
	Flux<BootCoinRequest> findByBootCoinSellerIdentityTypeAndBootCoinSellerIdentityNumber(String identityType, String identityNumber);
	
}