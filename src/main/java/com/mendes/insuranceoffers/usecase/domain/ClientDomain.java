package com.mendes.insuranceoffers.usecase.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDomain {
	
	private static int NINE_DIGITS = 9;
	
	private UUID clientId;
	private String idType;
	private String idNumber;
	private String fullName;
	private LocalDate birthDate;
	private char gender;
	private int maritalStatus;
	private List<AddressDomain> addresses;
	private List<PhoneDomain> phoneNumbers;
	private List<EmailDomain> emails;
	
	@Getter
	@Setter
	public static class AddressDomain {
		private UUID addressId;
		private String addressLine;
		private String addressNumber;
		private String addressApartment;
		private String zipCode;
		private String city;
		private String province;
		private String state;
		private String country;
		private LocalDateTime updateTime;
	}
	
	@Getter
	@Setter
	public static class PhoneDomain {
		private UUID phoneId;
		private boolean main;
		private boolean cellphone;
		private int countryCode;
		private int areaCode;
		private int number;
		private LocalDateTime updateTime;
	}
	
	@Getter
	@Setter
	public static class EmailDomain {
		private UUID emailId;
		private boolean main;
		private String email;
		private LocalDateTime updateTime;
	}

}
