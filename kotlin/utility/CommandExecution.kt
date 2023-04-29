package utility

import commands.*
import request


class CommandExecution() {
    private val commands = LinkedHashMap<String, AbstractCommand>()

    init {
        initMap()
    }


    private fun initMap() {
        commands["print_difficulty"] = PrintDifficulty()
        commands["help"] = Help()
        commands["show"] = Show()
        commands["exit"] = Exit()
        commands["clear"] = Clear()
        commands["insert"] = Insert()
        commands["filter_by_difficulty"] = FilterByDifficulty()
        commands["remove_key"] = RemoveKey()
        commands["info"] = Info()
        commands["remove_lower_key"] = RemoveLowerKey()
        commands["update"] = UpdateId()
        commands["execute_script"] = ExecuteScript()
        commands["remove_greater"] = RemoveGreater()
        commands["group_counting_by_id"] = GroupCountingByID()
        commands["save"] = Save()
        commands["print_field_ascending_difficulty"] = PrintDifficulty()
    }

    fun executeCommand(str1: String, str2: String): Boolean? {
        if (checkCommand(str1)) {
            println("Используется команда $str1")
            commands[str1]?.execute((str2 + " " + request.askForArgument(str1)).trim()).let {
                if (it?.message != null) println(it.message)
                return it?.commandComplicated
            }
        } else {
            println("Неправильная команда!")
            return false
        }
    }



    private fun checkCommand(str1: String): Boolean {
        if (str1 in commands) {
            return true
        }
        return false
    }

}