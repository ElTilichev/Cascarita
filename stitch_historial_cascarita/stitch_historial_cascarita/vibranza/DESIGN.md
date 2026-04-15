# Design System Document: Neon Folklore

## 1. Overview & Creative North Star
**Creative North Star: "The Neon Folklore"**
This design system moves away from the sterile, "saas-like" look of modern sports apps and embraces a high-end editorial aesthetic. It is a celebration of Mexican "retas" (pickup games)—merging the raw energy of the court with the sophisticated vibrance of contemporary Mexican culture. 

The system achieves a premium feel through **intentional asymmetry** and **tonal depth**. Instead of a rigid, boxed-in grid, we utilize the user's sketch to create a layout that breathes. Elements are allowed to overlap, and typography acts as a structural anchor. We are not just building an app; we are curate a digital sporting event.

---

## 2. Colors
Our palette is a high-contrast dialogue between a deep, obsidian-like foundation and "Rosa Mexicano," vibrant turquoise, and sunny orange.

*   **Primary (`#ff88b2`):** The "Rosa Mexicano." Use this for high-impact actions and brand moments.
*   **Secondary (`#5af8fb`):** Deep Turquoise. Use for technical information, status indicators, and secondary calls to action.
*   **Tertiary (`#ff9f4a`):** Sunny Orange. Use sparingly for accents or to highlight the "next up" status.
*   **The Foundation:** Use `surface` (`#0e0e0e`) as the primary canvas.

### The "No-Line" Rule
To maintain an editorial, premium feel, **1px solid borders for sectioning are strictly prohibited.** Boundaries must be defined solely through background color shifts. For example, a list of players in a "surface-container-low" (`#131313`) section should sit directly on the "surface" (`#0e0e0e`) background without a stroke.

### Surface Hierarchy & Nesting
Treat the UI as a series of physical layers—like stacked sheets of fine paper or polished volcanic stone. 
*   **Nesting:** Place a `surface-container-highest` card inside a `surface-container-low` section to create natural focus.
*   **The Glass & Gradient Rule:** For floating elements (like the "VS" or "Next Team" banners), use Glassmorphism. Combine `surface-variant` with a `backdrop-blur` and a 15% opacity to allow the vibrant primary/secondary colors to bleed through from behind.

---

## 3. Typography
We use a high-contrast typographic scale to create "rhythm" on the screen.

*   **Display & Headlines (Noto Serif):** This is our "Elegant" soul. Use these for scores, team names, and headers. The serif high-contrast provides an authoritative, sporty-luxe feel that mimics premium athletic journals.
*   **Title & Body (Manrope):** This is our "Clean" soul. A geometric sans-serif that ensures readability under the sun or in the heat of a game.

**The Typographic Hierarchy:**
- **Display-LG (`3.5rem`):** Reserved for the current score numbers.
- **Headline-LG (`2.0rem`):** Reserved for "CASCARITA" title and Team Names.
- **Body-MD (`0.875rem`):** Used for player names and game rules.

---

## 4. Elevation & Depth
Depth is achieved through **Tonal Layering**, not structural shadows.

*   **The Layering Principle:** Stack `surface-container` tiers to create lift. A card at `surface-container-lowest` on top of a `surface-container-low` background creates a soft, natural "recessed" look.
*   **Ambient Shadows:** If an element must float (like the "Add Player" FAB), use an extra-diffused shadow.
    *   *Shadow Color:* Tint the shadow with a 4-8% opacity of `surface_tint`. Never use pure black or grey.
*   **The "Ghost Border" Fallback:** If a container needs more definition (e.g., an input field), use the `outline_variant` token at **20% opacity**. This creates a "Ghost Border" that suggests a boundary without cluttering the visual field.

---

## 5. Components

### The Scoreboard (High-End Custom)
Inspired by the hand-drawn sketch, the scoreboard should not be a flat box. 
- Use `surface-container-highest` for the score containers. 
- Large `display-lg` numbers in `on_surface`.
- Apply a subtle `primary` (Rosa Mexicano) gradient glow behind the active winning score to indicate game state.

### Buttons
- **Primary:** Filled with `primary` (`#ff88b2`). Text in `on_primary`. Use `rounded-lg` (1rem).
- **Secondary:** Use the "Ghost Border" style with `secondary` text.
- **Tertiary:** Text-only in `tertiary_fixed`, no background.

### Cards & Lists (The "Anti-Divider" Rule)
For the "Upcoming Games" or "Player Queue":
- **Forbid dividers.** Use `spacing-4` (1rem) or `spacing-6` (1.5rem) of vertical white space.
- Use alternating `surface-container-low` and `surface-container-lowest` to distinguish between list items.

### Floating Action Button (FAB)
The "+" button from the sketch should be a circle (`rounded-full`) using the `primary_container` color. It should feel like a vibrant "pop" on the dark background.

---

## 6. Do's and Don'ts

### Do:
*   **Embrace the "Rosa Mexicano":** Use the primary pink for the most important data points.
*   **Use Asymmetry:** Place labels (like "EQUIPO 1") slightly off-center or aligned to the top-left of their container to feel custom and modern.
*   **Iterate on the Sketch:** The sketch shows a vertical stack; use varying heights for the "Next Team" and "Queue" sections to create a sense of hierarchy.

### Don't:
*   **Don't use 100% white:** Use `on_surface` or `on_surface_variant` to keep the dark mode sophisticated rather than jarring.
*   **Don't use default shadows:** Avoid the "drop shadow" look. If it doesn't look like ambient light, it's too heavy.
*   **Don't use sharp corners:** This is an energetic, "human" app. Stick to the `md` (0.75rem) and `lg` (1rem) roundedness scale.