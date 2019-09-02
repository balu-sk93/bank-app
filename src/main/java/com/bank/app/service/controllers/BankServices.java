package com.bank.app.service.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.app.data.dto.BankView;
import com.bank.app.data.dto.Branches;
import com.bank.app.data.repo.BankViewRepo;
import com.bank.app.data.repo.BranchRepo;
import com.bank.app.service.model.Request;
import com.bank.app.service.model.Response;
import com.bank.app.service.util.PaginationUtil;
import com.bank.app.utils.ValidationGroups.GetBankDetails;
import com.bank.app.utils.ValidationGroups.GetBankFromIfsc;

@RestController
@RequestMapping("/api")
public class BankServices {

	private static final Logger logger = LoggerFactory.getLogger(BankServices.class);

	@Autowired
	BranchRepo branchRepo;

	@Autowired
	BankViewRepo banksRepo;

	@PostMapping("/bank/ifsc")
	public ResponseEntity<Response> getBankDetailsFromIfsc(
			@Validated(GetBankFromIfsc.class) @RequestBody Request request, Pageable pageable) {

		logger.info("IFSC CODE : " + request.getIfscCode());

		Page<Branches> bankDetails = branchRepo.findByIfsc(request.getIfscCode(), pageable);
		HttpHeaders headers = null;
		Response response = new Response();
		if (bankDetails.hasContent() && bankDetails.getContent().size() > 0) {
			response.setBankId(bankDetails.getContent().get(0).getBankDetails().getId().toString());
			response.setBankName(bankDetails.getContent().get(0).getBankDetails().getName());
			response.setBranch(bankDetails.getContent().get(0).getBranch());
			response.setIfsc(bankDetails.getContent().get(0).getIfsc());
			response.setAddress(bankDetails.getContent().get(0).getAddress());
			response.setCity(bankDetails.getContent().get(0).getCity());
			response.setDistrict(bankDetails.getContent().get(0).getDistrict());
			response.setState(bankDetails.getContent().get(0).getState());
			response.setStatus("Success");
			headers = PaginationUtil.generatePaginationHttpHeaders(bankDetails, "/bank/ifsc");
		} else {
			response.setStatus("Error");
			response.setErrorMessage("Bank Details Not Found");
		}

		return new ResponseEntity<>(response, headers, HttpStatus.OK);
	}

	@PostMapping("/banks")
	public ResponseEntity<Response> getBankDetails(@Validated(GetBankDetails.class) @RequestBody Request request,
			Pageable pageable) {

		logger.info("Location: " + request.getBankName() + "," + request.getCity());

		Page<BankView> bankDetails = banksRepo.findAllByBankNameAndCity(request.getBankName(), request.getCity(), pageable);

		Response response = new Response();
		HttpHeaders headers = null;
		if (!bankDetails.isEmpty() || bankDetails.hasContent()) {
			response.setBankDetails(bankDetails.getContent());
			response.setStatus("Success");
			 headers = PaginationUtil.generatePaginationHttpHeaders(bankDetails, "/api/banks");
		} else {
			response.setStatus("Error");
			response.setErrorMessage("Bank Details Not Found");
		}

		return new ResponseEntity<>(response,headers, HttpStatus.OK);
	}

}
