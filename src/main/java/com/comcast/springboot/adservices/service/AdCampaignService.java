package com.comcast.springboot.adservices.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.comcast.springboot.adservices.model.AdCampaign;

@Component
public class AdCampaignService {

	private static List<AdCampaign> adCampaigns = new ArrayList<>();
	public static String status = "ACTIVE";

	public List<AdCampaign> retrieveAllAdCampaign() {
		return adCampaigns;
	}

	

	public List<AdCampaign> searchAdcampaign(AdCampaign adCampaign) {
		List<AdCampaign> result = null;

		if (!adCampaign.getAdContent().trim().equals(""))
			result = adCampaigns.stream().filter(x -> adCampaign.getAdContent().equals(x.getAdContent()))
					.collect(Collectors.toList());
		else if (adCampaign.getDuration() != 0 && !adCampaign.getAdTitle().trim().equals(""))
			result = adCampaigns.stream().filter(
					x -> adCampaign.getAdTitle().equals(x.getAdTitle()) && adCampaign.getDuration() == x.getDuration())
					.collect(Collectors.toList());
		else if (!adCampaign.getAdTitle().trim().equals(""))
			result = adCampaigns.stream().filter(x -> adCampaign.getAdTitle().equals(x.getAdTitle()))
					.collect(Collectors.toList());
		else if (adCampaign.getDuration() != 0)
			result = adCampaigns.stream().filter(x -> adCampaign.getDuration() == x.getDuration())
					.collect(Collectors.toList());

		return result;
	}

	public AdCampaign retrieveAdCampaign(String partnerId) {
		for (AdCampaign adCampaign : adCampaigns) {
			if (adCampaign.getPartnerId().equals(partnerId) && adCampaign.getAdStatus().equals(status)) {

				long output = (new Date().getTime() - adCampaign.getTimestamp()) / (60 * 1000) % 60;

				if (output <= adCampaign.getDuration())
					return adCampaign;
				else
					return null;
			}
		}
		return null;
	}

	public AdCampaign addAdCampaign(AdCampaign adCampaign) {

		AdCampaign checkAdCampaign = adCampaigns.stream()
				.filter(x -> adCampaign.getPartnerId().equals(x.getPartnerId())).findAny().orElse(null);

		if (checkAdCampaign == null) {
			adCampaigns.add(adCampaign);
			return adCampaign;
		} else
			return null;
	}

	public AdCampaign updateAdcampaign(AdCampaign adCampaign) {
		AdCampaign updateCampaign = null;
		for (int i = 0; i < adCampaigns.size(); i++) {
			if (adCampaigns.get(i).getPartnerId().equals(adCampaign.getPartnerId())) {
				adCampaigns.set(i, adCampaign);
				updateCampaign = adCampaign;
			}
		}

		return updateCampaign;
	}
}