package commands

import requestManager


class Exit: AbstractCommand()  {

    override fun execute(str2: String): CommandResult {
        return requestManager.exitRequest(str2)
    }

}
