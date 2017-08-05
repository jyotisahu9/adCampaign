package com.comcast.springboot.adservices.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.comcast.springboot.adservices.model.AdCampaign;
import com.comcast.springboot.adservices.model.CustomErrorType;
import com.comcast.springboot.adservices.service.AdCampaignService;

@RestController
public class AdCampaignController {

	@Autowired
	private AdCampaignService adCampaignService;

	@GetMapping("/listAllAdcampaigns")
	public ResponseEntity<List<AdCampaign>> retrieveAllAdcampaign() {

		List<AdCampaign> adCampaigns = adCampaignService.retrieveAllAdCampaign();
		if (adCampaigns.isEmpty())
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<AdCampaign>>(adCampaigns, HttpStatus.OK);

	}

	@GetMapping("/fetchAdcampaign/{partnerId}")
	public ResponseEntity<?> retrieveAdcampaign(@PathVariable String partnerId) {
		AdCampaign adCampaign = adCampaignService.retrieveAdCampaign(partnerId);

		if (adCampaign == null)
			return new ResponseEntity(new CustomErrorType("Adcampign with partnerId " + partnerId + " not found"),
					HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<AdCampaign>(adCampaign, HttpStatus.OK);
	}

	@PostMapping("/addAdcampaign")
	public ResponseEntity<?> addCampaign(@RequestBody AdCampaign newAdCampaign) {

		AdCampaign adCampaign = adCampaignService.addAdCampaign(newAdCampaign);

		if (adCampaign == null)
			return new ResponseEntity(
					new CustomErrorType("Adcampign with partnerId " + newAdCampaign.getPartnerId() + " already exists"),
					HttpStatus.CONFLICT);
		else
			return new ResponseEntity<AdCampaign>(adCampaign, HttpStatus.CREATED);

	}

	@PostMapping("/updateAdcampaigns")
	public ResponseEntity<?> updateAdcampaign(@RequestBody AdCampaign updateAdCampaign) {
		AdCampaign adCampaign = adCampaignService.updateAdcampaign(updateAdCampaign);

		if (adCampaign == null)
			return new ResponseEntity(
					new CustomErrorType("Unable to find adcampaign with partnerId " + updateAdCampaign.getPartnerId()),
					HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<AdCampaign>(adCampaign, HttpStatus.OK);

	}

	@PostMapping("/searchAdcampaign")
	public ResponseEntity<List<AdCampaign>> searchAdcampaign(@RequestBody AdCampaign newAdCampaign) {

		List<AdCampaign> adCampaigns = adCampaignService.searchAdcampaign(newAdCampaign);
		if (adCampaigns.isEmpty())
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<AdCampaign>>(adCampaigns, HttpStatus.OK);
	}
}