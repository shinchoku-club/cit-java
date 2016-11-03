<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ecb.game.cit.util.GameConstant" %>
<%@ page import="com.ecb.game.cit.code.PlayerEnum" %>
<%@ page import="com.ecb.game.cit.code.TypeEnum" %>
<%@ page import="com.ecb.game.cit.code.KindEnum" %>
<!DOCTYPE html>
<html>

<head>
<link href="./css/layoutSp.css?${form.now}" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="./js/jquery.js"></script>
<script src="http://cdn.rawgit.com/phi-jp/phina.js/v0.2.0/build/phina.js"></script>
<script type="text/javascript">
	phina.globalize();

	Cthulhu = function(){};
	Cthulhu.ACTION_HEADER = "${GameConstant.ACTION_HEADER}";
	Cthulhu.BATTLE_HEADER = "${GameConstant.BATTLE_HEADER}";
	Cthulhu.CALCULATE_HEADER = "${GameConstant.CALCULATE_HEADER}";
	Cthulhu.DICE_HEADER = "${GameConstant.DICE_HEADER}";
	Cthulhu.ERROR_HEADER = "${GameConstant.ERROR_HEADER}";
	Cthulhu.BELL_HEADER = "${GameConstant.BELL_HEADER}";
	Cthulhu.JOIN_HEADER = "${GameConstant.JOIN_HEADER}";
	Cthulhu.LIST_HEADER = "${GameConstant.LIST_HEADER}";
	Cthulhu.MES_HEADER = "${GameConstant.MES_HEADER}";
	Cthulhu.NAME_HEADER = "${GameConstant.NAME_HEADER}";
	Cthulhu.NAME_OK_HEADER = "${GameConstant.NAME_OK_HEADER}";
	Cthulhu.PRIVATE_MES_HEADER = "${GameConstant.PRIVATE_MES_HEADER}";
	Cthulhu.RESET_HEADER = "${GameConstant.RESET_HEADER}";
	Cthulhu.REFRESH_HEADER = "${GameConstant.REFRESH_HEADER}";
	Cthulhu.START_HEADER = "${GameConstant.START_HEADER}";
	Cthulhu.EXIT_HEADER = "${GameConstant.EXIT_HEADER}";
	Cthulhu.END_HEADER = "${GameConstant.END_HEADER}";

	Cthulhu.DRAW_ACTION = "${GameConstant.DRAW_ACTION}";
	Cthulhu.USE_PREDRAW_SKILL_ACTION = "${GameConstant.USE_PREDRAW_SKILL_ACTION}";
	Cthulhu.DILETTANTE_DROP_CHOOSE_ACTION = "${GameConstant.DILETTANTE_DROP_CHOOSE_ACTION}";
	Cthulhu.DILETTANTE_CHOOSE_ACTION = "${GameConstant.DILETTANTE_CHOOSE_ACTION}";
	Cthulhu.CHOOSE_ACTION = "${GameConstant.CHOOSE_ACTION}";
	Cthulhu.USE_ACTION = "${GameConstant.USE_ACTION}";
	Cthulhu.REPAIR_ACTION = "${GameConstant.REPAIR_ACTION}";
	Cthulhu.REGULATE_ACTION = "${GameConstant.REGULATE_ACTION}";

	Cthulhu.NYARLATHOTEP_ACTION = "${GameConstant.NYARLATHOTEP_ACTION}";
	Cthulhu.DRAW_FATE_ACTION = "${GameConstant.DRAW_FATE_ACTION}";
	Cthulhu.PROFESSOR_SKILL_ACTION = "${GameConstant.PROFESSOR_SKILL_ACTION}";

	Cthulhu.CARD_MOVE_MY_OPEN = "${GameConstant.CARD_MOVE_MY_OPEN}";
	Cthulhu.CARD_MOVE_MY_CLOSED = "${GameConstant.CARD_MOVE_MY_CLOSED}";
	Cthulhu.CARD_MOVE_ANOTHER_OPEN = "${GameConstant.CARD_MOVE_ANOTHER_OPEN}";
	Cthulhu.CARD_MOVE_ANOTHER_CLOSED = "${GameConstant.CARD_MOVE_ANOTHER_CLOSED}";

	Cthulhu.READY_PHASE = "${GameConstant.READY_PHASE}";
	Cthulhu.TOP_PHASE = "${GameConstant.TOP_PHASE}";
	Cthulhu.MIDDLE_PHASE = "${GameConstant.MIDDLE_PHASE}";
	Cthulhu.BOTTOM_PHASE = "${GameConstant.BOTTOM_PHASE}";
	Cthulhu.LAST_PHASE = "${GameConstant.LAST_PHASE}";
	Cthulhu.ADVENT_PHASE = "${GameConstant.ADVENT_PHASE}";
	Cthulhu.BATTLE_PHASE = "${GameConstant.BATTLE_PHASE}";
	Cthulhu.AFTER_PHASE = "${GameConstant.AFTER_PHASE}";

	Cthulhu.READY_SUB_PHASE = "${GameConstant.READY_SUB_PHASE}";
	Cthulhu.DRAW_SUB_PHASE = "${GameConstant.DRAW_SUB_PHASE}";
	Cthulhu.DILETTANTE_DROP_SUB_PHASE = "${GameConstant.DILETTANTE_DROP_SUB_PHASE}";
	Cthulhu.DILETTANTE_CHOSE_SUB_PHASE = "${GameConstant.DILETTANTE_CHOSE_SUB_PHASE}";
	Cthulhu.CHOOSE_SUB_PHASE = "${GameConstant.CHOOSE_SUB_PHASE}";
	Cthulhu.USE_SUB_PHASE = "${GameConstant.USE_SUB_PHASE}";
	Cthulhu.REPAIR_SUB_PHASE = "${GameConstant.REPAIR_SUB_PHASE}";
	Cthulhu.REGULATE_SUB_PHASE = "${GameConstant.REGULATE_SUB_PHASE}";
	Cthulhu.NYARLATHOTEP_SUB_PHASE = "${GameConstant.NYARLATHOTEP_SUB_PHASE}";
	Cthulhu.BATTLE_FATE_SUB_PHASE = "${GameConstant.BATTLE_FATE_SUB_PHASE}";
	Cthulhu.PROFESSOR_SKILL_SUB_PHASE = "${GameConstant.PROFESSOR_SKILL_SUB_PHASE}";

	Cthulhu.PROFESSOR = ${PlayerEnum.PROFESSOR.id};
	Cthulhu.DILETTANTE = ${PlayerEnum.DILETTANTE.id};
	Cthulhu.ENGINEER = ${PlayerEnum.ENGINEER.id};
	Cthulhu.JOURNALIST = ${PlayerEnum.JOURNALIST.id};
	Cthulhu.CHURCHMAN = ${PlayerEnum.CHURCHMAN.id};
	Cthulhu.PSYCHIATRIST = ${PlayerEnum.PSYCHIATRIST.id};
	Cthulhu.DETECTIVE = ${PlayerEnum.DETECTIVE.id};
	Cthulhu.POLICEMAN = ${PlayerEnum.POLICEMAN.id};
	Cthulhu.ARMY = ${PlayerEnum.ARMY.id};

	Cthulhu.CHECK_DOWN_TYPE = ${TypeEnum.CHECK_DOWN.id};
	Cthulhu.CHECK_UP_TYPE = ${TypeEnum.CHECK_UP.id};
	Cthulhu.ITEM_CHECK_TYPE = ${TypeEnum.ITEM_CHECK.id};
	Cthulhu.ITEM_OPEN_TYPE = ${TypeEnum.ITEM_OPEN.id};
	Cthulhu.ITEM_MOVE_TYPE = ${TypeEnum.ITEM_MOVE.id};
	Cthulhu.ITEM_REMOVE_TYPE = ${TypeEnum.ITEM_REMOVE.id};
	Cthulhu.HONEY_TYPE = ${TypeEnum.HONEY.id};
	Cthulhu.DRUG_TYPE = ${TypeEnum.DRUG.id};
	Cthulhu.HI_DRUG_TYPE = ${TypeEnum.HI_DRUG.id};
	Cthulhu.BATON_TYPE = ${TypeEnum.BATON.id};
	Cthulhu.GUN_TYPE = ${TypeEnum.GUN.id};
	Cthulhu.FIRE_TYPE = ${TypeEnum.FIRE.id};
	Cthulhu.MACHINE_GUN_TYPE = ${TypeEnum.MACHINE_GUN.id};
	Cthulhu.BIND_TYPE = ${TypeEnum.BIND.id};
	Cthulhu.AVON_TYPE = ${TypeEnum.AVON.id};
	Cthulhu.NO_NAME_TYPE = ${TypeEnum.NO_NAME.id};
	Cthulhu.KEY_TYPE = ${TypeEnum.KEY.id};
	Cthulhu.NECRONOMICON_TYPE = ${TypeEnum.NECRONOMICON.id};
	Cthulhu.SIGN_TYPE = ${TypeEnum.SIGN.id};
	Cthulhu.GUARD_TYPE = ${TypeEnum.GUARD.id};
	Cthulhu.ARMOR_TYPE = ${TypeEnum.ARMOR.id};
	Cthulhu.CRYSTAL_TYPE = ${TypeEnum.CRYSTAL.id};

	Cthulhu.CHECK_KIND = ${KindEnum.CHECK.id};
	Cthulhu.ACTION_KIND = ${KindEnum.ACTION.id};
	Cthulhu.ITEM_KIND = ${KindEnum.ITEM.id};

	Cthulhu.COMMAND_NG = "${GameConstant.COMMAND_NG}";
	Cthulhu.COMMAND_OK = "${GameConstant.COMMAND_OK}";

	Cthulhu.OPEN_MY_ITEMS = "${GameConstant.CARD_MOVE_MY_OPEN}";
	Cthulhu.CLOSED_MY_ITEMS = "${GameConstant.CARD_MOVE_MY_CLOSED}";
	Cthulhu.OPEN_ANOTHER_ITEMS = "${GameConstant.CARD_MOVE_ANOTHER_OPEN}";
	Cthulhu.CLOSED_ANOTHER_ITEMS = "${GameConstant.CARD_MOVE_ANOTHER_CLOSED}";

	Cthulhu.SEPARATOR = "${GameConstant.SEPARATOR}";

	var SOUNDS = {
		sound: {
			'${GameConstant.BELL_SE}': './se/bell.mp3',
			'${GameConstant.CHAT_SE}': './se/chat.mp3',
			'${GameConstant.DICE_SE}': './se/dice.mp3',
			'${GameConstant.JOIN_SE}': './se/join.mp3',
			'${GameConstant.OPENING_SE}': './se/opening.mp3',
			'${GameConstant.ENDING_SE}': './se/ending.mp3',
			'${GameConstant.SCREAM_SE}': './se/scream.mp3',
		},
	};
</script>
<script type="text/javascript" src="./js/phinaSp/ColorfulLabelArea.js"></script>
<script type="text/javascript" src="./js/phina/CthulhuCard.js"></script>
<script type="text/javascript" src="./js/phina/CthulhuEvent.js"></script>
<script type="text/javascript" src="./js/phina/CthulhuPlayer.js"></script>
<script type="text/javascript" src="./js/phinaSp/CthulhuPlayerInfo.js"></script>
<script type="text/javascript" src="./js/phinaSp/CthulhuGame.js"></script>
<script type="text/javascript">

	$(function(){
		var ws = new WebSocket("${form.websocketUrl}");

		// アプリケーション生成
		var app = WebSocketGameApp({
			startLabel: 'title', // メインシーンから開始する
			query: '#gameCanvas',
			assets: $.extend(true, {}, CARD_IMAGE, EVENT_IMAGE, PLAYER_IMAGE, GAME_IMAGE, SOUNDS),
			scenes: [
				{
					label: 'title',
					className: 'TitleScene',
					nextLabel: 'main',
				},
				{
					label: 'main',
					className: 'MainScene',
				},
				{
					label: 'alert',
					className: 'AlertScene',
					nextLabel: 'main',
				},
				{
					label: 'confirm',
					className: 'ConfirmScene',
					nextLabel: 'main',
				},
				{
					label: 'select',
					className: 'SelectScene',
					nextLabel: 'main',
				},
			],
			ws: ws,
			mesHandler: {
				[Cthulhu.MES_HEADER]: function(e){
					var r = new RegExp("^(.*)/se(.+?)/(.*)$");
					var mesSe = r.exec(e.body);
					if (mesSe) {
						e.body = mesSe[1] + mesSe[3];
						AssetManager.get("sound", mesSe[2]).play();
					} else {
						AssetManager.get("sound", "${GameConstant.CHAT_SE}").play();
					}
					this.logBody = e.body + "\n" + this.logBody;
				},
				[Cthulhu.REFRESH_HEADER]: function(e){
					var data = JSON.parse(e.body);
					this.currentScene.refreshView && this.currentScene.refreshView(data);
				},

				[Cthulhu.LIST_HEADER]: function(e){
					var nameList = e.body.split(Cthulhu.SEPARATOR);
					var loginList = $("<ul></ul>");
					for (var i = 0; i < nameList.length; i++) {
						if (nameList[i] != "") {
							loginList.append("<li>" + nameList[i] + "</li>");
						}
					}
					$(".loginUsers").html(loginList);
				},

				[Cthulhu.NAME_OK_HEADER]: function(e){
					if (this.currentScene) {
						$("#login").hide();
						$("#game").show();

						this.playerName = e.body;
						this.currentScene.exit('main');
						var that = this;
						var scrollFunc = function(count) {
							$(window)
							.scrollTop($(that.domElement).offset().top / 2)
							.scrollLeft($(that.domElement).offset().left / 2);
							if (count++ < 10) {
								setTimeout(function() { scrollFunc(count); }, 50);
							}
						};
						scrollFunc(0);
					}
				},

				[Cthulhu.BELL_HEADER]: function(){
					AssetManager.get("sound", "${GameConstant.BELL_SE}").play();
				},

				[Cthulhu.ERROR_HEADER]: function(e){
					alert(e.body);
				},

			},
		});

		ws.onopen = function(){
			// アプリケーション実行
			app.run();

			$("#send").show();
		};

		ws.onclose = function(){
		};

		ws.onerror = function(event){
			alert("エラー");
		};

		$("#nameForm").submit(function(){
			if (!$("#nameInput").val()) {
				return false;
			}
			ws.send(Cthulhu.NAME_HEADER + $("#nameInput").val() + Cthulhu.SEPARATOR + $("#passInput").val());
			return false;
		});
	});
</script>
</head>

<body>
	<div id="wrapper">
		<div id="game">
			<canvas id="gameCanvas"></canvas>
		</div>
		<div id="login">
			<h2>Cthulhu in there</h2>
			<form id="nameForm" action="#">
				名前：
				<input type="text" id="nameInput" /><br />
				パスワード：
				<input type="password" id="passInput" /><br />
				<input type="submit" id="send" value="送信" />
			</form>
			<div class="loginUsers"></div>
		</div>
	</div>
</body>

</html>