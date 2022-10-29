/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package com.heimdall.entrypoint.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *      The DetectSmartPhone class encapsulates information about a browser's
 *      connection to your web site. You can use it to find out whether the browser
 *      asking for your site's content is probably running on a mobile device. The
 *      methods were written so you can be as granular as you want. For example,
 *      enquiring whether it's as specific as an iPod Touch or as general as a
 *      smartphone class device. The object's methods return true, or false.
 * </p>
 * @author Felipe de Andrade Batista
 */
@Slf4j
@SuppressWarnings({"IndexOfReplaceableByContains", "checkstyle:all"})
public class UAgentInfo {

    /**
     * Chrome.
     */
    public static final String chrome = "chrome";
    /**
     * Android.
     */
    public static final String deviceAndroid = "android";

    /**
     * Archos.
     */
    public static final String deviceArchos = "archos";
    /**
     * Black Berry.
     */
    public static final String deviceBB = "blackberry";
    /**
     * Black Baerry emulate IE or Firefox.
     */
    public static final String deviceBBStorm = "blackberry95"; // Storm 1 and 2
    /**
     * Initialize variables for other random devices and mobile browsers.
     */
    public static final String deviceBrew = "brew";
    /**
     * Initialize variables for other random devices and mobile browsers.
     */
    public static final String deviceDanger = "danger";
    /**
     * Initialize variables for other random devices and mobile browsers.
     */
    public static final String deviceHiptop = "hiptop";
    /**
     * Initialize variables for other random devices and mobile browsers.
     */
    public static final String deviceIeMob = "iemobile";
    /**
     * Initialize variables for other random devices and mobile browsers.
     */
    public static final String deviceIphone = "iphone";
    /**
     * Initialize variables for other random devices and mobile browsers.
     */
    public static final String deviceIpod = "ipod";
    /**
     * OpenWave server.
     */
    public static final String deviceMidp = "midp"; // a mobile Java technology
    /**
     * OpenWave server.
     */
    public static final String deviceNintendo = "nintendo";
    /**
     * OpenWave server.
     */
    public static final String deviceNintendoDs = "nitro";
    /**
     * OpenWave server.
     */
    public static final String devicePalm = "palm";
    /**
     * OpenWave server.
     */
    public static final String devicePda = "pda"; // some devices report
    /**
     * OpenWave server.
     */
    public static final String devicePlaystation = "playstation";
    /**
     * OpenWave server.
     */
    public static final String deviceS60 = "series60";
    /**
     * OpenWave server.
     */
    public static final String deviceS70 = "series70";
    /**
     * OpenWave server.
     */
    public static final String deviceS80 = "series80";
    /**
     * OpenWave server.
     */
    public static final String deviceS90 = "series90";
    /**
     * OpenWave server.
     */
    public static final String deviceSymbian = "symbian";

    /**
     * For Palm's new WebOS.
     */
    public static final String deviceWebOS = "webos";

    /**
     * Devices.
     */
    public static final String deviceWii = "wii";
    /**
     * Devices.
     */
    public static final String deviceWindows = "windows";
    /**
     * Devices.
     */
    public static final String deviceWinMob = "windows ce";
    /**
     * Devices.
     */
    public static final String deviceXbox = "xbox";
    /**
     * Devices Old Palm.
     */
    public static final String engineBlazer = "blazer";
    /**
     * Devices Common embedded.
     */
    public static final String engineNetfront = "netfront";
    /**
     * Phones Transcoding by.
     */
    public static final String engineOpenWeb = "openweb";
    /**
     * Phones Popular browser.
     */
    public static final String engineOpera = "opera";
    /**
     * Phones An old Windows Mobile.
     */
    public static final String enginePie = "wm5 pie";
    /**
     * OS browser common on some.
     */
    public static final String engineUpBrowser = "up.browser";

    /**
     * Initialize some initial smartphone string variables.
     */
    public static final String engineWebKit = "webkit";
    /**
     * Initialize some initial smartphone string variables.
     */
    public static final String engineXiino = "xiino"; // Another old Palm
    /**
     * Initialize some initial smartphone string variables.
     */
    public static final String firefox = "firefox";
    /**
     * Initialize some initial smartphone string variables.
     */
    public static final String linux = "linux";
    /**
     * Use Maemo, Tablet, and Linux to test for Nokia"s Internet Tablets.
     */
    public static final String maemo = "maemo";
    /**
     * Use Maemo, Tablet, and Linux to test for Nokia"s Internet Tablets.
     */
    public static final String maemoTablet = "tablet";
    /**
     * Ericsson.
     */
    public static final String manuericsson = "ericsson";
    /**
     * Ericsson.
     */
    public static final String manuSamsung1 = "sec-sgh";
    /**
     * Sony.
     */
    public static final String manuSony = "sony";
    /**
     * In some UserAgents, the only clue is the manufacturer.
     */
    public static final String manuSonyEricsson = "sonyericsson";

    /**
     * Themselves as PDAs.
     */
    public static final String mini = "mini"; // Some mobile browsers put "mini"
    /**
     * "mobile" in their user agent strings.
     */
    public static final String mobi = "mobi"; // Some mobile browsers put "mobi"
    /**
     * in their user agent strings. in their names.
     */
    public static final String mobile = "mobile"; // Some mobile browsers put
    /**
     * Standard desktop browser detection strings.
     */
    public static final String msie = "msie";
    /**
     * Standard desktop browser detection strings.
     */
    public static final String msie60 = "msie 6.0";
    /**
     * Standard desktop browser detection strings.
     */
    public static final String msie61 = "msie 6.1";
    /**
     * Standard desktop browser detection strings.
     */
    public static final String msie7 = "msie 7.0";
    /**
     * Standard desktop browser detection strings.
     */
    public static final String msie8 = "msie 8.0";
    /**
     * Standard desktop browser detection strings.
     */
    public static final String msie9 = "msie 9.0";

    /**
     * For Sony Mylo also.
     */
    public static final String mylocom2 = "com2";
    /**
     * For Sony Mylo also.
     */
    public static final String opera = "presto";
    /**
     * For Sony Mylo also.
     */
    public static final String qtembedded = "qt embedded";

    /**
     * Apple.
     */
    public static final String safari = "apple";
    /**
     * In some UserAgents, the only clue is the operator.
     */
    public static final String svcDocomo = "docomo";
    /**
     * In some UserAgents, the only clue is the operator.
     */
    public static final String svcKddi = "kddi";
    /**
     * In some UserAgents, the only clue is the operator.
     */
    public static final String svcVodafone = "vodafone";
    /**
     * In some UserAgents, the only clue is the operator.
     */
    public static final String uplink = "up.link";
    /**
     * In some UserAgents, the only clue is the operator.
     */
    public static final String vndRIM = "vnd.rim"; // Detectable when BB devices
    /**
     * Initialize variables for mobile-specific content.
     */
    public static final String vndwap = "vnd.wap";
    /**
     * OS Detection.
     */
    public static final String unix = "x11";
    /**
     * OS Detection.
     */
    public static final String windows = "windows";
    /**
     * OS Detection.
     */
    public static final String wml = "wml";
    /**
     * OS Detection.
     */
    public static final String mac = "mac";

    private String httpAccept = "";

    // User-Agent and Accept HTTP request headers
    private String userAgent = "";

    /**
     * Initialize the userAgent and httpAccept variables by getting the headers
     * from the HttpServletRequest.
     *
     * @param request the HttpServletRequest to get the header information from
     */
    public UAgentInfo(HttpServletRequest request) {
        this(
                request.getHeader("User-Agent"),
                request.getHeader("Accept")
        );
    }

    /**
     * Initialize the userAgent and httpAccept variables.
     *
     * @param userAgent the UserDataMapper-Agent header
     * @param httpAccept the Accept header
     */
    public UAgentInfo(String userAgent, String httpAccept) {
        if (userAgent != null) {
            this.userAgent = userAgent.toLowerCase();
        }
        if (httpAccept != null) {
            this.httpAccept = httpAccept.toLowerCase();
        }

    }

    /**
     * Detects if the current device is an Android OS-based device.
     *
     * @return Boolean
     */
    public boolean detectAndroid() {
        return userAgent.indexOf(deviceAndroid) != -1;
    }

    /**
     * Detects if the current device is an Android OS-based device and the
     * browser is based on WebKit.
     *
     * @return Boolean
     */
    public boolean detectAndroidWebKit() {
        return detectAndroid() && detectWebkit();
    }

    /**
     * Detects if the current device is an Archos media player/Internet tablet.
     *
     * @return Boolean
     */
    public boolean detectArchos() {
        return userAgent.indexOf(deviceArchos) != -1;
    }

    /**
     * Detects if the current browser is a BlackBerry of some sort.
     *
     * @return Boolean
     */
    public boolean detectBlackBerry() {
        return userAgent.indexOf(deviceBB) != -1 || httpAccept.indexOf(vndRIM) != -1;
    }

    /**
     * Detects if the current browser is a BlackBerry Touch device, such as the
     * Storm.
     *
     * @return Boolean
     */
    public boolean detectBlackBerryTouch() {
        return userAgent.indexOf(deviceBBStorm) != -1;
    }

    /**
     * Detects whether the device is a Brew-powered device.
     *
     * @return Boolean
     */
    public boolean detectBrewDevice() {
        return userAgent.indexOf(deviceBrew) != -1;
    }

    /**
     * Detects the Chrome Browser.
     *
     * @return Boolean
     */
    public boolean detectChrome() {
        return userAgent.indexOf(chrome) != -1;
    }

    /**
     * Detects the Danger Hiptop device.
     *
     * @return Boolean
     */
    public boolean detectDangerHiptop() {
        return userAgent.indexOf(deviceDanger) != -1 || userAgent.indexOf(deviceHiptop) != -1;
    }

    /**
     * Detects the Firefox Browser.
     *
     * @return Boolean Booelan
     */
    public boolean detectFirefox() {
        return userAgent.indexOf(firefox) != -1;
    }

    /**
     * Detects if the current device is an Internet-capable game console.
     *
     * @return Boolean Booelan
     */
    public boolean detectGameConsole() {
        return detectSonyPlaystation() || detectNintendo() || detectXbox();
    }

    /**
     * Detects if the current device is an iPhone.
     *
     * @return Boolean
     */
    public boolean detectIphone() {
        // The iPod touch says it's an iPhone! So let's disambiguate.
        return userAgent.indexOf(deviceIphone) != -1 && !detectIpod();
    }

    /**
     * Detects if the current device is an iPhone or iPod Touch.
     *
     * @return Boolean
     */
    public boolean detectIphoneOrIpod() {
        // We repeat the searches here because some iPods may report themselves
        // as an iPhone, which would be okay.
        return userAgent.indexOf(deviceIphone) != -1 || userAgent.indexOf(deviceIpod) != -1;
    }

    /**
     * Detects if the current device is an iPod Touch.
     *
     * @return Boolean
     */
    public boolean detectIpod() {
        return userAgent.indexOf(deviceIpod) != -1;
    }

    /**
     * Detects if the current device is on one of the Maemo-based Nokia Internet
     * Tablets.
     *
     * @return Boolean
     */
    public boolean detectMaemoTablet() {
        return (userAgent.indexOf(maemo) != -1
                || (userAgent.indexOf(maemoTablet) != -1 && userAgent.indexOf(linux) != -1));
    }

    /**
     * Unix
     *
     * @return Boolean
     */
    public boolean detectUnix() {
        return userAgent.indexOf(unix) != -1;
    }

    /**
     * Detects if the current device supports MIDP, a mobile Java technology.
     *
     * @return Boolean
     */
    public boolean detectMidpCapable() {
        return userAgent.indexOf(deviceMidp) != -1 || httpAccept.indexOf(deviceMidp) != -1;
    }

    /**
     * The longer and more thorough way to detect for a mobile device. Will
     * probably detect most feature phones, smartphone-class devices, Internet
     * Tablets, Internet-enabled game consoles, etc. This ought to catch a lot
     * of the more obscure and older devices, also -- but no promises on
     * thoroughness!
     *
     * @return Boolean
     */
    public boolean detectMobileLong() {
        return detectMobileQuick() || detectMaemoTablet() || detectGameConsole();
    }

    /**
     * The quick way to detect for a mobile device. Will probably detect most
     * recent/current mid-tier Feature Phones as well as smartphone-class
     * devices.
     *
     * @return Boolean
     */
    public boolean detectMobileQuick() {
        // Ordered roughly by market share, WAP/XML > Brew > Smartphone.
        if (detectWapWml()) {
            return true;
        }
        if (detectBrewDevice()) {
            return true;
        }

        // Updated by AHand
        if (detectOperaMobile()) {
            return true;
        }

        if (userAgent.indexOf(engineUpBrowser) != -1) {
            return true;
        }
        if (userAgent.indexOf(engineOpenWeb) != -1) {
            return true;
        }
        if (userAgent.indexOf(deviceMidp) != -1) {
            return true;
        }

        if (detectSmartphone()) {
            return true;
        }
        if (detectDangerHiptop()) {
            return true;
        }

        if (detectMidpCapable()) {
            return true;
        }

        if (userAgent.indexOf(devicePda) != -1) {
            return true;
        }
        if (userAgent.indexOf(mobile) != -1) {
            return true;
        }

        // detect older phones from certain manufacturers and operators.
        if (userAgent.indexOf(uplink) != -1) {
            return true;
        }
        if (userAgent.indexOf(manuSonyEricsson) != -1) {
            return true;
        }
        if (userAgent.indexOf(manuericsson) != -1) {
            return true;
        }
        if (userAgent.indexOf(manuSamsung1) != -1) {
            return true;
        }
        if (userAgent.indexOf(svcDocomo) != -1) {
            return true;
        }
        if (userAgent.indexOf(svcKddi) != -1) {
            return true;
        }
        if (userAgent.indexOf(svcVodafone) != -1) {
            return true;
        }

        return false;
    }

    // *****************************
    // For Desktop Browsers
    // *****************************
    /**
     * Microsoft Internet Explorer.
     *
     * @return Boolean
     */
    public boolean detectMSIE() {
        return userAgent.indexOf(msie) != -1;
    }

    /**
     * Microsoft Internet Explorer.
     *
     * @return Boolean
     */
    public boolean detectMSIE6() {
        return userAgent.indexOf(msie60) != -1 && userAgent.indexOf(msie61) != -1;
    }

    /**
     * Microsoft Internet Explorer.
     *
     * @return Boolean
     */
    public boolean detectMSIE7() {
        return userAgent.indexOf(msie7) != -1;
    }

    /**
     * Microsoft Internet Explorer.
     *
     * @return Boolean
     */
    public boolean detectMSIE8() {
        return userAgent.indexOf(msie8) != -1;
    }

    /**
     * Microsoft Internet Explorer.
     *
     * @return Boolean
     */
    public boolean detectMSIE9() {
        return userAgent.indexOf(msie9) != -1;
    }

    /**
     * Detects if the current device is a Nintendo game device.
     *
     * @return Boolean
     */
    public boolean detectNintendo() {
        return userAgent.indexOf(deviceNintendo) != -1 || userAgent.indexOf(deviceWii) != -1
                || userAgent.indexOf(deviceNintendoDs) != -1;
    }

    /**
     * Detect Opera.
     *
     * @return Boolean
     */
    public boolean detectOpera() {
        return userAgent.indexOf(opera) != -1;
    }

    /**
     * Detects Opera Mobile or Opera Mini. Added by AHand
     *
     * @return Boolean
     */
    public boolean detectOperaMobile() {
        return userAgent.indexOf(engineOpera) != -1 && (userAgent.indexOf(mini) != -1 || userAgent.indexOf(mobi) != -1);
    }

    /**
     * Detects if the current browser is on a PalmOS device.
     *
     * @return Boolean
     */
    public boolean detectPalmOS() {
        // Most devices nowadays report as 'Palm', but some older ones reported
        // as Blazer or Xiino.
        if (userAgent.indexOf(devicePalm) != -1 || userAgent.indexOf(engineBlazer) != -1
                || userAgent.indexOf(engineXiino) != -1 && !detectPalmWebOS()) {
            // Make sure it's not WebOS first
            return !detectPalmWebOS();
        }
        return false;
    }

    /**
     * Detects if the current browser is on a Palm device running the new WebOS.
     *
     * @return Boolean
     */
    public boolean detectPalmWebOS() {
        return userAgent.indexOf(deviceWebOS) != -1;
    }

    /**
     * Detects if the current browser is the S60 Open Source Browser.
     *
     * @return Boolean
     */
    public boolean detectS60OssBrowser() {
        // First, test for WebKit, then make sure it's either Symbian or S60.
        return detectWebkit() && (userAgent.indexOf(deviceSymbian) != -1 || userAgent.indexOf(deviceS60) != -1);
    }

    /**
     * Detect Safari.
     *
     * @return Boolean
     */
    public boolean detectSafari() {
        return userAgent.indexOf(safari) != -1;
    }

    /**
     * Check to see whether the device is any device in the 'smartphone'
     * category.
     *
     * @return Boolean
     */
    public boolean detectSmartphone() {
        return (detectIphoneOrIpod() || detectS60OssBrowser() || detectSymbianOS() || detectWindowsMobile()
                || detectBlackBerry() || detectPalmOS() || detectPalmWebOS() || detectAndroid());
    }

    /**
     * Detects if the current browser is a Sony Mylo device. Updated by AHand
     *
     * @return Boolean
     */
    public boolean detectSonyMylo() {
        return userAgent.indexOf(manuSony) != -1
                && (userAgent.indexOf(qtembedded) != -1 || userAgent.indexOf(mylocom2) != -1);
    }

    /**
     * Detects if the current device is a Sony Playstation.
     *
     * @return Boolean
     */
    public boolean detectSonyPlaystation() {
        return userAgent.indexOf(devicePlaystation) != -1;
    }

    /**
     *
     * Detects if the current device is any Symbian OS-based device, including
     * older S60, Series 70, Series 80, Series 90, and UIQ, or other browsers
     * running on these devices.
     *
     * @return Boolean
     */
    public boolean detectSymbianOS() {
        return userAgent.indexOf(deviceSymbian) != -1 || userAgent.indexOf(deviceS60) != -1
                || userAgent.indexOf(deviceS70) != -1 || userAgent.indexOf(deviceS80) != -1
                || userAgent.indexOf(deviceS90) != -1;
    }

    /**
     * The quick way to detect for a tier of devices. This method detects for
     * devices which can display iPhone-optimized web content. Includes iPhone,
     * iPod Touch, Android, Palm WebOS, etc.
     *
     * @return Boolean
     */
    public boolean detectTierIphone() {
        return detectIphoneOrIpod() || detectPalmWebOS() || detectAndroid() || detectAndroidWebKit();
    }

    /**
     * Detect Mac.
     *
     * @return Boolean
     */
    public boolean detectMac() {
        return userAgent.indexOf(mac) != -1;
    }

    /**
     * The quick way to detect for a tier of devices. This method detects for
     * all other types of phones, but excludes the iPhone and Smartphone Tier
     * devices.
     *
     * @return Boolean
     */
    public boolean detectTierOtherPhones() {
        return detectMobileQuick() && (!detectTierIphone()) && (!detectTierSmartphones());
    }

    /**
     * The quick way to detect for a tier of devices. This method detects for
     * all smartphones, but excludes the iPhone Tier devices.
     *
     * @return Boolean
     */
    public boolean detectTierSmartphones() {
        return detectSmartphone() && (!detectTierIphone());
    }

    /**
     * Detects whether the device supports WAP or WML.
     *
     * @return Boolean
     */
    public boolean detectWapWml() {
        return httpAccept.indexOf(vndwap) != -1 || httpAccept.indexOf(wml) != -1;
    }

    /**
     * Detects if the current browser is based on WebKit.
     *
     * @return Boolean
     */
    public boolean detectWebkit() {
        return userAgent.indexOf(engineWebKit) != -1;
    }

    /**
     * Detect Windows.
     *
     * @return Boolean
     */
    public boolean detectWindows() {
        return userAgent.indexOf(windows) != -1;
    }

    /**
     * Detects if the current browser is a Windows Mobile device.
     *
     * @return Boolean
     */
    public boolean detectWindowsMobile() {
        // Most devices use 'Windows CE', but some report 'iemobile'
        // and some older ones report as 'PIE' for Pocket IE.
        return userAgent.indexOf(deviceWinMob) != -1 || userAgent.indexOf(deviceIeMob) != -1
                || userAgent.indexOf(enginePie) != -1 || (detectWapWml() && userAgent.indexOf(deviceWindows) != -1);
    }

    // *****************************
    // For Mobile Web Site Design
    // *****************************
    /**
     * Detects if the current device is a Microsoft Xbox.
     *
     * @return Boolean
     */
    public boolean detectXbox() {
        return userAgent.indexOf(deviceXbox) != -1;
    }

    /**
     * Return the lower case HTTP_ACCEPT.
     *
     * @return Boolean
     */
    public String getHttpAccept() {
        return httpAccept;
    }

    /**
     * Return the lower case HTTP_USER_AGENT.
     *
     * @return Boolean
     */
    public String getUserAgent() {
        return userAgent;
    }
}
