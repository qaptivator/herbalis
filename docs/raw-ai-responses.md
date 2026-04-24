# Raw AI Responses

this is where i will pin very useful messages made by Gemini AI, which is the main tool which i use for thinking about progression and items in this mod. these will later get put into well organized docs files.

## herb states -----------------

To make this systematic, we need a **Linear Transformation Pipeline**. In developer terms, think of the raw herb as a "Source Object" that can be cast into different "Functional Classes" depending on the processing method.

Instead of arbitrary recipes, every herb follows the exact same **four-stage logic**.

---

### 1. The Apothecary’s Processing Pipeline

#### **Stage 0: Harvest (The Raw Input)**

- **Item:** Fresh [Herb]
- **State:** Volatile. High potency but rots quickly (becomes "Compost" in 3 days).
- **Utility:** Can be eaten raw for a tiny, immediate effect, but it's inefficient.

#### **Stage 1: Desiccation (The Drying Rack)**

The first branch. You place the fresh herb on a **Drying Rack** (a block that uses air-exposure timers).

- **Output:** Dried [Herb]
- **Logic:** Becomes a "Permanent" item. It never rots, but its direct "healing" power is reduced. It is now a **Base Ingredient** for all further stages.

#### **Stage 2: Extraction (The Solvent Choice)**

Now you decide _how_ to release the chemicals. The "Solvent" determines the **Delivery Method**.

- **Infusion (Water):** Brewed in a Cauldron. Short-lived, high-potency "Teas."
- **Maceration (Oil/Lard):** Mixed in a Mortar & Pestle. Becomes "Ointments" or "Salves."
- **Tincture (Alcohol/Spirit):** Distilled in an Alembic. Becomes long-lasting, high-toxicity "Drops."

#### **Stage 3: Stabilization (The Carrier)**

The final form factor. This determines how the player **uses** the item in-game.

- **Poultice:** (Dried Herb + Paper/Cloth). Applied to a specific body part (UI slot) to treat an "Injury."
- **Elixir:** (Tincture + Glass Bottle). Drunk instantly for a "Buff."
- **Pastille:** (Dust + Gum Arabic/Sugar). Stackable "candies" for minor, long-term maintenance.

---

### 2. The Systematic States of a Single Herb

Let's trace **Chamomile** through this system to see how one "simple" plant becomes a dozen different tools.

| State        | Name                 | Crafting Logic    | In-Game Purpose                                                            |
| :----------- | :------------------- | :---------------- | :------------------------------------------------------------------------- |
| **Raw**      | Fresh Chamomile      | Harvested         | Emergency sleep (reset 10% of Insomnia).                                   |
| **Dried**    | Dried Chamomile      | Drying Rack       | Bulk storage; base for all recipes.                                        |
| **Dust**     | Chamomile Powder     | Mortar & Pestle   | Ingredient for pills/pastilles.                                            |
| **Infusion** | Chamomile Tea        | Dried + Hot Water | Relax: Removes "Tremors" (aim shake) for 5 mins.                           |
| **Salve**    | Chamomile Ointment   | Powder + Lard     | Skin Care: Heals "Burn" damage from Magma/Fire.                            |
| **Tincture** | Essence of Chamomile | Dried + Spirit    | Heavy Sleep: Instantly skip to morning without a bed.                      |
| **Poultice** | Chamomile Wrap       | Dried + Bandage   | Reduction: Reduces the "Toxicity" of other drugs currently in your system. |
| **Pastille** | Chamomile Drop       | Powder + Sugar    | Passive: Very slowly lowers the Insomnia timer over 20 mins.               |

---

### 3. The "State Machine" Summary

By using this system, you don't need to memorize 1,000 recipes. You only need to know the **Rules of the Solvent**:

1.  **Water** = Quick, temporary relief of symptoms (Debuffs).
2.  **Oil/Lard** = Exterior healing (Physical injuries/Burns).
3.  **Alcohol** = Internal system changes (Buffs/Sleep/Toxicity).
4.  **Sugar/Gum** = Automation (Passive, slow-burn effects).

This solves your "XL Food" dilemma because a **Chamomile Tea** (Water) and a **Chamomile Pastille** (Sugar) serve entirely different gameplay needs. One is for "I need to aim my bow _now_," and the other is for "I'm going on a long mining trip and don't want to see Phantoms."

This turns your apothecary from a chest full of "crushed leaves" into a **modular toolkit** where the _form_ of the medicine is just as important as the _ingredient_.

## valerian example -----------------

Exactly. From a technical and gameplay perspective, you’ve just described a **"Lossy Compression"** system for resources. You are turning inventory clutter (stacks of herbs) into high-density, high-utility "executable" items.

To finalize the "systematic" feel, here is how those states and solvents would look in your inventory and on your shelves:

### 1. The Inventory Hierarchy

| Item Class       | Storage Type     | Logic / Behavior                                                                                                              |
| :--------------- | :--------------- | :---------------------------------------------------------------------------------------------------------------------------- |
| **Dried Plants** | Bundles / Chests | **The Bulk:** Low value per item, but infinite shelf life. This is your "raw data."                                           |
| **Powders**      | Jars             | **The Intermediate:** Stackable up to 64. Increases "Surface Area" (efficiency) in recipes by 20% compared to whole leaves.   |
| **Tinctures**    | Vials            | **The Gold Standard:** A single 100ml vial represents ~32 fresh herbs. It is high-density and has the most "logic" per pixel. |
| **Salves**       | Pots / Crocks    | **The Durable:** Does not rot. It acts like a "buffer" for your health; you apply it _before_ the problem gets worse.         |

---

### 2. The Solvent "Engine" (The System)

To keep it from being "weird," the solvents act as the **Type Definition** for the final product:

- **Water (Infusions):** The _Volatile_ solvent. It’s cheap but "leaks" potency. If you leave a tea in your inventory for 2 days, it becomes "Stale Water."
- **Oil/Lard (Salves):** The _Stable_ solvent. It "traps" the herb's properties. It doesn't rot, but it’s slow to apply (long "use" animation).
- **Alcohol (Tinctures):** The _Potent_ solvent. Requires a **Distillery** to make the "Spirit" first. This is the endgame processing that allows for the "Stack of Herbs in a Bottle" effect.
- **Sugar/Gum (Pastilles):** The _Solid_ solvent. It turns a powder into a "Time-Release" item.

---

### 3. The Lifecycle of Valerian (An Example)

To see the "State Machine" in action, look at how **Valerian** changes based on your processing:

- **Fresh Valerian:** (Fresh Herb) Smells terrible, attracts Cats (like Ocelots), rots in 3 days.
- **Dried Valerian:** (Base) Can be stored in a "Fancy Apothecary Drawer."
- **Valerian Powder:** (Step) Put it in a Mortar & Pestle to prep for a Tincture.
- **Valerian Poultice:** (Emergency) Wrapped in cloth. Used if you have the "Shakes" (Mining Fatigue) to temporarily suppress it so you can finish a vein.
- **Valerian Tincture:** (The "Essence") This is your 100-herb distillation. One drop makes you "Sleep" instantly, skipping the night and clearing the "Insomnia" counter entirely.
- **Valerian Pastille:** (The Pill) A tiny sugar-coated tablet. You take it before a long build session; it prevents the "Insomnia" counter from rising for the next 15 minutes.

---

### 4. The "Apothecary Aesthetic" vs. Function

The reason those real-life apothecary images look so cool is because of this exact system:

- **The 100s of Drawers:** Contain the **Dried Plants** (Stage 1).
- **The Ceramic Jars:** Contain the **Powders** and **Salves** (Stage 2/3).
- **The Tiny Glass Bottles:** Contain the **Tinctures** (The Endgame).

By making **Powders** a necessary step for "better surface area," you give the player a reason to actually use the Mortar and Pestle instead of just clicking a crafting table. You aren't just making "magic juice"; you're performing **extraction**.

This avoids the "XL Food Dilemma" because you aren't choosing between 20 things that heal you—you are choosing whether you need a **fast-acting tincture** (alcohol), a **long-term pill** (sugar), or a **topical cream** (lard).

Does this "Systematic Extraction" feel like something that would fit into a "PlainTech" or minimalist high-performance mod?

## machines -----------------

To keep it "Vanilla-like" without electricity, you should focus on **mechanical force, temperature, and time.** These machines shouldn't be "Magic Cubes" with a UI; they should feel like interactive furniture that physically transforms the items.

Here are the 5 core functional blocks for your apothecary:

---

### 1. The Drying Rack (The Dehydrator)

A simple wooden frame with mesh (perhaps made from String and Slabs).

- **Function:** Converts **Fresh Herbs** $\rightarrow$ **Dried Herbs**.
- **Mechanic:** It’s a "Passive Block." It requires **Air Exposure** (no solid blocks directly above it) and **Light**.
- **Vanilla Logic:** Similar to a Campfire or Beehive. You right-click to place up to 4 herbs on the mesh; after 1-2 Minecraft days, they visually change texture to "Brown/Dried."

### 2. The Mortar & Pestle (The Grinder)

A stone or ceramic bowl block.

- **Function:** Converts **Dried Herbs** $\rightarrow$ **Powder**.
- **Mechanic:** Requires **Manual Input**. You place the herb in, and you must "use" the block (hold right-click) for a few seconds.
- **Vanilla Logic:** Think of it like drawing a Bow. The "grinding" sound plays, and then the item pops out as a Powder. This makes mass-producing powders a deliberate labor.

### 3. The Copper Alembic (The Distiller)

A two-part block: a boiling pot on the bottom and a cooling coil on top.

- **Function:** Converts **Alcohol + Powder** $\rightarrow$ **Tincture**.
- **Mechanic:** Requires **Heat**. It must be placed over a Campfire or Magma Block.
- **Vanilla Logic:** Similar to a Brewing Stand, but instead of "Blaze Powder," its "fuel" is the heat source underneath. It drips the final liquid into a Glass Vial over several minutes.

### 4. The Infusion Cauldron (The Brewer)

A standard Minecraft Cauldron, but enhanced with a "Lid" or "Stirrer."

- **Function:** Converts **Water/Oil + Powder** $\rightarrow$ **Tea/Salve**.
- **Mechanic:** Uses **Saturation**. You fill it with water or lard (oil), add the herbs, and wait.
- **Vanilla Logic:** If you leave it too long, it "over-boils" and becomes "Sludge." You have to click it with a bottle at the exact right color/state (indicated by particle effects).

### 5. The Screw Press (The Extractor)

A heavy wooden beam with a large metal screw.

- **Function:** Converts **Animal Parts/Seeds** $\rightarrow$ **Oils/Lard/Juice**.
- **Mechanic:** **Mechanical Force.** You place the item (like Fat or Seeds) in a basin and rotate the screw.
- **Vanilla Logic:** Like a Grindstone or Loom. It’s a physical interaction. This is how you get your "Solvents" (Lard and Oils) to start making salves.

---

### Summary of the "Apothecary Loop"

| Machine             | Input      | Solvent/Power    | Output     |
| :------------------ | :--------- | :--------------- | :--------- |
| **Drying Rack**     | Fresh Herb | Sunlight/Air     | Dried Herb |
| **Screw Press**     | Seeds/Fat  | Mechanical Force | Oil/Lard   |
| **Mortar & Pestle** | Dried Herb | Player Effort    | Powder     |
| **Cauldron**        | Powder     | Water/Oil + Heat | Tea/Salve  |
| **Alembic**         | Powder     | Alcohol + Heat   | Tincture   |

### The "Logic" of the Layout

In a real 15th-century apothecary, the **Drying Racks** would be in the attic (dry air), the **Alembic** and **Cauldron** in the cellar or hearth (fire safety), and the **Mortar** on the counter for everyone to see. In Minecraft, this creates a beautiful, multi-room "Laboratory" feel without a single redstone wire.

## crushed vs powdered -----------------

> will we have crushed/powdered or just powderd? is there a real life diference or we are introducing unecesary complexity? (respond shortly)

Stick to Powdered only.

In real life, "crushed" is just a coarse version of "powdered." In a game, having both creates "Redundant Tiers"—you’d just be adding an extra click for the same result.

Using a single Powdered state keeps the "Surface Area" logic intact while preventing the inventory from bloating with two nearly identical items. Keep it simple: Whole (Dried) → Ground (Powder).

## efects of different variations of the same herb -----------------

> what tiers will we have for every state that you have provided me with? i already know that poultices will be like: poultice (raw crushed clump which you can rub on yourself. very rushed and looses potency very quickly. only useful for something like plantago) -> bandage (more controlled and mobile) etc.

To make the tiers meaningful, we should scale them by **stability** (shelf-life) and **administration** (how long it takes to use).

Here is the tiering logic for the states we’ve discussed:

---

### 1. The Surface Tier (Poultices & Topicals)

This is for external injuries (bleeding, burns, fractures).

- **Tier 1: Raw Mash (The "Plantago" Tier):** Fresh herb + Mortar & Pestle. It’s a wet, green clump.
  - _Effect:_ Fast-acting but rots in 10 minutes.
  - _Usage:_ "I need to stop this bleeding _now_."
- **Tier 2: The Poultice/Bandage:** Powder + Cloth.
  - _Effect:_ Stable. Provides "Lingering Healing"—it heals you slowly while you move.
- **Tier 3: The Salve (Crock):** Powder + Lard/Wax.
  - _Effect:_ High-density. A single crock has 4-8 uses. Best for long-term status resistance (e.g., "Fire Resistance" balm).

### 2. The Solution Tier (Liquid Extractions)

This is for internal chemistry (debuffs, sleep, toxins).

- **Tier 1: Infusion (Tea):** Powder + Water.
  - _Effect:_ Weak and becomes "Stale" after 1 day. Use it for minor things like "Chamomile for Aim."
- **Tier 2: Decoction (Concentrate):** Powder boiled down in a Cauldron for 2x the time.
  - _Effect:_ Stronger, stackable (in vials), but higher Toxicity.
- **Tier 3: Tincture (The Essence):** Powder + Spirit in the **Alembic**.
  - _Effect:_ The "Endgame." Never spoils, near-instant effect, extreme potency.

### 3. The Solid Tier (Portable Maintenance)

This is for long-term exploration where you can't carry a lab.

- **Tier 1: Raw Powder:** Just the dust.
  - _Effect:_ You can toss it in your mouth in a pinch, but it causes "Coughing" (3 seconds of inability to eat/drink).
- **Tier 2: Pastille (The Pill):** Powder + Sugar.
  - _Effect:_ A tiny "buff candy." Low potency, but it stacks to 64.
- **Tier 3: The Lozenge/Bolus:** Powder + Honey + Resin.
  - _Effect:_ "Time-Release." You eat it once, and it grants a 20-minute slow-acting immunity or buff.

---

### 🧪 Example: The "Wound Healing" Path

If you have a **Deep Cut** (Infection risk):

1.  **Immediate:** Smash some **Fresh Yarrow** into a **Raw Mash** and slap it on. (Stops the bleeding, but mash rots soon).
2.  **Travel:** Apply a **Yarrow Bandage**. (Stabilizes the wound so you can walk home).
3.  **Treatment:** Back at the base, apply **Calendula Salve**. (Actually repairs the HP and prevents scarring).

### The "PlainTech" Rule

Each tier up should require a **new machine**:

- **Mash** = Mortar only.
- **Bandage** = Loom/Crafting.
- **Salve** = Screw Press (for lard) + Cauldron.
- **Tincture** = Alembic + Heat.

This creates a "Tech Tree" made of furniture! Does this progression make the 100+ items feel like a hierarchy you can actually master?

> wait so essence are like eye drops right? (yes/no)
> This is for informational purposes only. For medical advice or diagnosis, consult a professional.

Yes. In this system, an Essence (Tincture) is a highly concentrated liquid delivered in tiny doses. Think of them like "biological commands"—just a few drops from a vial provide an immediate, potent effect, unlike a Tea which you have to drink in volume.

## gauze, bandages, plasters -----------------

> what about gnaws or bandages? yknow, the stuff you roll around yourself? or stuff like bandaids or plasters? (reply short)

- **Gauze/Bandages:** The **base delivery layer**. You craft them from linen or wool and "impregnate" them with **Mashes** or **Salves**. You physically equip them to a limb slot to treat fractures or bleeding.
- **Plasters:** A **sticky version** using **Resin** or **Beeswax**. These are "set and forget"—they stay on until the injury is 100% healed, then pop off as "Dirty Rags."
- **Gnaws (Chews):** The **low-tech alternative** to pills. You chew on raw **Dried Roots** (like Licorice). It’s slow and uses the "eating" animation repeatedly, but it’s great for a "passive" buff while your hands are busy mining.

> oh sorry i didnt mean a gnaw i meant a gauze

No worries! Let’s refine that. Since **gauze** is a fabric-based delivery system, it functions differently than a liquid or a pill.

In this system, gauze and its relatives act as **"Applied Gear"**:

- **Gauze:** The "raw" fabric. On its own, it only stops the **Bleeding** status but doesn't heal the wound.
- **Impregnated Gauze (The Bandage):** This is gauze combined with a **Mash** or **Powder**. It acts as a "constant infuser"—as long as it’s wrapped around your arm/leg, it’s slowly dripping the herb's effect into your system without increasing **Toxicity**.
- **Plasters (Bandaids):** These use **Beeswax** or **Resin** to stick to the skin. They are for "Localized" issues like a spider bite or a small burn. They are "one-use" and stay on the player model until the timer runs out.

### The Systematic Hierarchy:

1.  **Gauze:** Just the cloth.
2.  **Bandage:** Gauze + Herb (The "Mobile Treatment").
3.  **Plaster:** Gauze + Herb + Resin (The "Long-term Seal").

This fits perfectly with your "PlainTech" feel—it’s physical, mechanical, and requires you to actually "dress" a wound rather than just clicking a potion.

## internal naming -----------------

> okay now tell me every state which a plant can be in, updated. include the internal name, the template for naming ("{} tea", "fresh {}"), machines/recipes/processes (processes as in dunking in water or something) used to make it, and the visual part (considering they will be just retints, tell what parts of the textures will be recolored).

To make this developer-friendly for your ECS-based logic, here is the full **State Machine** for a botanical item. This covers the transformation from a raw entity into a high-density pharmaceutical.

---

### 🌿 The Universal Botanical State Machine

| State         | Internal ID      | Naming Template | Process / Machine                     | Texture Logic (Recolorable)                                                           |
| :------------ | :--------------- | :-------------- | :------------------------------------ | :------------------------------------------------------------------------------------ |
| **Raw**       | `herb_fresh`     | `Fresh {}`      | **Harvesting** (Drops from crop)      | **The entire plant.** High-saturation greens/vibrant colors.                          |
| **Dried**     | `herb_dried`     | `Dried {}`      | **Drying Rack** (Time + Air)          | **The entire plant.** Desaturated, "hay-like" brown/grey tints.                       |
| **Powder**    | `herb_powder`    | `{} Powder`     | **Mortar & Pestle** (Manual)          | **Fine grain pile.** Single color tint based on the herb's "core" color.              |
| **Mash**      | `herb_mash`      | `{} Mash`       | **Mortar** (Fresh Herb + Water)       | **Wet clump.** Darker, high-contrast version of the Fresh tint.                       |
| **Tea**       | `herb_tea`       | `{} Tea`        | **Cauldron** (Powder + Water + Heat)  | **Liquid in Mug.** Recolors the "water" pixels inside the cup.                        |
| **Decoction** | `herb_decoction` | `{} Decoction`  | **Cauldron** (Tea + Extended Heat)    | **Liquid in Bottle.** Same tint as Tea but with a "thicker" (less transparent) Alpha. |
| **Tincture**  | `herb_essence`   | `Essence of {}` | **Alembic** (Powder + Spirit + Heat)  | **Vial.** Recolors the liquid AND adds a "sparkle" glint overlay.                     |
| **Salve**     | `herb_salve`     | `{} Salve`      | **Cauldron** (Powder + Lard/Wax)      | **Jar/Crock.** Recolors the "paste" visible at the top of the jar.                    |
| **Bandage**   | `herb_bandage`   | `{} Bandage`    | **Crafting** (Gauze + Mash/Powder)    | **Wrapped Cloth.** White cloth base with colored "stains" in the center.              |
| **Plaster**   | `herb_plaster`   | `{} Plaster`    | **Crafting** (Gauze + Salve + Resin)  | **Small Square.** Tan/Beige base with a dark colored "pad" in the middle.             |
| **Pastille**  | `herb_pill`      | `{} Pastille`   | **Crafting** (Powder + Sugar + Press) | **Tiny Dot.** A solid, high-brightness tint of the herb's color.                      |

---

### 🛠️ The Processing Logic (The "Algorithms")

1.  **The Desiccation Logic:**

    - **Input:** `herb_fresh`
    - **Transformation:** `if (light_level > 10 && air_above) { timer += 1; }`
    - **Result:** `herb_dried` (Resets "Spoilage" component).

2.  **The Extraction Logic (Solvents):**

    - **Hydro-Extraction (Water):** Fast, low-tier. Creates **Teas**.
    - **Lipid-Extraction (Lard/Oil):** High stability. Creates **Salves**.
    - **Ethanol-Extraction (Spirit):** Maximum concentration. Creates **Essences**.

3.  **The Delivery Logic (Administration):**
    - **Ingested (Tea/Pill/Essence):** Affects the `Internal_Chemistry` component. High **Toxicity** risk.
    - **Topical (Bandage/Plaster/Salve):** Affects the `External_Tissue` component. Zero **Toxicity**, treats "Injury" tags.

---

### 🎨 Texture Tinting Strategy

To avoid making 1,000 textures, you only need **one master texture** per state.

- **Layer 0 (Base):** The non-changing part (the glass bottle, the white bandage cloth, the wooden handle).
- **Layer 1 (Mask):** The area that receives the `hex_code` of the specific plant.
- **Layer 2 (Overlay):** Highlights or shadows to give it depth (e.g., the "liquid" shine).

For example, **Valerian** might have a Hex code of `#8B9A6D`.

- The **Valerian Tea** will simply apply `#8B9A6D` to the "Liquid" mask of the `cup.png` file.
- The **Valerian Essence** will apply a more concentrated version (darker) of `#8B9A6D` to the `vial.png`.

This system is infinitely scalable. If you add a 101st herb, you just give it a name and a color code, and the system automatically generates all 11 states!

## stuff other than herbs -----------------

> we will also have not only herbs but more stuff i think. wine and beer would be epic. yeah okay tell me what extras can we add. stuff which is adjacent or part of natural medicine which isnt a herb.

Since your apothecary is already handling **fermentation** (for spirits) and **extraction** (for lards/resins), adding "The Drunkard’s Cabinet" and "The Mineralist’s Tray" fits perfectly. In the 15th century, the line between a pharmacist and a tavern keeper was surprisingly thin.

Here are the non-herb categories that complete the ecosystem:

### 1. The Fermentary (Alcohols)

Alcohol isn't just a solvent; it’s a delivery vehicle.

- **Wine:** Made from berries/grapes in the **Screw Press** $\rightarrow$ **Cauldron**.
  - _Apothecary Use:_ **Mulled Wine.** Infuse with spices (Cinnamon/Clove) to create a "Heart Tonic" that restores hunger and warmth.
- **Beer/Ale:** Made from Wheat/Hops in the **Cauldron**.
  - _Apothecary Use:_ **Nutritive Ale.** A high-calorie liquid that prevents "Starvation" damage for a day but gives **Slowness**.
- **Spirit (Aqua Vitae):** The result of distilling Wine in the **Alembic**.
  - _Apothecary Use:_ The base for all **Essences**. Can also be used to "Sanitize" a **Gauze** (prevents infection).

### 2. The Apiary & Sweeteners (Fixatives)

These turn bitter medicine into something a player can actually stomach.

- **Honey:** Collected from hives.
  - _Use:_ The natural preservative. Adding Honey to any **Tea** doubles its "Expiration Timer."
- **Royal Jelly:** A rare drop from bees.
  - _Use:_ The "Bio-Catalyst." Used to make **Legendary Pastilles** that have zero Toxicity.
- **Vinegar:** Made by leaving **Wine** in an open Cauldron for 7 days.
  - _Use:_ **Oxymel** (Honey + Vinegar). The best delivery for respiratory herbs (like Horehound).

### 3. The Lithic Tray (Mineral Salts)

Not everything grows; some stuff is mined.

- **Charcoal:** From the furnace.
  - _Use:_ **Activated Carbon.** Eating this is the only way to "Purge" Toxicity quickly without waiting for the timer.
- **Salt:** Obtained by boiling Seawater in a **Cauldron**.
  - _Use:_ **Brine.** Used to soak **Gauzes** to treat "Swelling" (Jump Boost penalty).
- **Sulfur & Niter:** Rare drops from stone/sand.
  - _Use:_ **Fumigants.** Burn these on a campfire to create a "Smoke Zone" that kills insects (Spiders/Silverfish) or disinfects an area.

### 4. The Zoological Shelf (Animal Parts)

This is the "dark" corner of the apothecary.

- **Animal Fat (Tallow/Lard):** From Pigs/Cows in the **Screw Press**.
  - _Use:_ The base for all **Salves**.
- **Antler Dust:** Grinding Deer Antlers (or Goat Horns).
  - _Use:_ **Smelling Salts.** Instantly wakes a player up from "Magic Sleep" (Vex/Evoker spells) or resets the "Faint" bar.
- **Eggshells:** Grinding Eggs.
  - _Use:_ **Calcium Powder.** Added to **Pastilles** to strengthen the "Bone Repair" effect of Comfrey.

---

### 🧪 New Machine: The Fermentation Vat

To handle the wine and beer, you’d need one more "Vanilla-style" block.

- **The Vat:** A large wooden barrel.
- **Process:** You right-click it with **Fruit Juice** (from the Screw Press) or **Wort** (boiled wheat), then **Seal it** (Lid).
- **Vanilla Logic:** Like a Composter, but it takes 3-5 days. It produces a "Bubble" particle effect while working.

### Summary of New States

| Item        | Internal ID      | Process                            | Use                      |
| :---------- | :--------------- | :--------------------------------- | :----------------------- |
| **Spirit**  | `liquid_ethanol` | Wine $\rightarrow$ Alembic         | Essence Base / Sanitizer |
| **Vinegar** | `liquid_vinegar` | Wine $\rightarrow$ Open Air        | Respiratory Tonics       |
| **Brine**   | `liquid_brine`   | Salt + Water                       | Gauze Soaking            |
| **Tallow**  | `lipid_lard`     | Meat Fat $\rightarrow$ Screw Press | Salve Base               |

Adding these makes the "Apothecary" feel like a **complete pre-industrial chemist**. You aren't just a gardener; you're a vintner, a beekeeper, and a geologist. Does this expand the scope too much, or does it feel like the "missing pieces" of the puzzle?

## more stuff -----------------

> Okay meh we won't add them. what would be worthwhile additions are:
>
> 1. ash. when logs burn they release ash now which can be used for making soap or fertilizer (bone meal, compost and ash).
> 2. elemental sulfur. may be too much but it could be useful for changing the soil ph. although this is quite useless and toxic to handle. but it will be quite funny.
> 3. salt. i remember you talking about brine and boiling it so why not. maybe rock salt as well could be nice. maybe for cooking.
> 4. calcium carbonate. now i may reconsider this but getting it from eggshells or from chalk blocks near seas would be quite awesome. can also he used for ph regulation and, likely, better recipe for fertilizer.
>    i don't rly wanna add that much resources since some of these make sense in an apothecary but anything other than that makes it feel cluttered.

This is a much tighter, more "PlainTech" list. It focuses on the **chemical infrastructure** of the apothecary rather than just adding more plants.

By adding these four, you bridge the gap between **Geology** and **Biology**, giving the player "base chemicals" that regulate the environment.

---

### 1. Ash (The Alkaline Base)

Ash is the ultimate "utility" residue. It shouldn't just be a byproduct; it’s a precursor.

- **Source:** Obtained by clicking a **Campfire** or **Furnace** with a shovel.
- **Apothecary Use:** \* **Lye:** Ash + Water in a **Cauldron**. This is the caustic liquid needed to turn **Lard** into **Soap**.
  - **Fertilizer:** Ash + Bone Meal + Compost. A "Tier 2" bone meal that grows herbs 2x faster or guarantees a "High Potency" harvest.
  - **Soap:** Lye + Lard. In-game, this could be used to "Clean" a **Dirty Bandage** (Gauze) so you can reuse the cloth.

### 2. Elemental Sulfur (The Reactive Acid)

As you said, it’s toxic and funny. Let's make it the "High-Intensity" mineral.

- **Source:** Small yellow crystal outcroppings in the **Nether** or near **Lava pools**.
- **Apothecary Use:**
  - **Soil Acidifier:** Applying it to a Farmland block changes its "State." Some herbs (like certain berries or "Sour" herbs) _only_ grow if the soil is acidified with sulfur.
  - **Fumigation:** Throwing Sulfur into a **Campfire** creates a yellow cloud that deals "suffocation" damage to mobs but clears "Mould" or "Fungal" infestations in your lab.

### 3. Salt (The Preservative & Seasoning)

Salt makes everything stable.

- **Source:** **Evaporation** (Boiling sea water in a Cauldron) or **Rock Salt** (Mining halite veins in deserts).
- **Apothecary Use:**
  - **Brine:** (Water + Salt). Essential for **Stage 1** processing of certain roots to prevent rot during the drying process.
  - **Preservation:** Combined with **Fresh Herbs** to create "Salted Herbs," which don't rot but have high "Toxicity" (Dehydration) when consumed.
  - **Curing:** Used for making those **Cabanossi** or preserved meats you mentioned earlier.

### 4. Calcium Carbonate (The Buffer)

This is your "pH Balancer" and structural aid.

- **Source:** **Eggshells** (Mortar & Pestle) or **Chalk/Limestone** blocks near oceans.
- **Apothecary Use:**
  - **Antacid:** The base for "Stomach Pastilles." It neutralizes the "Nausea" or "Hunger" debuffs caused by eating dodgy food or high-toxicity tinctures.
  - **The "Soil Buffer":** If you over-acidify soil with Sulfur, you use **Chalk** to bring it back to neutral.
  - **Plaster of Paris:** Chalk + Water. Used to make **Hardened Splints** for "Fracture" injuries—this would be the Tier 3 version of a bandage that completely freezes a limb so it can heal.

---

### 🏛️ The "Non-Clutter" Integration

To avoid the "too many resources" feeling, these should all fit into your existing **Jar/Drawer** system.

| Resource   | Storage         | Role in the "Apothecary Logic"      |
| :--------- | :-------------- | :---------------------------------- |
| **Ash**    | Sacks / Barrels | The "Cleaner" (Soap/Lye)            |
| **Sulfur** | Small Jars      | The "Aggressor" (Acid/Fumes)        |
| **Salt**   | Large Jars      | The "Preserver" (Brine/Curing)      |
| **Chalk**  | Drawers         | The "Neutralizer" (Antacid/Splints) |

### Why this works:

It creates a **Closed-Loop System**.

- You burn wood for heat $\rightarrow$ get **Ash**.
- Use **Ash** to make **Soap** to clean your **Gauze**.
- Use **Gauze** to apply a **Salve** you made from **Lard** (from your livestock) and **Herbs** (from your sulfur-regulated garden).

It feels like "PlainTech" because every item has a mechanical "why." You aren't just hoarding; you're managing a cycle. Does this feel like it hits the "authentic but not cluttered" sweet spot?

## apothecary drawers -----------------

> to the functional blocks we will also add a 4x4 cabinet which will be like in those apothecaries. but instead of making it like storage drawers you will have to LEFT/RIGHT CLICK each drawer to open it up (inspired by the storage drawers mod)

> for labeling i will make "hover labels". or maybe i will make a thing like "note" which would be made out of TWO paper and will act as a mini sign which shows minimal content on hover.

## make paper out of other than sugarcane -----------------

> wait can you make paper out of other than sugarcane? (reply short)

**Yes.** Historically, paper was rarely made from sugar. In your apothecary setting, you can use:

- **Rags/Linen:** (Old clothes/Gauze) Shredded, boiled with **Ash (Lye)**, and pressed. This was the primary way "rag paper" was made for centuries.
- **Tree Bark:** (Specifically Birch or Mulberry) Beaten into fibers and dried.
- **Papyrus/Reeds:** Sliced thin, soaked, and pressed in your **Screw Press**.
- **Straw/Hemp:** Leftover stalks from crops, processed similarly to wood pulp.

Since you already have the **Screw Press**, **Cauldron**, and **Ash**, "Rag Paper" fits your tech loop perfectly.

> oh yeah how will you make cloths for bandages? my original idea was flax (like in immersive engineering but it used hemp) but maybe theres more ways of getting that?

Since you are aiming for historical authenticity and "PlainTech" loops, you can move beyond the standard "string = wool" Minecraft logic. In a real apothecary, **Linen** (from Flax) was king because it's breathable, doesn't shed fibers into wounds as much as wool, and can be boiled to sanitize.

Here are the most authentic ways to get your **Gauze/Bandage** materials:

### 1. The Flax Loop (The Gold Standard)

Since you mentioned **flax** (льон) earlier, this is the most satisfying mechanical process.

- **Harvest:** You get **Flax Stalks**.
- **Retting:** You dunk the stalks in a **Water Cauldron** or a nearby pond for a few days to rot away the "glue."
- **The Break:** Use the **Screw Press** (or a new "Scutching" interaction) to crush the woody bits, leaving the fibers.
- **The Result:** **Linen Fibers**, which can be woven into **Cloth**.

### 2. The "Rag" Loop (Recycling)

This is the most "utilitarian" method and connects to your **Paper** logic.

- **Source:** If you have old wool tunics, leather, or even failed linen experiments.
- **Process:** Use a knife to cut them into **Rags**.
- **Sanitization:** To turn a "Rag" into a "Bandage," you must boil it in a **Cauldron with Ash (Lye)** to strip away oils and bacteria.
- **Benefit:** This rewards the player for not throwing away "junk" armor or clothes.

### 3. Wild Alternatives (The Survivalist Way)

If you are away from your farm, you can find "Wild Gauze":

- **Nettle Fibers:** Stinging nettles were historically used to make a coarse, tough cloth similar to linen.
- **Birch Bark (Inner):** In a pinch, the thin, papery inner layer of Birch bark was used as a natural "splint" or wrap, though it's not a true fabric.

### 🛠️ The Processing Table

| Material        | Process            | Output          | Quality                                        |
| :-------------- | :----------------- | :-------------- | :--------------------------------------------- |
| **Flax**        | Retting + Pressing | **Linen Cloth** | **High:** Best for Essences/Bandages.          |
| **Old Clothes** | Boiling + Lye      | **Clean Rags**  | **Medium:** Reliable and cheap.                |
| **Wool**        | Spinning           | **Woolen Wrap** | **Low:** Good for warmth, bad for open wounds. |

### Why this works for your ECS:

You can have a single `Material_Fiber` component. Whether it comes from Flax, Nettle, or Hemp, the **Screw Press** and **Cauldron** are the "Functional Blocks" that convert those raw entities into the `Item_Cloth` used for your bandages and notes.

It makes the **Screw Press** the most important non-chemical machine in the lab!

## quick summary -----------------

Based on our discussion, here is the refined, "PlainTech" list of states. Using the **Generic ID + Data** approach, you only need to register these **11 base items**:

### 🌿 The Botanical State Machine

| State Item ID    | Data Component (NBT) | Process / Machine         | Use Case                              |
| :--------------- | :------------------- | :------------------------ | :------------------------------------ |
| `herb_fresh`     | `plant_id`           | **Harvesting**            | Short-lived raw material.             |
| `herb_dried`     | `plant_id`           | **Drying Rack**           | Stable storage; ingredient base.      |
| `herb_powder`    | `plant_id`           | **Mortar & Pestle**       | High-surface area; used in all logic. |
| `herb_mash`      | `plant_id`           | **Mortar** (+ Water)      | Immediate, unstable topical fix.      |
| `herb_tea`       | `plant_id`           | **Cauldron** (+ Water)    | Weak, short-term internal buff.       |
| `herb_decoction` | `plant_id`           | **Cauldron** (Boiled)     | Concentrated, shelf-stable liquid.    |
| `herb_essence`   | `plant_id`           | **Alembic** (+ Spirit)    | "Eye-drop" potency; no spoilage.      |
| `herb_salve`     | `plant_id`           | **Cauldron** (+ Lard)     | Multidose topical "Crock."            |
| `herb_bandage`   | `plant_id`           | **Crafting** (+ Gauze)    | Mobile, slow-release healing.         |
| `herb_plaster`   | `plant_id`           | **Crafting** (+ Resin)    | Long-term "Set and Forget" seal.      |
| `herb_pill`      | `plant_id`           | **Screw Press** (+ Sugar) | Stackable (64) portable doses.        |

---

### 🛠️ Why this is "Clean" Code:

Since you’re managing this via ECS/NBT, your logic for a **Valerian Bandage** looks like this:

- **Item:** `herbalis:herb_bandage`
- **NBT:** `{plant_id: "valerian", durability: 100, potency: 1.5}`

### 🎨 The "One-Texture" Rule

You only need **one `.png` per State ID**.

1. Your renderer checks the `plant_id`.
2. It looks up the `color_hex` in your `plants.json`.
3. It applies that tint to the mask of the generic texture.

This keeps your assets folder tiny and your performance high. Does this list cover everything you need for the "Apothecary" loop, or did we miss a step in the transformation?

## -----------------

## -----------------
