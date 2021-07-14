package com.everis.model;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "bootCoinRequest")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class BootCoinRequest {
	
	@Id
	private String id;
	
	@Field(name = "transactionNumber")
	private String transactionNumber;
	
	@Field(name = "bootCoinBuyer")
	@NotNull(message = "El campo Comprador es oblitagorio")
	private @Valid BootCoinBuyer bootCoinBuyer;
	
	@Field(name = "bootCoinSeller")
	@NotNull(message = "El campo Vendedor es oblitagorio")
	private @Valid BootCoinSeller bootCoinSeller;
	
	@Field(name = "amount")
	@NotNull(message = "Debe ingresar un monto de bootcoin a comprar")
	private Double amount;
	
	@Field(name = "paymentMethod")
	@NotEmpty(message = "Debe ingresar un medio de pago")
	private String paymentMethod;
	
	@Field(name = "exchangeRate")
	private ExchangeRate exchangeRate;
	
	private Double amountToPay;
	
}
