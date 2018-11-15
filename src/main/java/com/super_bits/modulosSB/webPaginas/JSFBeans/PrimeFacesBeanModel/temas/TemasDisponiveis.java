package com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel.temas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * TemasDisponiveis
 *
 * @author Oleg Varaksin / last modified by $Author: $
 * @version $Revision: 1.0 $
 */
public class TemasDisponiveis implements Serializable {

    private static final long serialVersionUID = 20120517L;

    private static TemasDisponiveis INSTANCE = null;

    public static TemasDisponiveis instance() {
        if (INSTANCE == null) {
            INSTANCE = new TemasDisponiveis();
        }

        return INSTANCE;
    }

    private final HashMap<String, TemaPrimeFaces> themesAsMap;
    private final List<TemaPrimeFaces> themes;

    public TemasDisponiveis() {
        List<String> themeNames = new ArrayList<String>();
        themeNames.add("afterdark");
        themeNames.add("afternoon");
        themeNames.add("afterwork");
        themeNames.add("aristo");
        themeNames.add("black-tie");
        themeNames.add("blitzer");
        themeNames.add("bluesky");
        themeNames.add("casablanca");
        themeNames.add("cupertino");
        themeNames.add("dark-hive");
        themeNames.add("dot-luv");
        themeNames.add("eggplant");
        themeNames.add("excite-bike");
        themeNames.add("flick");
        themeNames.add("glass-x");
        themeNames.add("home");
        themeNames.add("hot-sneaks");
        themeNames.add("humanity");
        themeNames.add("le-frog");
        themeNames.add("midnight");
        themeNames.add("mint-choc");
        themeNames.add("overcast");
        themeNames.add("pepper-grinder");
        themeNames.add("redmond");
        themeNames.add("rocket");
        themeNames.add("sam");
        themeNames.add("smoothness");
        themeNames.add("south-street");
        themeNames.add("start");
        themeNames.add("swanky-purse");
        themeNames.add("trontastic");
        themeNames.add("ui-darkness");
        themeNames.add("ui-lightness");
        themeNames.add("vader");
        themeNames.add("temaPersonalizado");

        themesAsMap = new HashMap<String, TemaPrimeFaces>();
        themes = new ArrayList<TemaPrimeFaces>();
        int i = 0;
        for (String themeName : themeNames) {
            TemaPrimeFaces theme = new TemaPrimeFaces(i, themeName, "/resources/SBComp/template/themeswitcher/" + themeName + ".png");
            i++;
            themes.add(theme);
            themesAsMap.put(theme.getName(), theme);
        }
    }

    public final List<TemaPrimeFaces> getThemes() {
        return themes;
    }

    public TemaPrimeFaces getTheme(String name) {
        return themesAsMap.get(name);
    }

}
