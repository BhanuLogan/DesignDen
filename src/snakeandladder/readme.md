# 🐍 Snake and Ladder - Low-Level Design

## 🎯 Problem Statement

Design and implement a scalable, extensible **Snake and Ladder** game that can support:

* Multiple players
* Custom board size
* Special cell types (e.g. power-ups, penalties, multi-dice bonus)
* Configurable number of dice

---

## ✅ Functional Requirements

1. The game should support **N players**.
2. Players take turns rolling dice to move forward on a board of **M cells**.
3. If a player lands on:

   * **Snake head** → Move down to tail
   * **Ladder base** → Climb to top
   * **Power Up** → Move forward X cells
   * **Penalty** → Move back X cells
   * **Multi Dice** → Roll again for next turn
4. The game ends when a player reaches the last cell.
5. Support custom board configuration via input or config file.

---

## 📦 Non-Functional Requirements

* Extensible architecture (add new types of cells or dice easily).
* Thread-safe turn management (optional for multiplayer concurrency).
* Logging of every move for future replay/debugging.

---

## 🔄 Extensible Features (Follow-Ups)

Here are follow-ups that can be implemented in order:

1. ✅ Support multiple dice
2. ✅ Add new cell types: PowerUp, Penalty, MultiDice
3. ⏳ Add save/resume game state
4. ⏳ Add CLI and/or GUI interface
5. ⏳ Multiplayer over network
6. ⏳ AI players
7. ⏳ Logging and replay functionality
---