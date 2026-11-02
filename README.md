<div align="center">

 

<img src="https://capsule-render.vercel.app/api?type=venom&height=220&color=0:171717,50:4a1515,100:8b2020&text=TOTEM%20WARNING&fontColor=ffffff&fontSize=55&fontAlignY=45&desc=Never%20lose%20track%20of%20your%20Totem%20again&descAlignY=65&animation=fadeIn&stroke=8b2020" width="100%" />

 

<br>

 

[![Minecraft](https://img.shields.io/badge/Minecraft-1.21.11-62A744?style=for-the-badge&logo=minecraft&logoColor=white)](https://www.minecraft.net/)

[![Fabric](https://img.shields.io/badge/Fabric-0.18.1-DDBB75?style=for-the-badge)](https://fabricmc.net/)

[![Client Side](https://img.shields.io/badge/Environment-Client--Side-3498DB?style=for-the-badge)](https://fabricmc.net/)

[![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)

 

[![License](https://img.shields.io/badge/License-MIT-purple?style=for-the-badge)](LICENSE)

[![GitHub Repository](https://img.shields.io/badge/GitHub-Repository-181717?style=for-the-badge&logo=github)](https://github.com/zartheusdev/totem-warning)

 

<br>

 

**A lightweight Minecraft Fabric mod that keeps you aware of your Totem of Undying status.**

 

[Features](#-features) • [Installation](#-installation) • [Configuration](#-configuration) • [Building](#-building-from-source)

 

</div>

 

---

 

## 🔮 Overview

 

**Totem Warning** is a customizable, client-side Minecraft Fabric mod designed to prevent you from forgetting your Totem of Undying during critical moments.

 

Whether you're fighting in PvP or exploring dangerous environments, having a totem in the wrong inventory slot can make a difference.

 

Totem Warning provides two independent visual warning systems that alert you whenever your Totem is missing from your offhand or hotbar.

 

**No automatic inventory manipulation. No unnecessary gameplay changes. Just visual awareness when you need it most.**

 

## ✨ Features

 

<table>

<tr>

<td width="50%">

 

### 🔴 Offhand Warning

 

Displays a full-screen colored overlay when your offhand does not contain a Totem of Undying.

 

</td>

<td width="50%">

 

### 🟠 Hotbar Warning

 

Displays a customizable warning overlay when none of your nine hotbar slots contains a Totem.

 

</td>

</tr>

<tr>

<td width="50%">

 

### 🎨 RGB Customization

 

Customize warning colors using individual Red, Green and Blue values.

 

</td>

<td width="50%">

 

### 🌫️ Opacity Control

 

Adjust overlay transparency to create subtle or prominent visual alerts.

 

</td>

</tr>

<tr>

<td width="50%">

 

### 📐 Custom Layouts

 

Choose between five different warning layouts or create your own screen area.

 

</td>

<td width="50%">

 

### 👁️ Live Preview

 

See your warning colors and layout changes directly inside the settings menu.

 

</td>

</tr>

<tr>

<td width="50%">

 

### 🌍 Multilingual Support

 

Available in Turkish, English and Spanish.

 

</td>

<td width="50%">

 

### ⚙️ Independent Controls

 

Configure, enable or disable each warning system separately.

 

</td>

</tr>

</table>

 

## 🎯 Warning Systems

 

### 1. Offhand Warning

 

The Offhand Warning monitors your offhand slot.

 

- Activates when your offhand does not contain a Totem of Undying.

- Displays a full-screen colored overlay.

- Supports custom RGB colors.

- Features adjustable opacity.

- Can be independently enabled or disabled.

 

### 2. Hotbar Warning

 

The Hotbar Warning checks all nine hotbar slots.

 

- Activates when no Totem of Undying is present in your hotbar.

- Supports five different visual layouts.

- Allows custom positioning and dimensions.

- Features independent RGB and opacity settings.

 

## 🖥️ Hotbar Warning Layouts

 

Choose the warning style that fits your gameplay.

 

| Layout | Description |

|:---|:---|

| `Custom Area` | Create a custom warning area using X, Y, width and height percentages. |

| `Hotbar Area` | Highlights the area directly over your hotbar. |

| `Screen Border` | Displays a colored warning around the screen edges. |

| `Bottom Strip` | Displays a horizontal warning along the bottom of the screen. |

| `Top Strip` | Displays a horizontal warning along the top of the screen. |

 

> **Note:** Offhand Warning always uses a full-screen overlay. The five layout options are exclusive to Hotbar Warning.

 

## ⚙️ Configuration

 

Open the in-game settings menu by pressing:

 

<div align="center">

 

# `K`

 

**Default Settings Keybind**

 

</div>

 

The keybind can also be changed through Minecraft's Controls menu.

 

### Settings Menu

 

The configuration screen provides:

 

- Separate Offhand and Hotbar tabs.

- RGB color sliders.

- Adjustable opacity.

- Five Hotbar Warning layouts.

- Custom X and Y positioning.

- Custom width and height.

- Interface language selection.

- Live warning previews.

- Save and Close controls.

- Reset configuration option.

 

### Default Settings

 

| Setting | Default |

|:---|:---|

| Interface Language | Turkish |

| Offhand Warning | Enabled |

| Offhand Color | `#FF0000` |

| Offhand Opacity | `80 / 255` |

| Hotbar Warning | Enabled |

| Hotbar Color | `#FF8C00` |

| Hotbar Opacity | `140 / 255` |

| Hotbar Layout | Custom Area |

| Custom X / Y | `25% / 86%` |

| Custom Width / Height | `50% / 14%` |

 

### Configuration File

 

All settings are automatically saved to:

 

```text

.minecraft/config/totemwarning.json

```

 

The configuration file is generated automatically when the mod launches for the first time.

 

RGB and opacity values range from `0` to `255`. Custom layout coordinates and dimensions use screen percentages.

 

## 📦 Installation

 

### Requirements

 

| Component | Version |

|:---|:---|

| Minecraft Java Edition | `1.21.11` |

| Mod Loader | Fabric |

| Fabric Loader | `0.18.1+` |

| Fabric API | `0.141.0+1.21.11` |

| Java | `21+` |

 

### Installation Guide

 

1. Install [Fabric Loader](https://fabricmc.net/use/installer/) for Minecraft `1.21.11`.

2. Download [Fabric API](https://modrinth.com/mod/fabric-api) matching your Minecraft version.

3. Download the latest Totem Warning JAR.

4. Move the downloaded `.jar` file into your Minecraft `mods` folder.

5. Launch Minecraft using your Fabric profile.

 

<div align="center">

 

### ⬇️ Download

 

[![Download Mod](https://img.shields.io/badge/Download-Totem%20Warning%201.0.0-success?style=for-the-badge&logo=github)](https://github.com/zartheusdev/totem-warning/blob/main/totem-warning-1.0.0.jar)

 

</div>

 

## 🌐 Client-Side Compatibility

 

Totem Warning is a **100% client-side mod**.

 

This means:

 

- No server installation is required.

- Other players do not need to install the mod.

- Works on multiplayer servers.

- Does not require server-side modifications.

- Does not automatically move, equip or replenish items.

- All warning rendering and configuration happen locally.

 

**Your game, your settings, your control.**

 

## 🛠️ Building From Source

 

Want to compile Totem Warning yourself?

 

### Prerequisites

 

- JDK 21 or newer.

- Git.

 

### Clone Repository

 

```bash

git clone https://github.com/zartheusdev/totem-warning.git

 

cd totem-warning

```

 

### Build

 

**Windows:**

 

```powershell

.\gradlew.bat build

```

 

**Linux / macOS:**

 

```bash

./gradlew build

```

 

The compiled JAR files will be generated inside:

 

```text

build/libs/

```

 

## 🤝 Contributing

 

Contributions and suggestions are welcome!

 

- Found a bug? Open an [Issue](https://github.com/zartheusdev/totem-warning/issues).

- Have a feature request? Share your idea.

- Want to improve the project? Submit a Pull Request.

 

Every contribution is appreciated.

 

## 📄 License

 

Totem Warning is distributed under the [MIT License](LICENSE).

 

You are free to use, modify and distribute this project according to the license terms.

 

---

 

<div align="center">

 

### 🔮 Never Forget Your Totem.

 

**Stay prepared. Stay protected.**

 

<br>

 

Made with ❤️ for Minecraft players.

 

<br>

 

[![GitHub](https://img.shields.io/badge/Visit-Repository-181717?style=for-the-badge&logo=github)](https://github.com/zartheusdev/totem-warning)

 

</div>
