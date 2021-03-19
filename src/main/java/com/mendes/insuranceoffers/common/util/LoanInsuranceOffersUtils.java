package com.mendes.insuranceoffers.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.Validation;
import javax.validation.Validator;

import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;
import static org.apache.commons.lang3.math.NumberUtils.isDigits;

public class LoanInsuranceOffersUtils {
	
	private LoanInsuranceOffersUtils() {}
	
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	
	private static final int DECIMAL_PLACES_NUMBER = 2;
	private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
	private static final int CPF_DIGITS_NUMBER = 14;
	private static final int CNPJ_DIGITS_NUMBER = 14;
	
	
	public static Validator beanValidator() {
		return Validation.buildDefaultValidatorFactory().getValidator();
	}
	
	public static BigDecimal round(BigDecimal value) {
		return isNotEmpty(value) ? value.setScale(DECIMAL_PLACES_NUMBER, ROUNDING_MODE) : value;
	}
	
	
	public static String generateCorrelationTimestamp() {
		return DATE_TIME_FORMATTER.format(ZonedDateTime.now().withZoneSameInstant(ZoneId.of("America/Sao_Paulo")));
	}
	
	public static String getCPFNumber(String cpf) {
		if(isAValidCPF(cpf)) {
			return cpf.substring(3,12);
		}
		throw new IllegalArgumentException("Unable to obtain CPF number. Invalid format. CPF: " + cpf);
	}
	
	
	public static String geCPFVerificationDigit(String cpf) {
		if(isAValidCPF(cpf)) {
			return cpf.substring(12);
		}
		throw new IllegalArgumentException("Unable to obtain CPF verification digit. Invalid format.. CPF: " + cpf);
	}
	
	private static boolean isAValidCPF(String cpf) {
		return isNotEmpty(cpf)
				&& cpf.length() == CPF_DIGITS_NUMBER
				&& isDigits(cpf);
	}
	
	public static String getCNPJCode(String cnpj) {
		if(isAValidCNPJ(cnpj)) {
			return cnpj.substring(0,8);
		}
		throw new IllegalArgumentException("Unable to obtain CPNJ number. Invalid format. CNPJ: " + cnpj);
	}
	
	public static String getCNPJBranchNumber(String cnpj) {
		if(isAValidCNPJ(cnpj)) {
			return cnpj.substring(8,12);
		}
		throw new IllegalArgumentException("Unable to obtain CPNJ branch number. Invalid format. CNPJ: " + cnpj);
	}
	
	
	public static String getCNPJVerificationDigit(String cnpj) {
		if(isAValidCNPJ(cnpj)) {
			return cnpj.substring(12);
		}
		throw new IllegalArgumentException("Unable to obtain CPNJ verification digit. Invalid format. CNPJ: " + cnpj);
	}
	
	private static boolean isAValidCNPJ(String cnpj) {
		return isNotEmpty(cnpj)
				&& cnpj.length() == CNPJ_DIGITS_NUMBER
				&& isDigits(cnpj);
	}
	
	public static String trim(String value) {
		return isNotEmpty(value) ? value.trim() : value;
	}
	
	
}
