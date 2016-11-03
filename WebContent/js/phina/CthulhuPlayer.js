var PLAYER_SIZE_X_BASE = 48;
var PLAYER_SIZE_Y_BASE = 72;
var PLAYER_SCALE = 0.8;
var PLAYER_SIZE_X = PLAYER_SIZE_X_BASE * PLAYER_SCALE;
var PLAYER_SIZE_Y = PLAYER_SIZE_Y_BASE * PLAYER_SCALE;
var PLAYER_IMAGE = {
	image: {
		'player': "./img/players.png",
	}
};

phina.define('CthulhuPlayer', {
	superClass: 'Sprite',

	init: function() {
		this.superInit('player', PLAYER_SIZE_X_BASE, PLAYER_SIZE_Y_BASE);

		this.id = -1;
		this.frameIndex = 0;
		this.name = '';
		this.battle = 0;
		this.san = 0;
		this.chara = '';
		this.charaDetail = '';
		this.skill = '';
		this.skillDetail = '';
		this.setScale(PLAYER_SCALE);
	},

	setData: function(data) {
		this.id = data.id;
		this.frameIndex = this.id - 1;
		this.name = data.name;
		this.battle = data.battle;
		this.san = data.san;
		this.chara = data.chara;
		this.charaDetail = data.charaDetail;
		this.skill = data.skill;
		this.skillDetail = data.skillDetail;

		return this;
	},

	getMessage: function() {
		var mes = '';
		mes += this.name + "\n";
		mes += this.chara + ":\n";
		mes += this.charaDetail + "\n";
		mes += this.skill + ":\n";
		mes += this.skillDetail;
		return mes;
	},

	setDispatch: function(select, scene) {
		if (this.id != -1) {
			this.setInteractive(true);
			this.onpointstart = function() {
				var message = this.getMessage();
				if (select == -1) {
					scene.alertPush(message);
				} else {
					scene.confirmPush(message, select, 0, 0);
				}
			};
		}

		return this;
	},
});
