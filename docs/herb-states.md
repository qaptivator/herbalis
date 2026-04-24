# Herb States

there will be many herbs, so we must make a universal registering system for it to simplify recipes and later JEI integration

here are all of the states which will be used in the game:

- **raw** -- the item which is dropped from the plants (blocks). stacks to 8. looses potency quickly and eventually decomposes into [Compost](soil.md#Compost)
- **dried** --

## chamomile example

| State    | Name                 | Crafting Logic    | In-Game Purpose                                                            |
| -------- | -------------------- | ----------------- | -------------------------------------------------------------------------- |
| Raw      | Fresh Chamomile      | Harvested         | Emergency sleep (reset 10% of Insomnia).                                   |
| Dried    | Dried Chamomile      | Drying Rack       | Bulk storage; base for all recipes.                                        |
| Dust     | Chamomile Powder     | Mortar & Pestle   | Ingredient for pills/pastilles.                                            |
| Infusion | Chamomile Tea        | Dried + Hot Water | Relax: Removes "Tremors" (aim shake) for 5 mins.                           |
| Salve    | Chamomile Ointment   | Powder + Lard     | Skin Care: Heals "Burn" damage from Magma/Fire.                            |
| Tincture | Essence of Chamomile | Dried + Spirit    | Heavy Sleep: Instantly skip to morning without a bed.                      |
| Poultice | Chamomile Wrap       | Dried + Bandage   | Reduction: Reduces the "Toxicity" of other drugs currently in your system. |
| Pastille | Chamomile Drop       | Powder + Sugar    | Passive: Very slowly lowers the Insomnia timer over 20 mins.               |
