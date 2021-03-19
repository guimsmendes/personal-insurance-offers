package com.mendes.insuranceoffers.usecase.domain;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProponentDomain {
	
	private LocalDate birthDate;
	private AccountDomain account;
	
	@Getter
	@Setter
	public static class AccountDomain{
		private int bankId;
		private int agencyId;
		private int accountId;
		private String accountVerificationDigit;
	}

}
