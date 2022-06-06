package com.angus.omdc.message;


import com.angus.omdc.annotation.DynamicLengthMessage;

import java.util.List;

/**
 * Liquidity Provider (13)
 * 
 * The Liquidity Provider message is generated at the start of the business day for securities that have at least one liquidity
 * provider.
 * 
 * @author wangjia
 *
 */
@DynamicLengthMessage("10 + 2nT (nT = value of NoLiquidityProviders)")
public class LiquidityProviderMessage extends OmdMessage {
	int securityCode; // uint32, 1-99999
	int noLiquidityProviders; // uint16, 1-50, Number of liquidity providers within this message.
	List<Integer> lpBrokerNumber; // Broker number of the liquidity provider
	
	public int getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}
	public int getNoLiquidityProviders() {
		return noLiquidityProviders;
	}
	public void setNoLiquidityProviders(int noLiquidityProviders) {
		this.noLiquidityProviders = noLiquidityProviders;
	}
	public List<Integer> getLpBrokerNumber() {
		return lpBrokerNumber;
	}
	public void setLpBrokerNumber(List<Integer> lpBrokerNumber) {
		this.lpBrokerNumber = lpBrokerNumber;
	}
}
