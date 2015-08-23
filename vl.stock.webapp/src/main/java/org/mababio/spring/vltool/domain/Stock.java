
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
	private String stkName;
	private String Ticker;
	private BigDecimal vlinePrice;
	
	private BigDecimal vlinePercentage;
	
	public BigDecimal getVlinePercentage() {
		return vlinePercentage;
	}

	public void setVlinePercentage(BigDecimal vlinePercentage) {
		this.vlinePercentage = vlinePercentage;
	}

	private ValueLine valueLine;
	

		
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





	
	
}
