<div align="center"><img src="src/main/resources/assets/totemwarning/icon.png" alt="Totem Warning icon" width="128">Totem Warning
Never lose track of your Totem of Undying again.

A customizable, client-side Minecraft Fabric mod that displays visual warnings when you have no totem in your offhand or hotbar.

</div>Overview
Totem Warning helps you notice when a Totem of Undying is missing from the places you rely on during gameplay. It provides two independent, configurable on-screen warnings:
- Offhand warning: Tints the entire screen when you are not holding a Totem of Undying in your offhand.
- Hotbar warning: Tints a selected part of the screen when none of your nine hotbar slots contains a Totem of Undying.
These are visual reminders only. The mod does not automatically equip, move, or replenish totems.
Features
- Two independent warning systems — enable or disable offhand and hotbar alerts separately.
- Custom RGB colors — adjust red, green, and blue values for each warning.
- Adjustable opacity — control how subtle or prominent each overlay appears.
- Five hotbar warning layouts — choose a preset or define your own screen area.
- In-game settings menu — press K by default to configure the mod.
- Live previews — see your warning color and selected hotbar region before closing the menu.
- Three interface languages — Turkish, English, and Spanish.
- Client-side only — no installation is needed on the server.
Hotbar warning layouts
Mode	Description
Custom Area	Place and resize the warning using X, Y, width, and height percentages.
Hotbar Area	Highlight the area directly over the hotbar.
Screen Border	Draw a colored border around the screen edges.
Bottom Strip	Tint a horizontal band along the bottom of the screen.
Top Strip	Tint a horizontal band along the top of the screen.


The offhand warning always uses a full-screen tint; the five layout options apply to the hotbar warning.
Requirements
Component	Version
Minecraft: Java Edition	1.21.11
Mod loader	Fabric Loader 0.18.0+
Dependency	Fabric API compatible with Minecraft 1.21.11
Java	21+


The project is built with Fabric Loader 0.18.1 and Fabric API 0.141.0+1.21.11.
Installation
1. Install Fabric Loader for Minecraft 1.21.11.
2. Download and install the matching Fabric API.
3. Download [totem-warning-1.0.0.jar](./totem-warning-1.0.0.jar) from this repository (use GitHub's Download raw file option).
4. Put the .jar file in your Minecraft mods folder.
5. Launch Minecraft with your Fabric profile.
Note: Totem Warning runs entirely on your client. You can use it on multiplayer servers without asking the server owner to install the mod.

Configuration
Press K in-game to open the settings screen. You can change this shortcut in Minecraft's key bindings.

The menu includes separate Offhand and Hotbar tabs, a language selector, RGB/opacity sliders, live previews, and Save and Close / Reset controls.
Default settings
Setting	Default
Interface language	Turkish
Offhand warning	Enabled
Offhand color	Red (#FF0000)
Offhand opacity	80 / 255
Hotbar warning	Enabled
Hotbar color	Orange (#FF8C00)
Hotbar opacity	140 / 255
Hotbar layout	Custom Area
Custom area X / Y	25% / 86%
Custom area width / height	50% / 14%


Settings are written to:
.minecraft/config/totemwarning.json
The file is created automatically when the mod first launches. RGB and opacity values range from 0 to 255; custom-area position and dimensions use screen percentages.
Build from source
To build the mod yourself, install JDK 21 and clone this repository.

On Windows, run:
git clone https://github.com/zartheusdev/totem-warning.git
cd totem-warning
.\gradlew.bat build
The generated JAR files will be in build/libs/.
Feedback and contributions
Found a bug or have an idea for an improvement? Open an issue. Pull requests are welcome.
License
This project is distributed under the MIT License.
<div align="center">
  Made for Minecraft players who want a clear warning before their next fight.<br>
  <a href="https://github.com/zartheusdev">@zartheusdev</a>
</div>
