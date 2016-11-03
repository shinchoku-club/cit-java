<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ecb.game.cit.util.GameConstant" %>
<%@ page import="com.ecb.game.cit.code.PlayerEnum" %>
<%@ page import="com.ecb.game.cit.code.TypeEnum" %>
<%@ page import="com.ecb.game.cit.code.KindEnum" %>
<!DOCTYPE html>
<html>

<head>
<link href="./css/layout.css?${form.now}" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/createjs-2015.11.26.min.js"></script>
<script type="text/javascript" src="./js/sound.js"></script>
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
</script>
<script type="text/javascript" src="./js/phina/CthulhuCard.js"></script>
<script type="text/javascript" src="./js/phina/CthulhuEvent.js"></script>
<script type="text/javascript" src="./js/phina/CthulhuPlayer.js"></script>
<script type="text/javascript" src="./js/phina/CthulhuPlayerInfo.js"></script>
<script type="text/javascript" src="./js/phina/CthulhuGame.js"></script>
<script type="text/javascript">

	$(function(){
		var ws = new WebSocket("${form.websocketUrl}");

		var app = null;

		ws.onopen = function(){
		};

		ws.onclose = function(){
		};

		ws.onmessage = function(message){
			var head = message.data.substr(0, 1);
			var body = message.data.substr(1);
			if (head == Cthulhu.MES_HEADER) {
				var r = new RegExp("^(.*)/se(.+?)/(.*)$");
				var mesSe = r.exec(body);
				if (mesSe) {
					body = mesSe[1] + mesSe[3];
					var sndMap = {
						'bell' : 0,
						'chat' : 1,
						'dice' : 2,
						'join' : 3,
						'opening' : 4,
						'ending' : 5,
						'scream' : 6,
					};
					SESound.sound(sndMap[mesSe[2]]);
				} else {
					SESound.sound(SESound.Type.CHAT);
				}
				body = body.replace(/^(.*)<color:(.*?)>(.*)<\/color>(.*)$/gi, "$1<span style='color:$2'>$3</span>$4");
				$("#log").prepend("<hr/>").prepend(body);
			} else if (head == Cthulhu.REFRESH_HEADER) {
				var data = JSON.parse(body);
				app.currentScene.refreshView && app.currentScene.refreshView(data);
			} else if (head == Cthulhu.LIST_HEADER) {
				body = body.replace(/^(.*)<color:(.*?)>(.*)<\/color>(.*)$/gi, "$1<span style='color:$2'>$3</span>$4");
				var nameList = body.split(Cthulhu.SEPARATOR);
				var loginList = $("<ul></ul>");
				var selectList = $("#mesTo");
				var selectedName = selectList.val();
				selectList.children("option").remove();
				selectList.append("<option value=''>全体</option>");
				for (var i = 0; i < nameList.length; i++) {
					if (nameList[i] != "") {
						loginList.append("<li>" + nameList[i] + "</li>");
						var selectName = nameList[i].replace(/<span.*?<\/span>/gi, '');
						selectList.append($("<option></option>").val(selectName).text(selectName));
					}
				}
				$(".loginUsers").html(loginList);
				selectList.val(selectedName);
			} else if (head == Cthulhu.NAME_OK_HEADER) {
				$("#nameDisp").html(body);
				$("#login").hide();
				$("#play").show();

				// アプリケーション生成
				app = WebSocketGameApp({
					startLabel: 'title', // メインシーンから開始する
					query: '#game-canvas',
					height: 495,
					width: 495,
					fit: false,
					assets: $.extend(true, {}, CARD_IMAGE, EVENT_IMAGE, PLAYER_IMAGE, GAME_IMAGE),
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
					playerName: body,
				});
				// アプリケーション実行
				app.run();

				setTimeout(function() {
					app.currentScene.exit();
				}, 1000);
			} else if (head == Cthulhu.BELL_HEADER) {
				SESound.sound(SESound.Type.BELL);
			} else if (head == Cthulhu.ERROR_HEADER) {
				alert(body);
			}
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

		$("#message").keydown(function(e){
			if ($(this).val() !== ""
					&& (e.which == 13 || e.keyCode == 13)) {
				if ($("#mesTo").val() !== "") {
					ws.send(Cthulhu.PRIVATE_MES_HEADER + $("#mesTo").val() + Cthulhu.SEPARATOR + $(this).val());
				} else {
					ws.send(Cthulhu.MES_HEADER + $(this).val());
				}
				$(this).val("");
			}
		});

		$("#bell").click(function(){
			ws.send(Cthulhu.BELL_HEADER);
		});

		$("#dice").click(function(){
			ws.send(Cthulhu.DICE_HEADER);
		});
	});
</script>
</head>

<body>
	<h2>Cthulhu in there</h2>
	<div id="login">
		<form id="nameForm" action="#">
			名前：
			<input type="text" id="nameInput" /><br />
			パスワード：
			<input type="password" id="passInput" /><br />
			<input type="submit" id="send" value="送信" />
		</form>
		<div class="loginUsers"></div>
	</div>
	<div id="play">
		<h3>ようこそ<span id="nameDisp"></span>さん</h3>
		<div class="clear"></div>
		<div id="playLeft">
			<div id="playerList" class="loginUsers"></div>
			<div id="log"></div>
		</div>
		<div id="playRight">
			<div id="game-stage">
				<canvas id="game-canvas"></canvas>
			</div>
		</div>
		<div class="clear"></div>
		<form id="form" action="#">
			<select id="mesTo">
				<option value="">全体</option>
			</select>
			<input type="text" id="message" />
			<input type="button" id="dice" value="ダイス" />
			<input type="button" id="bell" value="ベル" />
		</form>
	</div>
</body>

</html>