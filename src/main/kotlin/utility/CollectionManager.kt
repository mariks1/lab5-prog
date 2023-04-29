package utility

import data.Difficulty
import data.LabWork
import java.time.LocalDate
import java.util.Hashtable


class CollectionManager(private var collection: Hashtable<CollectionKey,LabWork>) {
    companion object {
        val initTime: LocalDate = LocalDate.now()

    }


    fun getLabworkCollection(): Hashtable<CollectionKey,LabWork> {
        return collection
    }

    fun addElem(key: CollectionKey,labWork: LabWork) {
        collection[key] = labWork
    }
    fun getAllKeys(): MutableSet<CollectionKey> {
        return collection.keys
    }

    fun clear(){
        collection.clear()
    }
    fun getId(key:CollectionKey): Long? {
        return collection[key]?.getId()
    }

    fun setId(key: CollectionKey,id: Long) {
        collection[key]?.setID(id)
    }

    fun getKeyWithId(id: Long): CollectionKey {
        for (key in collection.keys) {
            if (collection[key]?.getId() == id) {
                return key
            }
        }
        return CollectionKey(0)
    }
    fun removeElem(key: CollectionKey) {
        collection.remove(key)
    }

    fun getAveragePoints(): Double {
        return (collection.values.sumOf { labWork -> labWork.getMinimalPoint()} / collection.size.toDouble() )
    }


    fun getAllDifficulty(): HashMap<CollectionKey,Difficulty?> {
        val temp: HashMap<CollectionKey,Difficulty?> = HashMap()
        for (key in collection.keys) {
            temp[key] = collection[key]?.getDifficulty()
        }
        return temp
    }

    fun getAllElements(): MutableCollection<LabWork> {
        return collection.values
    }

    fun generateId(): Long {
        return getMaxId() + 1
    }

    fun getMaxId(): Long {
        var max: Long = 0
        for (elem in collection.values) {
            if (max < elem.getId()) {
                max = elem.getId()
            }
        }
        return max
    }

    fun isEmpty(): Boolean {
        return collection.isEmpty
    }
    fun getInitTime(): LocalDate {
        return initTime
    }

    operator fun set(key: CollectionKey, value: LabWork) {
        collection[key] = value
    }

    operator fun get(key: CollectionKey): LabWork? {
        return collection[key]
    }


    override fun toString(): String {
        if (collection.isEmpty) return "Коллекция пустая"

        val collection1 = getAllElements()
        var string = ""
        for (labwork in collection1) {
            string += labwork
            if (collection1.indexOf(labwork) != collection1.size - 1) {string += "\n"}
        }
        return string
    }
}