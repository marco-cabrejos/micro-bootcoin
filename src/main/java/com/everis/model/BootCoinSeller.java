package com.everis.model;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "bootCoinSeller")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class BootCoinSeller {
	@Field("identityType")
	@NotEmpty(message = "Tipo de documento de identidad del vendedor es obligatorio")
	private String identityType;
	@Field("identityNumber")
	@NotEmpty(message = "Número de documento de identidad del vendedor es obligatorio")
	private String identityNumber;
}
