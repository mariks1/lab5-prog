package commands

import requestManager

class PrintDifficulty: AbstractCommand() {


    override fun execute(str2: String): CommandResult {
        return requestManager.printFieldAscindingDifficultyRequest(str2)
    }
}
