SESound = function () { };
SESound.Audio = function () { };
SESound.Audio.WEB_AUDIO_API = 0;
SESound.Audio.CREATE_JS = 1;
SESound.Type = function () { };
SESound.Type.BELL = 0;
SESound.Type.CHAT = 1;
SESound.Type.DICE = 2;
SESound.Type.JOIN = 3;
SESound.Type.OPENING = 4;
SESound.Type.ENDING = 5;
SESound.Type.SCREAM = 6;

SESound.audio = null;
SESound.SoundUrlList = [
	  './se/bell'
	, './se/chat'
	, './se/dice'
	, './se/join'
	, './se/opening'
	, './se/ending'
	, './se/scream'
];
SESound.webAudioContext = null;
SESound.SoundList = [];
SESound.volume = 1.0

SESound.sound = function (type) {
	try {
		if (SESound.audio === SESound.Audio.WEB_AUDIO_API) {
			var gainNode = SESound.webAudioContext.createGain();
			var source = SESound.webAudioContext.createBufferSource();

			source.buffer = SESound.SoundList[type];
			source.connect(gainNode);
			gainNode.connect(SESound.webAudioContext.destination);
			gainNode.gain.value = SESound.volume;

			source.start();
		} else {
			var instance = createjs.Sound.createInstance(type);

			instance.setVolume(SESound.volume);
			instance.play();
		}
	} catch (e) {
	}
}

$(function(){
	try {
		SESound.webAudioContext = new webkitAudioContext();

		SESound.audio = SESound.Audio.WEB_AUDIO_API;

		var i;
		for (i = 0; i < SESound.SoundUrlList.length; i++) {
			var xhr = new XMLHttpRequest();

			xhr.open('GET', SESound.SoundUrlList[i] + '.mp3', true);
			xhr.responseType = 'arraybuffer';

			xhr.onload = (function() {
				var _i = i;

				return function() {
					SESound.webAudioContext.decodeAudioData(this.response,
							function(buffer) {
								SESound.SoundList[_i] = buffer;
							});
				}
			})();

			xhr.send();
		}
	} catch (e) {
		SESound.audio = SESound.Audio.CREATE_JS;

		var format = '.mp3';

		var userAgent = navigator.userAgent.toLowerCase();

		if (userAgent.match(/firefox/)) {
			format = '.ogg';
		}

		var manifest = [];

		var i;
		for (i = 0; i < SESound.SoundUrlList.length; i++) {
			manifest.push({
				src : SESound.SoundUrlList[i] + format,
				id : i
			});
		}

		var queue = new createjs.LoadQueue(true);
		queue.installPlugin(createjs.Sound);
		queue.loadManifest(manifest, true);
	}
});