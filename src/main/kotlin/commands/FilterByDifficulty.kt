package commands

import requestManager

class FilterByDifficulty: AbstractCommand() {

    override fun execute(str2: String): CommandResult {
        return requestManager.filterByDifficultyRequest(str2)
    }
}
