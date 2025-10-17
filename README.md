# Roleplay Utils
A mod for various roleplay elements for minecraft

# Commands
## /rename
`/rename <name>`  
renames an item to the given name, leave empty to reset the item name.

## /renamecustom
`/rename <name>`  
renames an item to the given name using the CUSTOM_NAME component, allowing the name to appear in item frames

## /lore
`/lore add <lore>`  
Add a new lore line to the end of the item's lore.

`/lore edit <line number> <lore>`  
Change a lore line by its number.

`/lore remove <line number>`  
Remove a lore line from its number.

`/lore reset`  
remove all lore from an item

## /glow
`/glow [glow]`  
Adds or removes glow from an item (defaults to adding glow).

## /model
`/model set`  
Set the model of an item using an identifier (e.g. `minecraft:diamond`). Note that this can grab from a custom
namespace in a resource pack.

`/model reset`  
Reset the item model to its default

## /bulkcopy
copies elements added to item in main hand to all items of the same type if shulkers in the players inventory

*Note: this is a bit finicky, you may need to run the command more than once and the box may need to be placed for
the items to update*
