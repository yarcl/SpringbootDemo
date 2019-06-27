package com.yarcl.springquart.bean;

import lombok.Data;

import java.util.Date;

/**
 * 3.2.6自定义事件表razor_event_defination
 * 
 * @author Anthonyxw
 * 
 */
@Data
public class EventDefination {

	private String eventId;
	private String userId;
	private String productId;
	private String channelId;
	private String eventName;
	private String eventIdentifier; // 事件定义
	private String productKey;// 应用KEY
	private int active; // 是否激活 1是
	private Date created; // 创建时间

	public EventDefination() {
		super();
	}

	public EventDefination(String eventId) {
		super();
		this.eventId = eventId;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + active;
		result = prime * result
				+ ((channelId == null) ? 0 : channelId.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((eventId == null) ? 0 : eventId.hashCode());
		result = prime * result
				+ ((eventIdentifier == null) ? 0 : eventIdentifier.hashCode());
		result = prime * result
				+ ((eventName == null) ? 0 : eventName.hashCode());
		result = prime * result
				+ ((productId == null) ? 0 : productId.hashCode());
		result = prime * result
				+ ((productKey == null) ? 0 : productKey.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventDefination other = (EventDefination) obj;
		if (active != other.active)
			return false;
		if (channelId == null) {
			if (other.channelId != null)
				return false;
		} else if (!channelId.equals(other.channelId))
			return false;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (eventId == null) {
			if (other.eventId != null)
				return false;
		} else if (!eventId.equals(other.eventId))
			return false;
		if (eventIdentifier == null) {
			if (other.eventIdentifier != null)
				return false;
		} else if (!eventIdentifier.equals(other.eventIdentifier))
			return false;
		if (eventName == null) {
			if (other.eventName != null)
				return false;
		} else if (!eventName.equals(other.eventName))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (productKey == null) {
			if (other.productKey != null)
				return false;
		} else if (!productKey.equals(other.productKey))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	
	
}
