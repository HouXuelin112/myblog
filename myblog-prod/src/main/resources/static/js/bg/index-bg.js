/*!
 * particle-bg v0.3.2
 * https://github.com/PengJiyuan/particle-bg
 *
 * Copyright (c) 2018 PengJiyuan
 * Licensed under the MIT license.
 */
!function(t,i){"object"==typeof exports&&"undefined"!=typeof module?module.exports=i():"function"==typeof define&&define.amd?define(i):t.particleBg=i()}(this,function(){"use strict";function n(t,i){var e=window.devicePixelRatio||1,h=null;return t.forEach(function(t){h=t.getContext("2d"),t.style.position=i.position,t.style.width=i.width+"px",t.style.height=i.height+"px",t.width=i.width*e,t.height=i.height*e,h.scale(e,e)}),t}var e=function(t,i){var e=this;void 0===i&&(i={}),this.element=document.querySelector(t),this.color=i.color||"#fff",this.width=this.element.clientWidth,this.height=this.element.clientHeight,this.distance=i.distance||this.width/10,this.radius=i.radius||2,this.points=[],this.count=i.count||100,this.zIndex=i.zIndex||1,this.rate=i.rate||this.width/1e4,this.resize="boolean"!=typeof i.resize||i.resize,this.line="boolean"!=typeof i.line||i.line,this.appendCanvas();for(var h=0;h<this.count;h++)e.points.push(e.getPoint());this.resize&&(window.onresize=function(){e.width=e.element.clientWidth,e.height=e.element.clientHeight,e.distance=i.distance||e.width/10,e.rate=i.rate||e.width/1e4,e.canvas.width=e.width,e.canvas.height=e.height,n([e.canvas],{width:e.width,height:e.height})})};return e.prototype.getPoint=function(){return{x:Math.ceil(Math.random()*this.width),y:Math.ceil(Math.random()*this.height),r:+(Math.random()*this.radius).toFixed(4),rateX:+(2*Math.random()-1).toFixed(4),rateY:+(2*Math.random()-1).toFixed(4)}},e.prototype.appendCanvas=function(){this.canvas=document.createElement("canvas"),this.ctx=this.canvas.getContext("2d"),this.canvas.width=this.width,this.canvas.height=this.height,n([this.canvas],{width:this.width,height:this.height}),this.canvas.style.zIndex=this.zIndex,this.element.appendChild(this.canvas)},e.prototype.draw=function(){this.ctx.clearRect(0,0,this.width,this.height),this.drawPoints(),this.line&&this.drawLines(),window.requestAnimationFrame(this.draw.bind(this))},e.prototype.drawPoints=function(){var e=this;this.points.forEach(function(t,i){e.ctx.beginPath(),e.ctx.arc(t.x,t.y,t.r,0,2*Math.PI,!1),e.ctx.fillStyle=e.color,e.ctx.fill(),0<t.x&&t.x<e.width&&0<t.y&&t.y<e.height?(t.x+=t.rateX*e.rate,t.y+=t.rateY*e.rate):(e.points.splice(i,1),e.points.push(e.getPoint(e.radius)))})},e.prototype.dis=function(t,i,e,h){var n=Math.abs(t-e),s=Math.abs(i-h);return Math.sqrt(n*n+s*s)},e.prototype.drawLines=function(){for(var t=this,i=this.points.length,e=0;e<i;e++)for(var h=i-1;0<=h;h--){var n=t.points[e].x,s=t.points[e].y,o=t.points[h].x,a=t.points[h].y,r=t.dis(n,s,o,a);r<=t.distance&&(t.ctx.beginPath(),t.ctx.moveTo(n,s),t.ctx.lineTo(o,a),t.ctx.strokeStyle=t.color,t.ctx.lineWidth=1-r/t.distance,t.ctx.stroke())}},function(t,i){new e(t,i).draw()}});