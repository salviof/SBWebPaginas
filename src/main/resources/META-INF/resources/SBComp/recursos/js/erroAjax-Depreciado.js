/*
 * Copyright 2012-2014 John Yeary <jyeary@bluelotussoftware.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
jsf.ajax.addOnError(function (data) {
    // This shows how to get the information via XPath, but it is not required. The error name can be found using data.errorName
    var errorName = data.responseXML.evaluate('//error/error-name', data.responseXML, null, XPathResult.STRING_TYPE, null);
    var message = 'AJAX Exception';
    message += '\nSource: ' + data.source.id;
    message += '\nValue:' + data.source.value;
    message += '\nError: ' + errorName.stringValue;
    message += '\nMessage: ' + data.errorMessage;
    alert(message);
    //TODO Take Additional actions
});

jsf.ajax.addOnEvent(function (data) {
    alert(data.source.id + " " + data.type + " " + data.status);
});