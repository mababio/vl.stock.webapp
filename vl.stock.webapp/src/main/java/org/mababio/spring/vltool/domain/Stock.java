
package org.mababio.spring.vltool.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.function.Function;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



/*
*This object is a representation of a row from the page 47 of the valueline pdf file.
* it includes all fields that appears in the pdf, and a field for worst/best performer
* Annotated with Soring Data MongoDB
*/
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
	private Integer pageNumber;
	private Double changeInPrice;
	private Integer perRank;
	private Integer techRank;
	private Integer safetyRank;
	private String performanceType;


@Override
public String toString(){
	return this.stkName +" "+  this.ticker;
	
	
	
}


	/*Getter and Setter*/
	public String getPerformanceType() {
		return performanceType;
	}

	public Stock  setPerformanceType(String performanceType) {
		this.performanceType = performanceType;
		return this;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}

	public Stock setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
		return this;
	}

	public Double getChangeInPrice() {
		return changeInPrice;
	}

	public Stock setChangeInPrice(Double changeInPrice) {
		this.changeInPrice = changeInPrice;
		return this;
	}

	public Integer getPerRank() {
		return perRank;
	}

	public Stock setPerRank(Integer perRank) {
		this.perRank = perRank;
		return this;
	}

	public Integer getTechRank() {
		return techRank;
	}

	public Stock setTechRank(Integer techRank) {
		this.techRank = techRank;
		return this;
	}

	public Integer getSafetyRank() {
		return safetyRank;
	}

	public Stock setSafetyRank(Integer safetyRank) {
		this.safetyRank = safetyRank;
		return this;
	}

	public BigDecimal getVlinePercentage() {
		return vlinePercentage;
	}

	public Stock setVlinePercentage(BigDecimal vlinePercentage) {
		this.vlinePercentage = vlinePercentage;
		return this;
	}

	public String getStkName() {
		return stkName;
	}

	public Stock  setStkName(String stkName) {
		this.stkName = stkName;
		return this;
	}
/*I  don't know what the fucntion is for*/
	public Stock setStkName(String stkname, Function<String,String> function){
		this.stkName = function.apply(stkname);return this;
	}
	
	public String getVl_DateToken_stkName() {
		return vl_DateToken_stkName;
	}

	public Stock setVl_DateToken_stkName(String vl_DateToken_stkName) {
		this.vl_DateToken_stkName = vl_DateToken_stkName;
		return this;
	}

	public String getTicker() {
		return ticker;
	}

	public Stock setTicker(String stkTicker) {
		this.ticker = stkTicker;
		return this;

	}

	public BigDecimal getVlinePrice() {
		return vlinePrice;
	}

	public Stock setVlinePrice(BigDecimal vlinePrice) {
		this.vlinePrice = vlinePrice;
		return this;

	}

	public ValueLine getValueLine() {
		return valueLine;
	}

	public Stock setValueLine(ValueLine valueLine) {
		this.valueLine = valueLine;
		return this;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
