package com.ecb.game.cit.code;

public enum PlayerEnum {
	PROFESSOR(1, "教授", 0, 7, "読解力", "アイテム【書籍】の効果がすべて倍になる", "真実の伝達", "決戦フェーズの際、他一名のアイテムカード【書籍】の効果がすべて倍になる"),
	DILETTANTE(2, "ディレッタント", 0, 0, "苦労知らず", "イベントカードの狂気増減が倍になる", "経済力", "自分の手番の直前、手札を3枚にしたのち、2枚手札に加え1枚戻す"),
	ENGINEER(3, "エンジニア", 5, 3, "分解フェチ", "自分にアイテムカードがセットされている場合、手札に「アイテム破壊」がある時は必ず使用し自分にセットされているアイテムカードを破壊しなければならない", "修理力", "アイテムカードが捨て札になる際に、そのアイテムカードを手札に加えることができる"),
	JOURNALIST(4, "ジャーナリスト", 0, 6, "暴露壁", "いずれかのプレイヤーにクローズドセットされたアイテムカードがある場合、手札に「アイテム公開」がある時は必ず使用しなければならない", "真実の伝達", "調査カード「狂気度調査」を使用する際、他のプレイヤー1名に使用する調査カードを見せることができる"),
	CHURCHMAN(5, "聖職者", 0, 9, "神とのつながり", "自分にセットされた「エルダーサイン」の効果が倍になる", "神の御加護", "決戦フェーズの際、人類陣営の任意のプレイヤーの引いた運命数を+1ないし-1できる"),
	PSYCHIATRIST(6, "精神科医", 0, 2, "守秘義務", "アイテムカードをオープンセットできない", "精神分析", "自分の手番直前、任意のプレイヤーにセットされた狂気増減効果のあるイベントカードを除去できる"),
	DETECTIVE(7, "探偵", 10, 5, "調査マニア", "使用可能な調査カードが手札に2枚以上ある場合、必ず使用しなければならない", "調査力", "自分の手番直前、任意のプレイヤー1人にクローズセットされたアイテムカードをすべて確認できる"),
	POLICEMAN(8, "刑事", 15, 4, "拘束マニア", "アイテムカード「拘束具」が手札にある場合、必ず使用しなければならない", "取り締まり", "他プレイヤーが行動カードを使用した際、無効にすることができる"),
	ARMY(9, "軍人", 20, 8, "考えるより行動", "手札に使用可能な行動カードが2枚以上ある場合、必ず使わなければならない", "火器使用術", "決戦フェーズの際アイテムカード【火器】の効果が2倍になる。");

	private int id;
	private String name;
	private int battle;
	private int san;
	private String chara;
	private String charaDetail;
	private String skill;
	private String skillDetail;

	private PlayerEnum(int id, String name, int battle, int san, String chara, String charaDetail, String skill, String skillDetail) {
		this.id = id;
		this.name = name;
		this.battle = battle;
		this.san = san;
		this.chara = chara;
		this.charaDetail = charaDetail;
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


	public int getSan() {
		return san;
	}


	public String getChara() {
		return chara;
	}


	public String getCharaDetail() {
		return charaDetail;
	}


	public String getSkill() {
		return skill;
	}


	public String getSkillDetail() {
		return skillDetail;
	}


	// IDから取得
	public static PlayerEnum getById(int id) {
		for (PlayerEnum player : PlayerEnum.values()) {
			if (player.getId() == id) {
				return player;
			}
		}
		return null;
	}
}
