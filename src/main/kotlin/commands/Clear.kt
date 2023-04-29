package commands

import requestManager

class Clear: AbstractCommand()  {

    override fun execute(str2: String): CommandResult {
          return requestManager.clearRequest(str2)
    }

}