package com.ecb.game.cit.code;

public enum EventEnum {
	EVENT0(0, "狂人たちの宴", 10, "アイテムカードのように自分にオープンセットする。セットされたプレイヤーの狂気度+10。"),
	EVENT1(1, "超常現象目撃", 5, "アイテムカードのように自分にオープンセットする。セットされたプレイヤーの狂気度+5。"),
	EVENT2(2, "幸福な日常", -10, "アイテムカードのように自分にオープンセットする。セットされたプレイヤーの狂気度-10。"),
	EVENT3(3, "狂気の儀式", 15, "アイテムカードのように自分にオープンセットする。セットされたプレイヤーの狂気度+15。"),
	EVENT4(4, "明らかになる思惑", 0, "プレイヤーは自分にクローズドセットされているアイテムのうち1つをオープンにしなければならない。効果適用後このカードをイベントの山札に戻しシャッフルする。");

	private int id;
	private String name;
	private int san;
	private String detail;

	private EventEnum(int id, String name, int san, String detail) {
		this.id = id;
		this.name = name;
		this.san = san;
		this.detail = detail;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getSan() {
		return san;
	}

	public String getDetail() {
		return detail;
	}

	// IDから取得
	public static EventEnum getById(int id) {
		for (EventEnum event : EventEnum.values()) {
			if (event.getId() == id) {
				return event;
			}
		}
		return null;
	}
}
