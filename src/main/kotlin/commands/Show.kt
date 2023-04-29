package commands

import requestManager


class Show: AbstractCommand() {


    override fun execute(str2: String): CommandResult {
        return requestManager.showRequest(str2)
    }
}
