package commands

import commandReader

class ExecuteScript: AbstractCommand() {

    override fun execute(str2: String): CommandResult {
        commandReader.scriptMode(str2)
        return CommandResult(true)
    }
}