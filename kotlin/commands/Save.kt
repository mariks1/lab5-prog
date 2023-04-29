package commands


import requestManager


class Save: AbstractCommand() {

    override fun execute(str2: String): CommandResult {
        return requestManager.saveRequest(str2)
    }
}
