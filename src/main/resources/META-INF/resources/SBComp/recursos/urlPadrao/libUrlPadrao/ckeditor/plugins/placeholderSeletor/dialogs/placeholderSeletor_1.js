/*
 Copyright (c) 2003-2017, CKSource - Frederico Knabben. All rights reserved.
 For licensing, see LICENSE.md or http://ckeditor.com/license
*/
CKEDITOR.dialog.add("placeholderSeletor",function(a){var c=a.lang.placeholderSeletor,d=a.lang.common.generalTab;return{title:c.title,minWidth:300,minHeight:80,contents:[{id:"info",label:d,title:d,elements:[{id:"name",type:"select",items:[],"default":"meetingTime",style:"min-width: 500px;",label:c.name,"default":"",required:!0,setup:function(b){this.setValue(b.data.name)},onLoad:function(b){console.log($(a.element.$));console.log($(a));var c=$.parseJSON($(a.element.$).attr("palavras_placeholder"));
console.log(c);var d=$(this.getInputElement().$);"undefined"!==typeof b&&$.each(c,function(b,a){d.append($('\x3coption value\x3d"'+a+'"\x3e'+a+"\x3c/option\x3e"))})},onShow:function(){var b=$(a.element.$).attr("palavras_placeholder");console.log(b)},commit:function(a){a.setData("name",this.getValue())}}]}]}});