/*
 * Copyright 2009-2014 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.adamantium.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.primefaces.adamantium.domain.Theme;

@ManagedBean(name = "themeService", eager = true)
@ApplicationScoped
public class ThemeService {

    private List<Theme> themes;

    @PostConstruct
    public void init() {
        themes = new ArrayList<Theme>();
        themes.add(new Theme(0l, "Afterdark", "afterdark"));
        themes.add(new Theme(1l, "Afternoon", "afternoon"));
        themes.add(new Theme(2l, "Afterwork", "afterwork"));
        themes.add(new Theme(3l, "Aristo", "aristo"));
        themes.add(new Theme(4l, "Black-Tie", "black-tie"));
        themes.add(new Theme(5l, "Blitzer", "blitzer"));
        themes.add(new Theme(6l, "Bluesky", "bluesky"));
        themes.add(new Theme(7l, "Bootstrap", "bootstrap"));
        themes.add(new Theme(8l, "Casablanca", "casablanca"));
        themes.add(new Theme(9l, "Cupertino", "cupertino"));
        themes.add(new Theme(10l, "Cruze", "cruze"));
        themes.add(new Theme(11l, "Dark-Hive", "dark-hive"));
        themes.add(new Theme(12l, "Delta", "delta"));
        themes.add(new Theme(13l, "Dot-Luv", "dot-luv"));
        themes.add(new Theme(14l, "Eggplant", "eggplant"));
        themes.add(new Theme(15l, "Excite-Bike", "excite-bike"));
        themes.add(new Theme(16l, "Flick", "flick"));
        themes.add(new Theme(17l, "Glass-X", "glass-x"));
        themes.add(new Theme(18l, "Home", "home"));
        themes.add(new Theme(19l, "Hot-Sneaks", "hot-sneaks"));
        themes.add(new Theme(20l, "Humanity", "humanity"));
        themes.add(new Theme(21l, "Le-Frog", "le-frog"));
        themes.add(new Theme(22l, "Midnight", "midnight"));
        themes.add(new Theme(23l, "Mint-Choc", "mint-choc"));
        themes.add(new Theme(24l, "Overcast", "overcast"));
        themes.add(new Theme(25l, "Pepper-Grinder", "pepper-grinder"));
        themes.add(new Theme(26l, "Redmond", "redmond"));
        themes.add(new Theme(27l, "Rocket", "rocket"));
        themes.add(new Theme(28l, "Sam", "sam"));
        themes.add(new Theme(29l, "Smoothness", "smoothness"));
        themes.add(new Theme(30l, "South-Street", "south-street"));
        themes.add(new Theme(31l, "Start", "start"));
        themes.add(new Theme(32l, "Sunny", "sunny"));
        themes.add(new Theme(33l, "Swanky-Purse", "swanky-purse"));
        themes.add(new Theme(34l, "Trontastic", "trontastic"));
        themes.add(new Theme(35l, "UI-Darkness", "ui-darkness"));
        themes.add(new Theme(36l, "UI-Lightness", "ui-lightness"));
        themes.add(new Theme(37l, "Vader", "vader"));
    }

    public List<Theme> getThemes() {
        return themes;
    }
}
