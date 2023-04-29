package commands


import requestManager

class RemoveKey: AbstractCommand() {


    override fun execute(str2: String): CommandResult {
        return requestManager.removeKeyRequest(str2)
        }
}
