/*
 Copyright (c) 2003-2017, CKSource - Frederico Knabben. All rights reserved.
 For licensing, see LICENSE.md or http://ckeditor.com/license
 */
        CKEDITOR.dialog.add("placeholder",
                function (b) {
                    var c = b.lang.placeholder, d = b.lang.common.generalTab;
                    return{
                        title: c.title,
                        minWidth: 300, minHeight: 80,
                        contents: [{id: "info", label: d, title: d, elements: [{
                                        id: "name",
                                        type: "select", items: [],
                                        "default": "meetingTime",
                                        style: "min-width: 500px;",
                                        label: c.name, "default": "", required: !0,
                                        validate: CKEDITOR.dialog.validate.regex(/^[^\[\]<>]+$/, c.invalidName),
                                        setup: function (a) {
                                            this.setValue(a.data.name)
                                        },
                                        commit: function (a) {
                                            a.setData("name", this.getValue())
                                        },
                                        onShow: function () {
                                            var a =
                                                    JSON.parse($(b.element.$).attr("palavras_placeholder"));
                                            console.log(a);
                                            if ("undefined" == typeof a || 0 == a.length)
                                                CKEDITOR.dialog.getCurrent().hide(), alert("Não há nenhuma variável disponível para este conteúdo.")
                                        }, onLoad: function (a) {
                                            a = JSON.parse($(b.element.$).attr("palavras_placeholder"));
                                            console.log(a);
                                            var c = $(this.getInputElement().$);
                                            "undefined" != typeof a && $.each(a, function (a, b) {
                                                c.append($('\x3coption value\x3d"' + b + '"\x3e' + b + "\x3c/option\x3e"))
                                            })
                                        }}]}]}
                });