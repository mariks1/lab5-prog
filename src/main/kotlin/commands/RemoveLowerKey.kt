package commands

import requestManager


class RemoveLowerKey: AbstractCommand() {

    override fun execute(str2: String): CommandResult {
        return requestManager.removeLowerKeyRequest(str2)
    }

}
