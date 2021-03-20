package com.mendes.insuranceoffers.usecase.domain;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProponentDomain {
	private String name;
	private LocalDate birthDate;
}
