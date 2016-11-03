var EVENT_SIZE_X_BASE = 48;
var EVENT_SIZE_Y_BASE = 72;
var EVENT_SCALE = 0.35;
var EVENT_SIZE_X = EVENT_SIZE_X_BASE * EVENT_SCALE;
var EVENT_SIZE_Y = EVENT_SIZE_Y_BASE * EVENT_SCALE;
var EVENT_IMAGE = {
	image: {
		'event': "./img/events.png",
	}
};

phina.define('CthulhuEvent', {
	superClass: 'Sprite',

	init: function() {
		this.superInit('event', EVENT_SIZE_X_BASE, EVENT_SIZE_Y_BASE);

		this.id = -1;
		this.frameIndex = 0;
		this.kind = -1;
		this.kindName = '';
		this.type = -1;
		this.typeName = '';
		this.detail = '';
		this.setScale(EVENT_SCALE);
	},

	setData: function(data) {
		this.id = data.id;
		this.frameIndex = this.id;
		this.kind = data.kind;
		this.kindName = data.kindName;
		this.type = data.type;
		this.typeName = data.typeName;
		this.detail = data.detail;

		return this;
	},

	setDispatch: function(select, scene) {
		if (this.id >= 0) {
			this.setInteractive(true);
			this.onpointstart = function() {
				var message = this.typeName + "\n" + this.detail;
				if (select == -1) {
					scene.alertPush(message);
				} else {
					scene.confirmPush(message, select, 0, 0);
				}
			};
		}

		return this;
	}
});