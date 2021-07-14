package com.everis.model;

import javax.validation.constraints.NotEmpty;

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
@Document(collection = "bootCoinCustomer")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class BootCoinBuyer {
	@Id
	private String id;
	
	@Field("identityType")
	@NotEmpty(message = "Tipo de documento de identidad del comprador es obligatorio")
	private String identityType;
	
	@Field("identityNumber")
	@NotEmpty(message = "Número de documento de identidad del comprador es obligatorio")
	private String identityNumber;
	
	@Field("phoneNumber")
	@NotEmpty(message = "Número de teléfono del comprador es obligatorio")
	private String phoneNumber;
	
	@Field("mail")
	@NotEmpty(message = "Correo electrónico del comprador es obligatorio")
	private String mail;
}
