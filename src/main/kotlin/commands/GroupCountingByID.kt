package commands

import requestManager


class GroupCountingByID: AbstractCommand()  {

    override fun execute(str2: String): CommandResult {
        return requestManager.groupCountingByIdRequest(str2)
    }
}