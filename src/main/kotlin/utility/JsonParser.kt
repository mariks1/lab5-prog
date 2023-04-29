package utility



import data.LabWork
import java.io.FileReader
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.io.FileNotFoundException
import java.io.FileWriter
import java.io.IOException
import java.util.Hashtable


class FileManager(private var name: String) {

    fun fileWrite(collection: Hashtable<CollectionKey,LabWork>) {

        try {
            val list = ArrayList(collection.values)
            FileWriter(name).use { it.write(Json.encodeToString(list)) }
        } catch (e: IOException) {
            println("Невозможно сохранить коллекцию")
        }
    }


    fun fileRead(): Hashtable<CollectionKey,LabWork> {
        var fileReader: FileReader
        while (true) {
            try {
                fileReader = FileReader(name)
                break
            } catch (e: FileNotFoundException) {
                println("Файла с таким названием не существует!")
            }
        }
        var input = ""
        var ch: Int
        do {
            ch = fileReader.read()
            if (ch == -1) {
                break
            }
            input += ch.toChar()
        } while (true)
        var tcollectionKey = 1
        val temp: List<LabWork> = Json.decodeFromString<List<LabWork>>(input)
        val collection: Hashtable<CollectionKey, LabWork> = Hashtable()
        for (elem in temp) {
            val collectionKey: CollectionKey = CollectionKey(tcollectionKey)
            tcollectionKey += 1
            collection[collectionKey] = elem
        }
        return collection
    }


    private fun setName(name: String) {
        this.name = name
    }
}






















