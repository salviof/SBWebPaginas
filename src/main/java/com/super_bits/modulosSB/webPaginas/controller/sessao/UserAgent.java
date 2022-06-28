/* 
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90 

 */
package com.super_bits.modulosSB.webPaginas.controller.sessao;



/*-
 * #%L
 * Java Tool
 * %%
 * Copyright (C) 2014 - 2017 OSGL (Open Source General Library)
 * %%
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
 * #L%
 */



import java.util.regex.Pattern;

/*
 * PC
 *      Windows
 *          Chrome
 *          Firefox
 *          Edge (old)
 *          Edge (new)
 *          IE11
 *          IE8
 *          IE7
 *          IE6
 *          Netscape Navigator
 *          Opera
 *          Vivaldi
 *      Linux
 *          Chrome
 *          Firefox
 *  Macintosh
 *  macOs
 * IPhone
 * Android
 * Windows Mobile
 * HP system
 * Sun system
 * IBM mainframe
 */
public class UserAgent {

    public final static UserAgent UNKNOWN = new UserAgent();

    public static enum OS {
        MAC_OS, IOS, WIN32, WIN64, LINUX, DROID, SYMBIAN, BLACKBERRY, J2ME, SUN_OS, BOT, UNKNOWN
    }
    private OS os_ = null;
    public OS getOS() {
        return os_;
    }

    public enum Device {
        IPHONE,
        IPAD,
        IPOD,
        DROID,
        DROID_TABLET,
        BLACKBERRY,
        SONYERICSSON,
        NOKIA,
        PC,
        MOBILE,
        BOT,
        UNKNOWN
    }

    private Device device_ = null;
    public Device getDevice() {
        return device_;
    }
    public final boolean is(Device device) {
        return (device_ == device);
    }

    public final boolean isMobile() {
        final Device[] da = {
            Device.IPHONE,
            Device.IPOD,
            Device.DROID,
            Device.BLACKBERRY,
            Device.SONYERICSSON,
            Device.NOKIA
        };
        for (Device d: da) {
            if (device_ == d) return true;
        }
        return false;
    }
    
    public final boolean isTablet() {
        final Device[] da = {
            Device.IPAD,
            Device.DROID_TABLET
        };
        for (Device d: da) {
            if (device_ == d) return true;
        }
        return false;
    }

    public static enum Browser {
        IE_6, IE_7, IE_8, IE_9, IE_10, IE_11, EDGE,
        CHROME, SAFARI, FIREFOX_3, FIREFOX, OPERA, UCWEB, BOT, UNKNOWN
    }

    private Browser browser_ = Browser.UNKNOWN;
    public final Browser getBrowser() {
        return browser_;
    }

    public final boolean isIE678() {
        Browser b = browser_;
        return Browser.IE_6 == b || Browser.IE_7 == b || Browser.IE_8 == b;
    }

    public final boolean isIE9Down() {
        Browser b = browser_;
        return Browser.IE_8 == b || Browser.IE_9 == b || Browser.IE_6 == b || Browser.IE_7 == b;
    }

    public final boolean isIE9Up() {
        Browser b = browser_;
        return Browser.IE_9 == b || Browser.IE_10 == b || Browser.IE_11 == b;
    }

    public final boolean isIE10Up() {
        Browser b = browser_;
        return Browser.IE_10 == b || Browser.IE_11 == b;
    }

    public final boolean isIE11Up() {
        Browser b = browser_;
        return Browser.IE_11 == b;
    }

    public final boolean isEdge() {
        Browser b = browser_;
        return Browser.EDGE == b;
    }

    public final boolean isIE() {
        return browser_.name().contains("IE");
    }

    public final boolean isFirefox3() {
        return browser_ == Browser.FIREFOX_3;
    }

    public final boolean isFirefox4Up() {
        return browser_ == Browser.FIREFOX && browser_ != Browser.FIREFOX_3;
    }

    public final boolean isFirefox() {
        return browser_.name().contains("FIREFOX");
    }

    public final boolean isOpera() {
        return browser_ == Browser.OPERA;
    }
    
    public final boolean isWebKit() {
        return str_.contains("WebKit");
    }
    
    public final boolean isSafari() {
        return browser_ == Browser.SAFARI;
    }

    public final boolean isChrome() {
        return browser_ == Browser.CHROME;
    }

    public final boolean  isUCWeb() {
        return browser_ == Browser.UCWEB;
    }

    private String str_;

    /**
     *
     * @return A string completa do agente
     */
    @Override
    public final String toString() {
        return str_;
    }

   
   

    /**
     * Construct the instance from http header: user-agent
     * @param userAgent
     */
    public UserAgent(String userAgent) {
        this();
        parse_(userAgent);
        str_ = userAgent;
    }

    private UserAgent() {
        os_ = OS.UNKNOWN;
        device_ = Device.UNKNOWN;
        browser_ = Browser.UNKNOWN;
        str_ = "";
    }

    private static enum P {
        /*
         * Note the sequence of the enum DOSE matter!
         */
        J2ME(Pattern.compile(".*(MIDP|J2ME|CLDC).*"), Device.MOBILE, null, OS.J2ME),
        UCWEB(Pattern.compile(".*UCWEB.*"), Device.MOBILE, Browser.UCWEB, null),
        WIN32(Pattern.compile(".*(Windows|W32).*"), Device.PC, null, OS.WIN32),
        WIN64(Pattern.compile(".*(WOW64|Win64).*"), Device.PC, null, OS.WIN64),
        LINUX(Pattern.compile(".*Linux.*"), null, null, OS.LINUX),
        MAC(Pattern.compile(".*Mac OS.*"), Device.PC, null, OS.MAC_OS),
        SOS(Pattern.compile(".*SunOS.*"), Device.PC, null, OS.SUN_OS),
        IPHONE(Pattern.compile(".*iPhone.*"), Device.IPHONE, Browser.SAFARI, OS.IOS),
        IPAD(Pattern.compile(".*iPad.*"), Device.IPAD, Browser.SAFARI, OS.IOS),
        IPOD(Pattern.compile(".*iPod.*"), Device.IPOD, Browser.SAFARI, OS.IOS),
        DROID_TABLET(Pattern.compile(".*Android.*"), Device.DROID_TABLET, null, OS.DROID),
        DROID_MOBILE(Pattern.compile(".*Android.*Mobile.*"), Device.DROID, null, OS.DROID),
        BLACKBERRY(Pattern.compile(".*BlackBerry.*"), Device.BLACKBERRY, null, OS.BLACKBERRY),
        SYMBIAN(Pattern.compile(".*Symbian.*", Pattern.CASE_INSENSITIVE), null, null, OS.SYMBIAN),
        SONYERICSSON(Pattern.compile(".*SonyEricsson.*"), Device.SONYERICSSON, null, null),
        NOKIA(Pattern.compile(".*Nokia.*", Pattern.CASE_INSENSITIVE), Device.NOKIA, null, null),
        IE6(Pattern.compile(".*MSIE\\s+[6]\\.0.*"), Device.PC, Browser.IE_6, null),
        IE7(Pattern.compile(".*MSIE\\s+[7]\\.0.*"), Device.PC, Browser.IE_7, null),
        IE8(Pattern.compile(".*MSIE\\s+[8]\\.0.*"), Device.PC, Browser.IE_8, null),
        IE9(Pattern.compile(".*MSIE\\s+(9)\\.0.*"), Device.PC, Browser.IE_9, null),
        IE10(Pattern.compile(".*MSIE\\s+(10)\\.0.*"), null, Browser.IE_10, null),
        IE11(Pattern.compile(".*Windows\\s+NT.+rv:(11|12)\\.0.*"), Device.PC, Browser.IE_11, null),
        FIREFOX(Pattern.compile(".*Firefox.*"), null, Browser.FIREFOX, null),
        FIREFOX3(Pattern.compile(".*Firefox/3.*"), null, Browser.FIREFOX_3, null),
        SAFARI(Pattern.compile(".*Safari.*"), null, Browser.SAFARI, null),
        CHROME(Pattern.compile(".*Chrome.*"), null, Browser.CHROME, null),
        EDGE(Pattern.compile(".*\\s+Edg\\/.*"), null, Browser.EDGE, null),
        OPERA(Pattern.compile(".*Opera.*"), null, Browser.OPERA, null),
        BOT(Pattern.compile(".*(Googlebot|msn-bot|msnbot|Bot|bot|Baiduspider|SeznamBot|facebookexternalhit).*", Pattern.CASE_INSENSITIVE), Device.BOT, Browser.BOT, OS.BOT);

        private final Pattern p_;
        private Device d_ = Device.UNKNOWN;
        private Browser b_;
        private OS o_ = OS.UNKNOWN;
        P(Pattern pattern, Device device, Browser browser, OS os) {
            p_ = pattern;
            d_ = device;
            b_ = browser;
            o_ = os;
        }
        boolean matches(String ua) {
            return p_.matcher(ua).matches();
        }
        void test(String str, UserAgent ua) {
            if (matches(str)) {
                if (null != d_) {
                    ua.device_ = d_;
                }

                if (null != b_) {
                    ua.browser_ = b_;
                }

                if (null != o_) {
                    ua.os_ = o_;
                }
            }
        }
    }

    private void parse_(String userAgent) {
        for (P p: P.values()) {
            p.test(userAgent, this);
        }
    }

    
    






}