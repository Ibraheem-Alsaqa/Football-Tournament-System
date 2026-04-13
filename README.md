# ⚽ Football Tournament Management System (Java)

A robust, console-based Java application designed to automate the complete lifecycle of a football tournament. This project demonstrates advanced **Object-Oriented Programming (OOP)** principles and rigorous software engineering practices.

---

## 🚀 Key Features

* **Dynamic Data Management:** Flexible system for registering teams and players with unique identifiers.
* **Automated Grouping Logic:** Algorithmic distribution of teams into groups of four to ensure a fair and randomized tournament draw.
* **Interactive Match Engine:** Real-time tracking of goal scoring and match status updates through a command-line interface.
* **Qualification & Scoring:** Automatic point calculation (Win: 3 pts, Draw: 1 pt) with built-in logic to determine top qualifiers.
* **Top Scorer Analytics:** Efficient object-traversal to identify and display individual player performance across the entire tournament.

---

## 🛠 Technical Analysis & Design Philosophy

The architecture emphasizes **Data Integrity** and **System Stability** through the following principles:

### 1. Encapsulation & Data Protection
All core attributes (goals, points, player rosters) are defined as `private` to ensure strict integrity.
* **Controlled Modification:** For instance, the `incrementPoints` method in the `Team` class validates input (only 1 or 3), preventing invalid state changes and enforcing the rules of football.

### 2. Abstraction
The system provides a simplified interface for complex operations. 
* **Example:** Users interact with a clean `playNextMatch()` method, while the back-end manages a **2D Boolean Matrix** (`matches[][]`) to track fixture history and prevent duplicate matches.

### 3. Memory Safety: Deep Copying
To prevent unintended side effects, the `getPlayers()` method in the `Team` class implements **Deep Copying**:
* **Mechanism:** Instead of returning a reference to the original array, the system creates a new array and clones each player object.
* **Benefit:** This ensures the internal "Object State" remains immutable from external logic.

---

## 🏗 Class Architecture

| Class | Responsibility |
| :--- | :--- |
| **`Player`** | Encapsulates individual athlete data and scoring history. |
| **`Team`** | Manages player collections and tracks total team performance. |
| **`Group`** | The central logic hub managing match scheduling and group-stage qualification. |
| **`Assignment5`** | The main entry point orchestrating the tournament flow and User I/O. |

---

## 📝 About the Developer

**Ibrahim Raafat Al-Saqa** *Computer Engineering Student at the University of Jordan*

* **Focus:** Software Development, Embedded Systems, and Digital Logic Design.
* **Connect:** [LinkedIn]([linkedin.com/in/ibraheem-alsaqqa](https://www.linkedin.com/in/ibraheem-alsaqqa/)) | [GitHub](https://github.com/Ibraheem-Alsaqa)
