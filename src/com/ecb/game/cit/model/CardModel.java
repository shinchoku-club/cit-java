package com.ecb.game.cit.model;

import com.ecb.game.cit.code.KindEnum;
import com.ecb.game.cit.code.TypeEnum;

public class CardModel {
	public static final int TIME_CARD = -1;
	public static final int TIME_FATE = 0;
	public static final int TIME_KIND = -1;
	public static final int TIME_TYPE = -1;
	public static final int TIME_VALUE = 0;
	public static final String TIME_DETAIL = "";
	public static final String TIME_NAME = "時間進行カード";

	public static final int UNKNOWN_CARD = -2;
	public static final int UNKNOWN_FATE = 0;
	public static final int UNKNOWN_KIND = -1;
	public static final int UNKNOWN_TYPE = -1;
	public static final int UNKNOWN_VALUE = 0;
	public static final String UNKNOWN_DETAIL = "裏返しに伏せられたカード";
	public static final String UNKNOWN_NAME = "?";

	private int id;
	private int fate;
	private int kind;
	private int type;
	private int value;
	private String detail;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFate() {
		return fate;
	}
	public void setFate(int fate) {
		this.fate = fate;
	}
	public int getKind() {
		return kind;
	}
	public void setKind(int kind) {
		this.kind = kind;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}

	public static CardModel getTimeCard() {
		CardModel timeCard = new CardModel();
		timeCard.setId(TIME_CARD);
		timeCard.setFate(TIME_FATE);
		timeCard.setKind(TIME_KIND);
		timeCard.setType(TIME_TYPE);
		timeCard.setValue(TIME_VALUE);
		timeCard.setDetail(TIME_DETAIL);

		return timeCard;
	}

	public boolean isTimeCard() {
		return this.getId() == TIME_CARD;
	}

	public String getTypeName() {
		if (this.getId() == TIME_CARD) {
			return TIME_NAME;
		} else if (this.getId() == UNKNOWN_CARD) {
			return UNKNOWN_NAME;
		}
		return TypeEnum.getNameById(this.getType());
	}

	public String getKindName() {
		if (this.getId() == TIME_CARD) {
			return TIME_NAME;
		}
		return KindEnum.getNameById(this.getKind());
	}

	public static CardModel getUnknownCard() {
		CardModel unknownCard = new CardModel();
		unknownCard.setId(UNKNOWN_CARD);
		unknownCard.setFate(UNKNOWN_FATE);
		unknownCard.setKind(UNKNOWN_KIND);
		unknownCard.setType(UNKNOWN_TYPE);
		unknownCard.setValue(UNKNOWN_VALUE);
		unknownCard.setDetail(UNKNOWN_DETAIL);

		return unknownCard;
	}
}
