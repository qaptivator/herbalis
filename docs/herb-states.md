# Herb States

there will be many herbs, so we must make a universal registering system for it to simplify recipes and later jei integration. instead of 200+ unique items, we use generic items with an nbt/component for the plant id.

here are all of the states which will be used in the game:

- **raw** -- the item which is dropped from the plants (blocks). stacks to 8. looses potency quickly and eventually decomposes into [compost](soil.md#compost).

  - _template:_ `Fresh {}`
  - _logic:_ pure harvesting.

- **dried** -- result of leaving raw herbs on a [drying rack](machines.md#drying-rack). stops the decomposition timer. can be stacked to 64.

  - _template:_ `Dried {}`
  - _logic:_ time + air.

- **powder** -- the fine dust made by grinding dried herbs in a [mortar](machines.md#mortar). the primary base for most chemical extractions.

  - _template:_ `{} powder`
  - _logic:_ manual grinding.

- **mash** -- a wet, crushed clump made from fresh herbs. high potency but rots in 10 minutes. used for immediate field treatment.

  - _template:_ `{} mash`
  - _logic:_ mortar + water + fresh herb.

- **tea** -- weak liquid extraction. fast to make but becomes "stale" after one day.

  - _template:_ `{} tea`
  - _logic:_ [cauldron](machines.md#cauldron) + water + powder + heat.

- **decoction** -- a stronger, concentrated liquid. shelf-stable and stackable in small vials. higher [toxicity](player.md#toxicity) than tea.

  - _template:_ `{} decoction`
  - _logic:_ cauldron + extended boiling.

- **essence** -- the pure chemical essence. near-instant effect and never spoils. delivers in drops.

  - _template:_ `Essence of {}`
  - _logic:_ [alembic](machines.md#alembic) + spirit + powder.

- **salve** -- a thick ointment for external use. comes in a multi-dose crock.

  - _template:_ `{} salve`
  - _logic:_ cauldron + lard + powder. see [chemicals.md](chemicals.md#lard) for lipid info.

- **bandage** -- a strip of cloth impregnated with medicine. provides slow-release healing while moving.

  - _template:_ `{} bandage`
  - _logic:_ crafting + cloth + mash/powder.

- **plaster** -- a "set and forget" seal that stays on the player until fully healed.

  - _template:_ `{} plaster`
  - _logic:_ crafting + cloth + salve + [resin](chemicals.md#resin).

- **pill** -- tiny, high-density sugar drops. low potency per unit but allows for "buff stacking" without filling the inventory.
  - _template:_ `{} pastille`
  - _logic:_ [screw press](machines.md#screw-press) + sugar + powder.

## Chamomile Example

| state     | name                 | crafting logic    | in-game purpose                                              |
| --------- | -------------------- | ----------------- | ------------------------------------------------------------ |
| raw       | fresh chamomile      | harvested         | emergency sleep (reset 10% of insomnia).                     |
| dried     | dried chamomile      | drying rack       | bulk storage; base for all recipes.                          |
| powder    | chamomile powder     | mortar & pestle   | ingredient for pills/pastilles.                              |
| tea       | chamomile tea        | dried + hot water | relax: removes "tremors" (aim shake) for 5 mins.             |
| decoction | chamomile decoction  | powder + boil     | strong relax: cures "panic" status.                          |
| salve     | chamomile ointment   | powder + lard     | skin care: heals "burn" damage from magma/fire.              |
| essence   | essence of chamomile | dried + spirit    | heavy sleep: instantly skip to morning without a bed.        |
| bandage   | chamomile wrap       | powder + cloth    | reduction: slowly lowers toxicity levels.                    |
| plaster   | chamomile plaster    | salve + resin     | long-term: grants "rested" buff after 10 mins.               |
| pill      | chamomile drop       | powder + sugar    | passive: very slowly lowers the insomnia timer over 20 mins. |

## Technical Implementation

for rendering, we use a single texture per state. the `plant_id` component carries a hex color code (e.g., `#d4af37` for valerian). the renderer applies this tint to the "liquid" or "herb" mask of the item model.

- **ids:** `herbalis:herb_tea`, `herbalis:herb_powder`, etc.
- **nbt:** `{plant:"chamomile", potency:1.0, expiration:12000}`

this keeps the registry clean and allows us to add new herbs via simple json files without writing new classes.
