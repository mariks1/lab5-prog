package commands

import requestManager


class UpdateId: AbstractCommand() {


    override fun execute(str2: String): CommandResult {
        return requestManager.updateIdRequest(str2)
    }
}


