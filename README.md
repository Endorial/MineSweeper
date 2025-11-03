# 💣 MineSweeper Android Game

<div align="center">

![MineSweeper Logo](https://user-images.githubusercontent.com/57392686/217226002-9eacfe51-0901-4c8a-800f-c36dc1826fed.png)

**A classic MineSweeper game for Android with modern UI and multiple difficulty levels**

[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com)
[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg)](https://developer.android.com)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Kotlin](https://img.shields.io/badge/Language-Java-orange.svg)](https://www.java.com)

</div>

---

## 📋 Table of Contents
- [✨ Features](#-features)
- [📸 Screenshots](#-screenshots)
- [🚀 Installation](#-installation)
- [🎮 How to Play](#-how-to-play)
- [📖 Game Rules](#-game-rules)
- [🛠️ Technical Details](#️-technical-details)
- [🤝 Contributing](#-contributing)
- [📄 License](#-license)

---

## ✨ Features

- 🎯 **Multiple Difficulty Levels**: Easy (5x5), Medium (8x8), and Hard (10x10)
- ⏱️ **Timer System**: Track your solving time with a countdown timer
- 🏆 **Best Time Tracking**: Save and display your best completion time per difficulty
- 🎨 **Modern UI**: Clean, intuitive interface with card-based design
- 🚩 **Flag System**: Mark suspected mine locations with flags
- 💥 **Classic Gameplay**: Traditional MineSweeper mechanics with flood-fill reveal
- 📱 **Responsive Design**: Optimized for various Android screen sizes
- 🎭 **Visual Feedback**: Color-coded numbers and status indicators

---

## 📸 Screenshots

<div align="center">

### Main Menu & Difficulty Selection
<img src="https://user-images.githubusercontent.com/57392686/217249296-bac77e3c-1fb2-49d1-8502-81ba5d990147.png" width="200" alt="Main Menu"/> <img src="https://user-images.githubusercontent.com/57392686/217249309-886add46-49c3-4f2e-bcb2-ecfa5dc4ef45.png" width="200" alt="Difficulty Selection"/>

### Gameplay
<img src="https://user-images.githubusercontent.com/57392686/217249358-c1521053-b8dd-48ce-b290-c5d3cf4eb84f.png" width="200" alt="Gameplay"/> <img src="https://user-images.githubusercontent.com/57392686/217249811-158e3b08-e3ce-434f-9d29-d9149ec98c5d.png" width="200" alt="Game Board"/>

</div>

---

## 🚀 Installation

### Prerequisites
- **Android Studio**: Download and install from [official website](https://developer.android.com/codelabs/basic-android-kotlin-compose-install-android-studio#0)
- **Android SDK**: API level 16 (Android 4.1) or higher
- **JDK**: Java Development Kit 8 or higher

### Steps
1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/minesweeper-android.git
   cd minesweeper-android
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an existing Android Studio project"
   - Navigate to the cloned directory and select it

3. **Build the project**
   - Wait for Gradle sync to complete
   - Build → Make Project (or press `Ctrl+F9`)

4. **Run on device/emulator**
   - Connect an Android device or start an emulator
   - Run → Run 'app' (or press `Shift+F10`)

---

## 🎮 How to Play

### Basic Controls
- **Tap**: Reveal a cell
- **Long Press**: Flag/unflag a cell
- **Smiley Button**: Restart the game

### Objective
- Reveal all cells that don't contain mines
- Flag all cells that contain mines
- Avoid clicking on mines!

### Game States
- 🟢 **Ready**: Game is ready to start
- 🟡 **Playing**: Timer is running, game in progress
- 🔴 **Game Over**: Hit a mine - all mines revealed
- 🟢 **Victory**: All non-mine cells revealed!

---

## 📖 Game Rules

### MineSweeper Basics
1. **Numbers**: Indicate how many mines are adjacent (including diagonally)
2. **Empty Cells**: Auto-reveal adjacent cells when clicked
3. **Flags**: Mark cells you suspect contain mines
4. **Timer**: 999-second countdown - game over when time expires

### Winning Condition
- All non-mine cells must be revealed
- Mines can be flagged or left unflagged

### Losing Condition
- Clicking on a mine ends the game
- Timer reaching zero ends the game

### Number Color Coding
- **1**: Blue
- **2**: Green
- **3**: Red
- **4**: Purple
- **5**: Brown
- **6**: Turquoise
- **7**: Black
- **8**: Gray

---

## 🛠️ Technical Details

### Architecture
- **Language**: Java
- **Architecture**: MVVM-inspired structure
- **UI Framework**: Android Views with RecyclerView
- **Minimum SDK**: API 16 (Android 4.1 Jelly Bean)
- **Target SDK**: API 32 (Android 12L)

### Key Components
- `MineSweeperGame`: Core game logic and state management
- `MineGrid`: Grid data structure and mine placement
- `MineGridRecyclerAdapter`: UI adapter for the game board
- `MainActivity`: Game screen with timer and controls
- `MenuActivity`: Main menu and difficulty selection

### Dependencies
```gradle
dependencies {
    implementation 'androidx.appcompat:appcompat:1.2.0'
    implementation 'androidx.recyclerview:recyclerview:1.1.0'
    implementation 'com.google.android.material:material:1.5.0'
    implementation 'androidx.cardview:cardview:1.0.0'
}
```

### Build Configuration
- **Compile SDK**: API 32
- **Build Tools**: 30.0.3
- **Gradle Version**: 6.1.1+

---

## 🤝 Contributing

We welcome contributions! Please follow these steps:

1. **Fork the repository**
2. **Create a feature branch**: `git checkout -b feature/your-feature-name`
3. **Make your changes** and test thoroughly
4. **Commit your changes**: `git commit -m 'Add some feature'`
5. **Push to the branch**: `git push origin feature/your-feature-name`
6. **Open a Pull Request**

### Development Guidelines
- Follow Android development best practices
- Maintain consistent code style
- Add comments for complex logic
- Test on multiple device sizes and API levels
- Update README for significant changes

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

```
MIT License

Copyright (c) 2023 MineSweeper Android

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

---

<div align="center">

**Enjoy playing MineSweeper! 🎮💣**

Made with ❤️ for Android developers

[⭐ Star this repo](https://github.com/yourusername/minesweeper-android) • [🐛 Report Issues](https://github.com/yourusername/minesweeper-android/issues)

</div>

