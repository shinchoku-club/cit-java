package com.ecb.game.cit.util;

import com.ecb.game.cit.code.PlayerEnum;
import com.ecb.game.cit.code.TypeEnum;

public class GameConstant {
	public static final String SEPARATOR = "<>";

	public static final String ACTION_HEADER = "a";
	public static final String BATTLE_HEADER = "b";
	public static final String CALCULATE_HEADER = "c";
	public static final String DICE_HEADER = "d";
	public static final String ERROR_HEADER = "e";
	public static final String BELL_HEADER = "f";
	public static final String JOIN_HEADER = "j";
	public static final String LIST_HEADER = "l";
	public static final String MES_HEADER = "m";
	public static final String NAME_HEADER = "n";
	public static final String NAME_OK_HEADER = "o";
	public static final String PRIVATE_MES_HEADER = "p";
	public static final String RESET_HEADER = "q";
	public static final String REFRESH_HEADER = "r";
	public static final String START_HEADER = "s";
	public static final String EXIT_HEADER = "x";
	public static final String END_HEADER = "z";

	public static final String NAME_ANONYMOUS = "匿名";

	public static final int READY_PHASE = 0;
	public static final int TOP_PHASE = 1;
	public static final int MIDDLE_PHASE = 2;
	public static final int BOTTOM_PHASE = 3;
	public static final int LAST_PHASE = 4;
	public static final int ADVENT_PHASE = 5;
	public static final int BATTLE_PHASE = 6;
	public static final int AFTER_PHASE = 7;

	public static final int READY_SUB_PHASE = 0;
	public static final int DRAW_SUB_PHASE = 1;
	public static final int DILETTANTE_DROP_SUB_PHASE = 2;
	public static final int DILETTANTE_CHOSE_SUB_PHASE = 3;
	public static final int CHOOSE_SUB_PHASE = 4;
	public static final int USE_SUB_PHASE = 5;
	public static final int REPAIR_SUB_PHASE = 6;
	public static final int REGULATE_SUB_PHASE = 7;
	public static final int NYARLATHOTEP_SUB_PHASE = 8;
	public static final int BATTLE_FATE_SUB_PHASE = 9;
	public static final int PROFESSOR_SKILL_SUB_PHASE = 10;

	public static final String DRAW_ACTION = "draw";
	public static final String USE_PREDRAW_SKILL_ACTION = "predraw-skill";
	public static final String DILETTANTE_DROP_CHOOSE_ACTION = "dilettante-drop-choose";
	public static final String DILETTANTE_CHOOSE_ACTION = "dilettante-choose";
	public static final String CHOOSE_ACTION = "choose";
	public static final String USE_ACTION = "use";
	public static final String REPAIR_ACTION = "repair";
	public static final String REGULATE_ACTION = "regulate";
	public static final String NYARLATHOTEP_ACTION = "nyarlathoet-skill";
	public static final String DRAW_FATE_ACTION = "draw-fate";
	public static final String PROFESSOR_SKILL_ACTION = "professor-skill";

	public static final String CARD_MOVE_MY_OPEN = "0";
	public static final String CARD_MOVE_MY_CLOSED = "1";
	public static final String CARD_MOVE_ANOTHER_OPEN = "2";
	public static final String CARD_MOVE_ANOTHER_CLOSED = "3";

	public static final String COMMAND_NG = "0";
	public static final String COMMAND_OK = "1";

	public static final String BELL_SE = "bell";
	public static final String CHAT_SE = "chat";
	public static final String DICE_SE = "dice";
	public static final String JOIN_SE = "join";
	public static final String OPENING_SE = "opening";
	public static final String ENDING_SE = "ending";
	public static final String SCREAM_SE = "scream";

	public static final Integer[] BATTLE_SKILL_HOLDER_IDS = {
		new Integer(PlayerEnum.PROFESSOR.getId()),
		new Integer(PlayerEnum.CHURCHMAN.getId()),
		new Integer(PlayerEnum.ARMY.getId())};

	public static final Integer[] BATTON_AND_GUN_IDS = {
			new Integer(TypeEnum.BATON.getId()),
			new Integer(TypeEnum.GUN.getId()),
			new Integer(TypeEnum.MACHINE_GUN.getId())};
	public static final Integer[] BOOK_IDS = {
			new Integer(TypeEnum.AVON.getId()),
			new Integer(TypeEnum.NO_NAME.getId()),
			new Integer(TypeEnum.NECRONOMICON.getId())};
	public static final Integer[] ANOTHER_WEAPON_IDS = {
			new Integer(TypeEnum.BIND.getId()),
			new Integer(TypeEnum.AVON.getId()),
			new Integer(TypeEnum.NO_NAME.getId()),
			new Integer(TypeEnum.NECRONOMICON.getId()),
			new Integer(TypeEnum.GUARD.getId()),
			new Integer(TypeEnum.ARMOR.getId()),
			new Integer(TypeEnum.CRYSTAL.getId())};
	public static final Integer[] SAN_ITEM_IDS = {
			new Integer(TypeEnum.AVON.getId()),
			new Integer(TypeEnum.NO_NAME.getId()),
			new Integer(TypeEnum.KEY.getId()),
			new Integer(TypeEnum.NECRONOMICON.getId()),
			new Integer(TypeEnum.SIGN.getId()),
			new Integer(TypeEnum.CRYSTAL.getId())};

	public static final String ERROR_INPUT_NAME = "名前を入力してください";
	public static final String ERROR_PRIVATE_MESSAGE = "メッセージエラー";
	public static final String FORMAT_PRIVATE_MESSAGE = "<color:#FFD700>{0}:{1}</color>";
	public static final String FORMAT_JOIN_SUCCESS = "<color:#FFFFFF>{0}:席に着きました</color>";
	public static final String FORMAT_JOIN_FAILURE = "<color:#FFFFFF>{0}:満席でした</color>";
	public static final String FORMAT_EXIT_SUCCESS = "<color:#FFFFFF>{0}:離席しました</color>";
	public static final String FORMAT_EXIT_FAILURE = "<color:#FFFFFF>{0}:離席できませんでした</color>";
	public static final String MESSAGE_START_GAME = "ゲームを開始します";
	public static final String FORMAT_TIME_PROGRESS = "<color:#FF0000>時間進行：{0}イベントが発生しました</color>";
	public static final String FORMAT_DICE = "ダイスロール[<color:#FF6666>{0}</color>]";
	public static final String MESSAGE_RESET_GAME = "リセットしました";

	public static final String META_MESSAGE_HEADER = "/";
	public static final String KEY_GRANT = "grant";
	public static final String KEY_REVOKE = "revoke";
	public static final String KEY_RESET = "reset";
	public static final String FORMAT_SE = "/se{0}/";
	public static final String MESSAGE_GRANT = "管理者になりました。";
	public static final String MESSAGE_REVOKE = "管理者を辞退しました";
	public static final String MARK_GRANT = "<color:#FFFF00>★</color>";

	public static final String PASSWORD = "cit";
}
