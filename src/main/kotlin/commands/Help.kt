package commands

import requestManager

class Help: AbstractCommand(){
    override fun execute(str2: String): CommandResult {
        return requestManager.helpRequest(str2)
    }
}