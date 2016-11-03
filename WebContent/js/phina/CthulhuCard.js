var CARD_SIZE_X_BASE = 48;
var CARD_SIZE_Y_BASE = 72;
var CARD_SCALE = 0.35;
var CARD_SIZE_X = CARD_SIZE_X_BASE * CARD_SCALE;
var CARD_SIZE_Y = CARD_SIZE_Y_BASE * CARD_SCALE;
var CARD_IMAGE = {
	image: {
		'card': "./img/cards.png",
	}
};

phina.define('CthulhuCard', {
	superClass: 'Sprite',

	init: function() {
		this.superInit('card', CARD_SIZE_X_BASE, CARD_SIZE_Y_BASE);

		this.id = -1;
		this.frameIndex = 0;
		this.kind = -1;
		this.kindName = '';
		this.type = -1;
		this.typeName = '';
		this.detail = '';
		this.setScale(CARD_SCALE);
	},

	setData: function(data) {
		this.id = data.id;
		if (this.id == -2) {
			this.frameIndex = 0;
		} else {
			this.frameIndex = this.id + 1;
		}
		this.kind = data.kind;
		this.kindName = data.kindName;
		this.type = data.type;
		this.typeName = data.typeName;
		this.detail = data.detail;

		return this;
	},

	setDispatch: function(select, scene, kind, number) {
		this.setInteractive(true);
		this.onpointstart = function() {
			var message = this.typeName + "\n" + this.detail;
			if (select == -1) {
				scene.alertPush(message);
			} else {
				scene.confirmPush(message, select, kind, number);
			}
		};

		return this;
	}
});