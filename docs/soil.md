# Soil & Cultivation

soil isn't just a platform for plants; it is a chemical system. every herb has a "preferred" environment. planting a high-acid herb in alkaline soil will cause it to wilt, turn yellow, or drop [Compost](soil.md#Compost) instead of a harvest.

## Soil Types

different biomes provide different base textures. you can harvest these or mix them in a [Basin](machines.md#Basin) to create custom soil.

- **Sandy Soil** -- found in **Deserts** or **Beaches**. high drainage, low nutrients. water evaporates quickly.
- **Clay Soil** -- found in **Swamps** or **Jungles**. holds water too well (risk of root rot). needs to be mixed with **Sand** to be viable for most herbs.
- **Loamy Soil** -- found in **Forests**. the balanced "all-rounder" soil.
- **Chernozem** -- high-performance black earth found in **Meadows**. has the highest natural nutrient density.

## Soil Stats

each soil block tracks hidden variables that affect the tick-rate and yield of your crops:

1.  **pH Level** -- ranges from 4 (acidic) to 9 (alkaline). most plants prefer a neutral 6-7.
2.  **Nutrients** -- the "fuel" for growth. depleted every time a plant reaches a new growth stage.
3.  **Compaction** -- walking on farmland "packs" the dirt. **Packed Soil** stops growth entirely. use a **Hoe** to till or a **Pitchfork** to aerate without destroying the plant.
4.  **Drainage** -- determines how long the soil stays "moist" after rain or watering.
5.

## Climate & Temperature

plants are sensitive to the ambient temperature of the biome. there are 5 distinct states:

- **Freezing** -- (e.g., **Ice Spikes**, **Cold Oceans**). plants will turn into **Frozen Stalks** and break unless kept in a heated greenhouse. some exotic plants may like this climate.
- **Cold** -- (e.g., **Taiga**, **Snowy Tundra**, **Underground**). growth is 50% slower. only hardy herbs (like **Pine**) grow normally here.
- **Temperate** -- (e.g., **Plains**, **Forest**). the baseline for most medicinal plants.
- **Hot** -- (e.g., **Jungle**, **Savanna**). growth is 25% faster, but soil dries out twice as quickly.
- **Scorching** -- (e.g., **Desert**, **Nether**). plants will wilt and turn into **Dead Bushes** unless they are shaded and heavily watered. some exotic plants may like this climate.

### Greenhouse Engineering

to grow plants outside their natural climate, you must build a functional greenhouse.

- **insulation:** the area must be enclosed with glass. this raises the internal temperature by +1 level (e.g., **Cold** becomes **Temperate**).
- **artificial heating:** placing a lit **Furnace** or a **Campfire** inside a greenhouse raises the temperature by another +1 level. this allows you to grow tropical herbs in the middle of a winter biome.

## Sunlight

most medicinal herbs require high-intensity light to grow. if the sky light level at the plant is below 14, growth will slow, and the plant will eventually drop **Compost** instead of a harvest.

### Light Shafts

for underground labs, you must dig vertical shafts to the surface to bring in natural light. you can cap these shafts with **Glass** to prevent heat loss, turning your underground room into a subterranean greenhouse (although by default your underground room is also technically a greenhouse)

### Reflectors & Mirror Bouncing

if you cannot dig a direct shaft over your garden, you can use **Polished Reflectors** to redirect light around corners.

- **mechanic:** place a reflector (made from polished **Copper** or **Silver**) at a 45-degree angle to bounce light from a vertical shaft into a horizontal room.
- **efficiency:** each "bounce" reduces the light level. **Silver Reflectors** are more efficient and allow for longer light paths, while **Copper Reflectors** are cheaper but lose intensity faster.

### Chemical Lighting

as a last resort for deep bunkers, you can craft **Phosphorus Lamps**.

- **logic:** these are glass globes filled with white phosphorus extracted from **Bone Meal** in the [Alembic](machines.md#Alembic).
- **maintenance:** they provide a sky-light equivalent of 15 in a small radius but slowly consume their fuel. this is the only way to grow herbs in total darkness without surface access.

## Buffering

You can keep fertilizers, alkalis or acids on the soil, and it will pick up only how much is needed by the plant and soil. so, your herbs wont need constant attention.

---

## Items

### pH Indicator

first, boil **Beetroot** in a **Basin** to extract the natural pigments. then, crush **Paper** into **Paper Strips** using the **Mortar & Pestle**. finally, soak those strips in the beetroot juice to get **pH Indicators**.

this can be used to monitor the pH of your soil by right-clicking on it. the strip will change color (red for acid, yellow/green for neutral, purple for alkali) so your plants won't rot.

although, this is not always needed. you can always look at the plant visually and tell what's wrong.

### Fertilizer

regular **Bone Meal** cannot be used to "insta-grow" herbalis plants. instead, you must craft **Fertilizer** using **Compost**, **Bone Meal**, and **Ash**. applying this to soil adds a "Fertilized" state that significantly boosts growth speed and final yield without breaking the realism.

### Compost

this is the base organic matter for all agriculture. it is obtained by letting your herbs decompose (their potency timer hits zero) or by using a vanilla composter. it is high in nitrogen, which is the primary driver of plant height and leaf health.

### Ash

when wooden structures or logs burn, they release **Ash**. these fall as layers (similar to snow) on the ground nearby. you can shovel these up to get **Ash** items.

- **use:** it is your primary alkali source. adding **Ash** to a soil block raises its pH.

### Sulfur

found as yellow toxic pools on the surface or as crystalline patches in deep caves.

- **use:** its primary agricultural use is acidifying soil (lowering pH). it is also used in specific high-level chemical recipes in the [Alembic](machines.md#Alembic).

### Pine Needles

harvested from **Spruce** or **Dark Oak** leaves.

- **use:** a "soft" acidifier. while **Sulfur** drops pH rapidly, **Pine Needles** can be mixed into the soil for a slow-release acidic effect.

---

## Cultivation Logic

| action                            | result              | logic                                            |
| :-------------------------------- | :------------------ | :----------------------------------------------- |
| add **Ash**                       | increase pH         | makes the soil more alkaline.                    |
| add **Sulfur** / **Pine Needles** | decrease pH         | makes the soil more acidic.                      |
| add **Sand**                      | increase drainage   | prevents water-logging in clay-heavy biomes.     |
| walk on plant                     | increase compaction | turns soil into **Packed Soil**, halting growth. |
| apply **Fertilizer**              | add nutrients       | allows the plant to grow faster and yield more.  |

if a plant's environmental needs aren't met (e.g. pH is too high), it will enter a "Stunted" state. it won't die, but it will never reach the harvest stage until the soil is fixed.
