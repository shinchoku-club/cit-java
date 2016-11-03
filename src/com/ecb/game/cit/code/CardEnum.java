package com.ecb.game.cit.code;

public enum CardEnum {
	CARD00(0, 1, 0, 0, 30),
	CARD01(1, 1, 0, 0, 40),
	CARD02(2, 2, 0, 0, 50),
	CARD03(3, 2, 0, 0, 60),
	CARD04(4, 3, 0, 1, 40),
	CARD05(5, 3, 0, 0, 70),
	CARD06(6, 4, 0, 1, 50),
	CARD07(7, 4, 0, 1, 60),
	CARD08(8, 5, 0, 1, 70),
	CARD09(9, 5, 0, 1, 80),
	CARD10(10, 6, 0, 1, 90),
	CARD11(11, 6, 0, 1, 100),
	CARD12(12, 7, 0, 0, 40),
	CARD13(13, 7, 0, 0, 50),
	CARD14(14, 8, 0, 0, 30),
	CARD15(15, 8, 0, 0, 60),
	CARD16(16, 9, 0, 1, 70),
	CARD17(17, 9, 0, 1, 100),
	CARD18(18, 10, 0, 1, 80),
	CARD19(19, 10, 0, 2, 0),
	CARD20(20, 11, 0, 2, 0),
	CARD21(21, 11, 0, 2, 0),
	CARD22(22, 12, 0, 0, 30),
	CARD23(23, 12, 0, 0, 40),
	CARD24(24, 13, 0, 1, 80),
	CARD25(25, 13, 0, 1, 90),
	CARD26(26, 14, 1, 3, 0),
	CARD27(27, 14, 1, 3, 0),
	CARD28(28, 15, 1, 3, 0),
	CARD29(29, 15, 1, 3, 0),
	CARD30(30, 16, 1, 4, 0),
	CARD31(31, 16, 1, 4, 0),
	CARD32(32, 17, 1, 4, 0),
	CARD33(33, 17, 1, 4, 0),
	CARD34(34, 18, 1, 5, 0),
	CARD35(35, 18, 1, 5, 0),
	CARD36(36, 19, 1, 5, 0),
	CARD37(37, 19, 1, 5, 0),
	CARD38(38, 20, 1, 6, 0),
	CARD39(39, 20, 1, 6, 0),
	CARD40(40, 21, 1, 6, 0),
	CARD41(41, 21, 1, 6, 0),
	CARD42(42, 22, 2, 7, -1),
	CARD43(43, 22, 2, 7, -1),
	CARD44(44, 23, 2, 8, -1),
	CARD45(45, 23, 2, 8, -1),
	CARD46(46, 24, 2, 9, -2),
	CARD47(47, 24, 2, 10, -3),
	CARD48(48, 25, 2, 11, -3),
	CARD49(49, 25, 2, 12, -3),
	CARD50(50, 26, 2, 13, -4),
	CARD51(51, 26, 2, 13, -4),
	CARD52(52, 27, 2, 13, -4),
	CARD53(53, 27, 2, 13, -4),
	CARD54(54, 28, 2, 14, -5),
	CARD55(55, 28, 2, 15, -5),
	CARD56(56, 29, 2, 16, -6),
	CARD57(57, 29, 2, 17, -5),
	CARD58(58, 30, 2, 18, -7),
	CARD59(59, 30, 2, 16, -6),
	CARD60(60, 31, 2, 19, -8),
	CARD61(61, 31, 2, 18, -7),
	CARD62(62, 32, 2, 20, -8),
	CARD63(63, 32, 2, 21, -9);


	private int id;
	private int fate;
	private int kind;
	private int type;
	private int value;

	private CardEnum(int id, int fate, int kind, int type, int value) {
		this.id = id;
		this.fate = fate;
		this.kind = kind;
		this.type = type;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public int getFate() {
		return fate;
	}

	public int getKind() {
		return kind;
	}

	public int getType() {
		return type;
	}

	public int getValue() {
		return value;
	}

	// IDから取得
	public static CardEnum getById(int id) {
		for (CardEnum card : CardEnum.values()) {
			if (card.getId() == id) {
				return card;
			}
		}
		return null;
	}
}
