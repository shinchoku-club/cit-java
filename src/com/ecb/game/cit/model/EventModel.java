package com.ecb.game.cit.model;

import com.ecb.game.cit.util.StringUtil;

public class EventModel {
	private static final int UNKNOWN_ID = -2;
	private static final String UNKNOWN_NAME = "?";
	private static final int UNKNOWN_SAN = 0;
	private static final String UNKNOWN_DETAIL = "";

	private int id;
	private String name;
	private int san;
	private String detail;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSan() {
		return san;
	}
	public void setSan(int san) {
		this.san = san;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getKind() {
		return -1;
	}

	public String getKindName() {
		return StringUtil.EMPTY;
	}

	public int getType() {
		return -1;
	}

	public String getTypeName() {
		return this.getName();
	}

	public static EventModel getUnknownEvent() {
		EventModel unknownEvent = new EventModel();
		unknownEvent.setId(UNKNOWN_ID);
		unknownEvent.setName(UNKNOWN_NAME);
		unknownEvent.setSan(UNKNOWN_SAN);
		unknownEvent.setDetail(UNKNOWN_DETAIL);
		return unknownEvent;
	}
}
