package commands

import requestManager


class RemoveGreater: AbstractCommand() {

    override fun execute(str2: String): CommandResult {
        return requestManager.removeGreaterRequest(str2)
    }


}