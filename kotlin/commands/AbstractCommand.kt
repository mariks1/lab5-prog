package commands

import utility.CollectionManager


data class CommandResult(
    val commandComplicated: Boolean,
    val message: String? = null
)


abstract class AbstractCommand {

    abstract fun execute(str2: String): CommandResult

}