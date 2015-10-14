
package org.mababio.spring.vltool.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.function.Function;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;




@Document
public class Stock implements Serializable {
	
	private static final long serialVersionUID = 6458020599086707522L;
	
	/*The primary key for this Mongo Document is a composite key -> vl.dakeToken + stkName*/
	@Id
	private String vl_DateToken_stkName;
	private ValueLine valueLine;
	private String stkName;
	private String ticker;
	private BigDecimal vlinePrice;
	private BigDecimal vlinePercentage;
	/*new fields that were extracted*/
	private  Integer pageNumber;
	private   Double changeInPrice;
	private   Integer perRank;
	private   Integer techRank;
	private   Integer safetyRank;




	/*Getter and Setter*/

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Double getChangeInPrice() {
		return changeInPrice;
	}

	public void setChangeInPrice(Double changeInPrice) {
		this.changeInPrice = changeInPrice;
	}

	public Integer getPerRank() {
		return perRank;
	}

	public void setPerRank(Integer perRank) {
		this.perRank = perRank;
	}

	public Integer getTechRank() {
		return techRank;
	}

	public void setTechRank(Integer techRank) {
		this.techRank = techRank;
	}

	public Integer getSafetyRank() {
		return safetyRank;
	}

	public void setSafetyRank(Integer safetyRank) {
		this.safetyRank = safetyRank;
	}

	public BigDecimal getVlinePercentage() {
		return vlinePercentage;
	}

	public void setVlinePercentage(BigDecimal vlinePercentage) {
		this.vlinePercentage = vlinePercentage;
	}



	

		
	public String getStkName() {
		return stkName;
	}

	public Stock  setStkName(String stkName) {
		this.stkName = stkName;
		return this;
	}

	public void setStkName(String stkname, Function<String,String> function){
		this.stkName = function.apply(stkname);
	}
	
	public String getVl_DateToken_stkName() {
		return vl_DateToken_stkName;
	}

	public void setVl_DateToken_stkName(String vl_DateToken_stkName) {
		this.vl_DateToken_stkName = vl_DateToken_stkName;
	}

	public String getTicker() {
		return Ticker;
	}

	public void setTicker(String stkTicker) {
		this.Ticker = stkTicker;
	}

	public BigDecimal getVlinePrice() {
		return vlinePrice;
	}

	public void setVlinePrice(BigDecimal vlinePrice) {
		this.vlinePrice = vlinePrice;
	}

	public ValueLine getValueLine() {
		return valueLine;
	}

	public void setValueLine(ValueLine valueLine) {
		this.valueLine = valueLine;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}




k
	
	
}
