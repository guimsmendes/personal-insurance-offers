package com.mendes.insuranceoffers.dataprovider.http.model.response;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(SnakeCaseStrategy.class)
public class ProductsCatalogCoverHttpResponse {
	
	private String productId;
	private String branchId;
	private String productName;
	private List<Cover> covers;
	
	@Getter
	@Setter
	@JsonNaming(SnakeCaseStrategy.class)
	public static class Cover{
		private String coverId;
		private String coverName;
		private List<CoverSummary> coverSummaries;
		
		@Getter
		@Setter
		@JsonNaming(SnakeCaseStrategy.class)
		public static class CoverSummary{
			private String order;
			private String content;
			private String type;
			private String groupBy;
		}
		
	}
	
	

}
