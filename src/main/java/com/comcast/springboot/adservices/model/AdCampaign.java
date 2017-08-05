package com.comcast.springboot.adservices.model;

import java.util.Date;

public class AdCampaign {

	private String partnerId;
	private long duration;
	private String adContent;
	private String adTitle;
	private String adStatus;
	private long timestamp;

	public AdCampaign() {
	}

	public AdCampaign(String partnerId, long duration, String adContent, String adTitle, String adStatus) {
		super();
		this.partnerId = partnerId;
		this.duration = duration;
		this.adContent = adContent;
		this.adTitle = adTitle;
		this.adStatus = adStatus;
		this.timestamp =  new Date().getTime();
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getAdContent() {
		return adContent;
	}

	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}

	public String getAdTitle() {
		return adTitle;
	}

	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}

	public String getAdStatus() {
		return adStatus;
	}

	public void setAdStatus(String adStatus) {
		this.adStatus = adStatus;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
}