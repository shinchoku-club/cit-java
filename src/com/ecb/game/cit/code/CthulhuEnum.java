package com.ecb.game.cit.code;

public enum CthulhuEnum {
	AZATHOTH(1, "アザトース", 30, "白痴全能", "自らが引いた運命数を+2～-2の範囲で加減算することができる"),
	NYARLATHOTEP(2, "ニャルラトホテプ", 10, "這いよる混沌", "決戦フェーズで使用されるプレイヤー1名のスキルを使用させない"),
	CTHULHU(3, "クトゥルフ", 0, "ルルイエの主", "自分にセットされたアイテムカード【書籍】の数×15戦闘力が上昇する"),
	SHUB_NIGGURATH(4, "黒い仔山羊", -10, "未知の物質", "アイテムカード【近接】【火器】による戦闘力上昇を無効にする"),
	DAGON(5, "ダゴン", 0, "深きものの王", "自分にセットされたアイテムカード【書籍】の数×10戦闘力が上昇する"),
	THE_HOUNDS_OF_TINDALOS(6, "ティンダロスの猟犬", -20, "時間の角に棲まうモノ", "アイテムカード【書籍】のセットされていないプレイヤーのアイテムカード【近接】【火器】による戦闘力上昇を無効にする"),
	SHOGGOTH(7, "ショゴス", -20, "完全生物", "「火炎放射器」を除くアイテムカード【近接】【火器】による戦闘力上昇を無効にする");

	private int id;
	private String name;
	private int battle;
	private String skill;
	private String skillDetail;

	private CthulhuEnum(int id, String name, int battle, String skill, String skillDetail) {
		this.id = id;
		this.name = name;
		this.battle = battle;
		this.skill = skill;
		this.skillDetail = skillDetail;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getBattle() {
		return battle;
	}

	public String getSkill() {
		return skill;
	}

	public String getSkillDetail() {
		return skillDetail;
	}

	// IDから取得
	public static CthulhuEnum getById(int id) {
		for (CthulhuEnum card : CthulhuEnum.values()) {
			if (card.getId() == id) {
				return card;
			}
		}
		return null;
	}
}
