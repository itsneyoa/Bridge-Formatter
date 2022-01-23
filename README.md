# Chat Bridge Formatter

hi

This works best with Senither's [Hypixel Discord Chat Bridge](https://github.com/Senither/hypixel-discord-chat-bridge) - while it may work with others, I can't confirm that it will. Hopefully in an update I'll add a way to modify the detection regex.

---

## Building from source

Run `gradlew build`, output will be in `build/libs/BridgeFormatter-<version>.jar`

---

## To do

- [ ] Add a way of setting colour codes in the config instead of using the `&` or `§` codes (`#yellow` would be changed to `&e` when set in the config)
- [ ] Add advanced options for setting the regex of chat message detection
- [ ] Write the wiki
- [ ] Improve building from source and contributing section here
- [ ] Join/leave message formatting
