package data

import kotlinx.serialization.Serializable

@Serializable
data class Discipline(private var name: String,
                      private var practiceHours: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Discipline

        if (name != other.name) return false
        if (practiceHours != other.practiceHours) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + practiceHours.hashCode()
        return result
    }

    override fun toString(): String {
        return "Discipline(name='$name', practiceHours=$practiceHours)"
    }

}

