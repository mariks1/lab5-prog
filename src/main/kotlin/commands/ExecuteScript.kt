package commands

import requestManager

class ExecuteScript: AbstractCommand() {

    override fun execute(str2: String): CommandResult {
        return requestManager.filterByDifficultyRequest(str2)
    }
}