package commands

import requestManager

class Insert: AbstractCommand() {

    override fun execute(str2: String): CommandResult {
        return requestManager.insertRequest(str2)
        }
}


