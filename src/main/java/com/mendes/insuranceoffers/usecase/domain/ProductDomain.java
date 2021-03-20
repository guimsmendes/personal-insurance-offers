package com.mendes.insuranceoffers.usecase.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDomain {
	
	private int productId;
	private int branchId;
	private String productName;
	private List<CoverDomain> covers;
	
	@Getter
	@Setter
	public static class CoverDomain {
		private String coverId;
		private String coverName;
		List<CoverSummaryDomain> coverSummaries;
		
		@Getter
		@Setter
		public static class CoverSummaryDomain {
			private int order;
			private String content;
			private String type;
			private String groupBy;
		}
	}

}
