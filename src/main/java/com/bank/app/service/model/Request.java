package com.bank.app.service.model;

import javax.validation.constraints.NotEmpty;

import com.bank.app.utils.ValidationGroups.GetBankDetails;
import com.bank.app.utils.ValidationGroups.GetBankFromIfsc;

public class Request {

	@NotEmpty(message = "Please provide Ifsc Code", groups = { GetBankFromIfsc.class })
	private String ifscCode;

	@NotEmpty(message = "Please provide Bank Name", groups = { GetBankDetails.class })
	private String bankName;

	@NotEmpty(message = "Please provide City", groups = { GetBankDetails.class })
	private String city;

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
