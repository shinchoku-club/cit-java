package com.ecb.game.cit.code;

import com.ecb.game.cit.util.StringUtil;


public enum KindEnum implements NameEnum {
	CHECK(0, "調査"),
	ACTION(1, "行動"),
	ITEM(2, "アイテム");

	private int id;
	private String name;

	private KindEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	public static KindEnum getById(int id) {
		for (KindEnum kind : KindEnum.values()) {
			if (kind.getId() == id) {
				return kind;
			}
		}
		return null;
	}

	public static String getNameById(int id) {
		for (KindEnum kind : KindEnum.values()) {
			if (kind.getId() == id) {
				return kind.getName();
			}
		}
		return StringUtil.EMPTY;
	}
}
