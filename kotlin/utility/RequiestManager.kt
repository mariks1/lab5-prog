package utility

import collection
import commands.*
import data.Coordinates
import data.Difficulty
import data.Discipline
import data.LabWork
import java.time.Instant
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.system.exitProcess

class RequestManager(private val collectionManager: CollectionManager,
                     private var fileManager: FileManager) {

    fun showRequest(str: String): CommandResult {
        return if (str.isEmpty()) {
            CommandResult(true, collectionManager.toString())
        } else CommandResult(false,"error: После show не должно быть аргументов")
    }

    fun infoRequest(str: String): CommandResult {
        return if (str.isEmpty()) {
            CommandResult(true,"Тип коллекции - HashTable" + "\n" + "Дата инициализации - " + collectionManager.getInitTime() + "\n" + "Количестов элементов - " +  collectionManager.getAllElements().size)
        } else CommandResult(false,"error: После info не должно быть аргументов")
    }

    fun helpRequest(str: String): CommandResult {
        return if (str.isEmpty()) {
            CommandResult(true, "help : вывести справку по доступным командам" + "\n" +
                    "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)" + "\n" +
                    "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении" + "\n" +
                    "insert null {element} : добавить новый элемент с заданным ключом" + "\n" +
                    "update id {element} : обновить значение элемента коллекции, id которого равен заданному" + "\n" +
                    "remove_key null : удалить элемент из коллекции по его ключу" + "\n" +
                    "clear : очистить коллекцию" + "\n" +
                    "save : сохранить коллекцию в файл" + "\n" +
                    "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме." + "\n" +
                    "exit : завершить программу (без сохранения в файл)" + "\n" +
                    "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный" + "\n" +
                    "remove_lower_key null : удалить из коллекции все элементы, ключ которых меньше, чем заданный"  + "\n" +
                    "group_counting_by_id : сгруппировать элементы коллекции по значению поля id, вывести количество элементов в каждой группе" + "\n" +
                    "filter_by_difficulty difficulty : вывести элементы, значение поля difficulty которых равно заданному" + "\n" +
                    "print_field_ascending_difficulty : вывести значения поля difficulty всех элементов в порядке возрастания")
        } else CommandResult(false, "error: После help не должно быть аргументов")
    }

    fun clearRequest(str: String): CommandResult {
        return if (str.isEmpty()) {
            if (collectionManager.isEmpty()) {
                CommandResult(false, "error: Коллекция пуста!")
            } else {
                collectionManager.clear()
                CommandResult(true, "Коллекция очищена!")
            }
        } else CommandResult(false, "error: После clear не должно быть аргументов")
    }

    fun exitRequest(str: String): CommandResult {
        if (str.isEmpty()) {
            exitProcess(0)
        } else return CommandResult(false,"error: После exit не должно быть аргументов")
    }

    fun groupCountingByIdRequest(str: String): CommandResult {
         if (str.isEmpty()) {
            val group1: ArrayList<CollectionKey> = ArrayList(); val group2: ArrayList<CollectionKey> = ArrayList()
            for (key in collectionManager.getAllKeys()) {
                if ((collectionManager[key]?.getId()?.rem(2))?.toInt() == 0) {
                    group1.add(key)
                } else {
                    group2.add(key)
                }
            }
            return CommandResult(true,"В коллекции ${group1.size} элементов с четным id, ${group2.size} с нечётным")
        } else {
            return CommandResult(false,"error: После group_counting_by_id не должно быть аргументов")
        }
    }

    fun insertRequest(str: String): CommandResult {
        val array = str.split(Regex(" ")).toTypedArray()
        if(array.size == 10) {
            val id = collectionManager.generateId()
            var i = 0
            val key: CollectionKey = CollectionKey(array[i++].toInt())
            val name = array[i++]
            val x = array[i++].toInt()
            val y = array[i++].toDouble()
            val minimalPoint = array[i++].toInt()
            val description = array[i++]
            val tunedInWork = array[i++].toLong()
            val difficulty = Difficulty.valueOf(array[i++])
            val disciplineName = array[i++]
            val practiceHours = array[i].toInt()
            val cord = Coordinates(x,y)
            val discipline = Discipline(disciplineName,practiceHours)
            val labWork: LabWork = LabWork(id,name,cord,
                Date.from(Instant.now()),minimalPoint,description,tunedInWork,difficulty,discipline)
            collectionManager.addElem(key,labWork)
            return CommandResult(true,"Элемент был добавлен!")
        } else return CommandResult(false, "error: Неправильный ввод данных")
    }




    fun updateIdRequest(str: String): CommandResult {
        val array = str.split(Regex(" ")).toTypedArray()
        if (array.size == 10) {
            var i = 0
            val id: Long = array[i++].toLong()
            val key: CollectionKey = collectionManager.getKeyWithId(id)
            if (key.number == 0) {
                return CommandResult(false, "В коллекции нет элемента с заданным ключем!")
            }
            val name = array[i++]
            val x = array[i++].toInt()
            val y = array[i++].toDouble()
            val minimalPoint = array[i++].toInt()
            val description = array[i++]
            val tunedInWork = array[i++].toLong()
            val difficulty = Difficulty.valueOf(array[i++])
            val disciplineName = array[i++]
            val practiceHours = array[i].toInt()
            val cord = Coordinates(x, y)
            val discipline = Discipline(disciplineName, practiceHours)
            val labWork: LabWork =
                LabWork(id, name, cord,Date.from(Instant.now()), minimalPoint, description, tunedInWork, difficulty, discipline)
            collection.addElem(key, labWork)
            collection.setId(key, id)
            return CommandResult(true, "Элемент был обновлен!")
        } else return CommandResult(false, "error: Неправильный ввод данных")
    }

    fun removeKeyRequest(str: String): CommandResult {
        if (str.split(Regex(" ")).toTypedArray().size == 1) {
            val key = CollectionKey(str.toInt())
            if (collection.getAllKeys().contains(key)) {
                collection.removeElem(key)
                return CommandResult(true,"Элемент с ключем $key был удален")
            }
            return CommandResult(false,"В коллекции нет такого элемента!")
        } else return CommandResult(false, "error: Неправильный ввод данных")
    }

    fun removeGreaterRequest(str: String): CommandResult {
        if (str.split(Regex(" ")).toTypedArray().size == 1) {
            val minimalPoint = str.toInt()
            val removeArray: ArrayList<CollectionKey> = ArrayList()
            for (key in collection.getAllKeys()) {
                if (collection[key]?.let { collection[key]?.getMinimalPoint() }!! < minimalPoint) {
                    removeArray.add(key)
                }
            }
            for (key in removeArray) {
                collection.removeElem(key)
            }
            return CommandResult(true,"Все лабораторные работы с баллом ниже введенного были удалены!")
        } else return CommandResult(false, "error: Неправильный ввод данных")
    }

    fun removeLowerKeyRequest(str: String): CommandResult {
        if (str.split(Regex(" ")).toTypedArray().size == 1) {
            val number: Int = str.toInt()
            val array = collectionManager.getAllKeys()
            val testArray: ArrayList<CollectionKey> = ArrayList()
            for (elem in array) {
                if (elem.number < number) {
                    testArray.add(elem)
                }
            }
            for (elem in testArray) {
                collection.removeElem(elem)
            }
            return CommandResult(true,"Все элементы с ключем меньше $number были удалены")
        } else return CommandResult(false, "error: Неправильный ввод данных")
    }

    fun filterByDifficultyRequest(str: String): CommandResult {
        if (str.split(Regex(" ")).toTypedArray().size == 1) {
            val diff: Difficulty = Difficulty.valueOf(str.uppercase())
            var str1 = ""
            for (labWork in collection.getAllElements()) {
                if (labWork.getDifficulty() == diff) {
                    str1 += labWork.toString() + "\n"
                }
            }
            return if (str1 == "") {
                CommandResult(false,"В коллекции нет элементов со сложностью $diff!")
            } else {
                str1 = str1.dropLast(1)
                CommandResult(true,str1)
            }
        } else return CommandResult(false, "error: Неправильный ввод данных")
    }

    fun printFieldAscindingDifficultyRequest(str: String): CommandResult {
        return if (str.isEmpty()) {
            val n: HashMap<CollectionKey, Difficulty?> = collection.getAllDifficulty()
            val resultMap = n.entries.sortedBy { it.value }.associate { it.toPair() }
            println(resultMap)
            CommandResult(true, "Лабораторные работы выведены в порядке возрастания сложности")
        } else CommandResult(false, "error: После group_counting_by_id не должно быть аргументов")
    }

    fun saveRequest(str: String): CommandResult {
        return if (str.isEmpty()) {
            fileManager.fileWrite(collectionManager.getLabworkCollection())
            CommandResult(true,"Коллекция была сохранена")
        } else CommandResult(false,"error: После save не должно быть аргументов")
}


}