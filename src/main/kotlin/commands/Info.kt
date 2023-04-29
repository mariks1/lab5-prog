package commands

import requestManager

class Info: AbstractCommand() {

    override fun execute(str2: String): CommandResult {
        return requestManager.infoRequest(str2)
    }
}



