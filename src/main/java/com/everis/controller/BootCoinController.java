package com.everis.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.everis.model.BootCoinRequest;
import com.everis.model.ExchangeRate;
import com.everis.service.IBootCoinRequestService;
import com.everis.service.IExchangeRateService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bootcoin")
public class BootCoinController {
	@Autowired
	private IBootCoinRequestService requestService;
	@Autowired
	private IExchangeRateService exchangeRateService;
	
	@PostMapping
	public Mono<ResponseEntity<BootCoinRequest>> createRequest(@Valid @RequestBody BootCoinRequest bootCoinRequest, 
			final ServerHttpRequest request){
		return requestService
				.validateAndCreate(bootCoinRequest)
				.flatMap(r->{
					return Mono.just(ResponseEntity
							.created(null)
							.contentType(MediaType.APPLICATION_JSON)
							.body(r));
				});
	}
	
	@GetMapping
	public Mono<ResponseEntity<List<BootCoinRequest>>> fetchRequests(){
		return requestService.findAll()
				.collectList()
				.flatMap(p->{
					return Mono.just(ResponseEntity.ok().body(p));
				})
				.defaultIfEmpty(ResponseEntity
						.noContent()
						.build());
	}
	
	@GetMapping("/seller")
	public Mono<ResponseEntity<List<BootCoinRequest>>> fetchRequestsBySeller(@RequestParam("identityType") String identityType, 
			@RequestParam("identityNumber") String identityNumber){
		return requestService.findByBootCoinBuyerIdentityTypeAndBootCoinBuyerIdentityNumber(identityType, identityNumber)
				.collectList()
				.flatMap(p->{
					return Mono.just(ResponseEntity.ok().body(p));
				})
				.defaultIfEmpty(ResponseEntity
						.noContent()
						.build());
	}

	@PostMapping("/exchangerate")
	public Mono<ResponseEntity<ExchangeRate>> createExchangeRate(@Valid @RequestBody ExchangeRate exchangeRate,
			final ServerHttpRequest request){
		return exchangeRateService.disablePreviousAndCreate(exchangeRate)
				.flatMap(e->{
					return Mono.just(ResponseEntity
							.created(null)
							.contentType(MediaType.APPLICATION_JSON)
							.body(e));
				});
	}
	
	@GetMapping("/exchangerate")
	public Mono<ResponseEntity<List<ExchangeRate>>> findAllExchangeRate(){
		return exchangeRateService.findAll()
				.collectList()
				.flatMap(list->{
					return Mono.just(ResponseEntity.ok().body(list)); 
				})
				.defaultIfEmpty(ResponseEntity
						.noContent()
						.build());				
	}
	@GetMapping("/exchangerate/current")
	public Mono<ResponseEntity<ExchangeRate>> findCurrentExchangeRate(){
		return exchangeRateService.findByDisabled(Boolean.FALSE)
				.flatMap(e->{
					return Mono.just(ResponseEntity.ok().body(e)); 
				})
				.defaultIfEmpty(ResponseEntity
						.noContent()
						.build());				
	}
}