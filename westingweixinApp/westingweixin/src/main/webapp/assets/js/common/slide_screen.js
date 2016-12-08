var callbackFunc = "";
// 判断是否支持触摸事件
function isTouchDevice(callbackFunction) {
	callbackFunc = callbackFunction;
	try {
		document.createEvent("TouchEvent");
		// alert("支持TouchEvent事件！");
		bindEvent(); // 绑定事件
	} catch (e) {
		// alert("不支持TouchEvent事件！" + e.message);
		bindKeyEvent();// 绑定按键事件
	}
}

// 绑定事件
function bindEvent() {
	document.addEventListener('touchstart', touchSatrtFunc, false);
	document.addEventListener('touchmove', touchMoveFunc, false);
	document.addEventListener('touchend', touchEndFunc, false);
}

/**
 * 绑定按键事件
 */
function bindKeyEvent() {
	// 键盘操作
	$(document).keydown(function(event) {
		switch (event.keyCode) {
		case 39://调用翻页函数
			var func = eval(callbackFunc);
			new func();
			break;
		case 40://调用翻页函数
			var func = eval(callbackFunc);
			new func();
			break;
		}
		return true;
	});
}

var startX = 0, startY = 0;
// touchstart事件
function touchSatrtFunc(evt) {
	try {
		var touch = evt.touches[0]; // 获取第一个触点
		var x = Number(touch.pageX); // 页面触点X坐标
		var y = Number(touch.pageY); // 页面触点Y坐标
		// 记录触点初始位置
		startX = x;
		startY = y;

		var text = 'TouchStart事件触发：（' + x + ', ' + y + '）';
	} catch (e) {
		alert('touchSatrtFunc：' + e.message);
	}
}

// touchmove事件，这个事件无法获取坐标
function touchMoveFunc(evt) {
	try {
		// evt.preventDefault(); // 阻止触摸时浏览器的缩放、滚动条滚动等
		var touch = evt.touches[0]; // 获取第一个触点
		var x = Number(touch.pageX); // 页面触点X坐标
		var y = Number(touch.pageY); // 页面触点Y坐标

		var text = 'TouchMove事件触发：（' + x + ', ' + y + '）';
		var bot = 50; // bot是底部距离的高度
		// // 判断滑动方向
		// if (x - startX >= 0 || y - startY >= 0) {
		// if ((bot + $(window).scrollTop()) >= ($(document).height() - $(
		// window).height())) {
		// // 当底部基本距离+滚动的高度〉=文档的高度-窗体的高度时；
		// // 我们需要去异步加载数据了
		// var func=eval(callbackFunc);
		// //创建函数对象，并调用
		// new func();
		// }
		// }
	} catch (e) {
		alert('touchMoveFunc：' + e.message);
	}
}

// touchend事件
function touchEndFunc(evt) {
	try {
		// evt.preventDefault(); // 阻止触摸时浏览器的缩放、滚动条滚动等
		var endX, endY;
		endX = evt.changedTouches[0].pageX;
		endY = evt.changedTouches[0].pageY;
		var direction = GetSlideDirection(startX, startY, endX, endY);
		switch (direction) {
		// case:0:没滑动；case:1:向右滑动；case:2:向下滑动；case:3:向左滑动；case4：向右滑动
		case 1:
			// 创建函数对象，并调用
			var func = eval(callbackFunc);
			new func();
			break;
		case 3:
			// 创建函数对象，并调用
			var func = eval(callbackFunc);
			new func();
			break;
		}
	} catch (e) {
		alert('touchEndFunc：' + e.message);
	}
}

// 根据起点和终点返回方向 1：向上，2：向下，3：向左，4：向右,0：未滑动
function GetSlideDirection(startX, startY, endX, endY) {
	var dy = startY - endY;
	var dx = endX - startX;
	var result = 0;

	// 如果滑动距离太短
	if (Math.abs(dx) < 2 && Math.abs(dy) < 2) {
		return result;
	}

	var angle = GetSlideAngle(dx, dy);
	if (angle >= -45 && angle < 45) {
		result = 4;
	} else if (angle >= 45 && angle < 135) {
		result = 1;
	} else if (angle >= -135 && angle < -45) {
		result = 2;
	} else if ((angle >= 135 && angle <= 180)
			|| (angle >= -180 && angle < -135)) {
		result = 3;
	}

	return result;
}

// 返回角度
function GetSlideAngle(dx, dy) {
	return Math.atan2(dy, dx) * 180 / Math.PI;
}