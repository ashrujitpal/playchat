package com.springboot.kafka.Consumer.models;

public class BigDataRequest {

	private String fromAccNo;
	private String toAccNo;
	private String amount;
	private String ifscCode;
	private String desc;
	private String refId;
	
	public BigDataRequest(){
		
	}
	
	public BigDataRequest(String fromAccNo, String toAccNo, String amount,
			String ifscCode, String desc, String refId) {		
		this.fromAccNo = fromAccNo;
		this.toAccNo = toAccNo;
		this.amount = amount;
		this.ifscCode = ifscCode;
		this.desc = desc;
		this.refId = refId;
	}

	public String getFromAccNo() {
		return fromAccNo;
	}
	public void setFromAccNo(String fromAccNo) {
		this.fromAccNo = fromAccNo;
	}
	public String getToAccNo() {
		return toAccNo;
	}
	public void setToAccNo(String toAccNo) {
		this.toAccNo = toAccNo;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	
	@Override
	public String toString() {
		return "BigDataRequest {fromAccNo=" + fromAccNo + ", toAccNo="
				+ toAccNo + ", amount=" + amount + ", ifscCode=" + ifscCode
				+ ", desc=" + desc + ", refId=" + refId + "}";
	}
	
}
