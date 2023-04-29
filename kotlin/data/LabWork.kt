package data

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.Date

object DateSerializer: KSerializer<Date> {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("Date", PrimitiveKind.LONG)

    override fun deserialize(decoder: Decoder): Date {
        return Date(decoder.decodeLong())
    }

    override fun serialize(encoder: Encoder, value: Date) {
        encoder.encodeLong(value.time)
    }

}



@Serializable
data class LabWork(private var id: Long,
                   private var name: String,
                   private var coordinates: Coordinates,
                   @Serializable(DateSerializer::class)
                   private var creationDate: Date,
                   private var minimalPoint: Int,
                   private var description: String,
                   private var tunedInWorks: Long,
                   private var difficulty: Difficulty,
                   private var discipline: Discipline): Comparable<LabWork> {
    fun getId(): Long {
        return id
    }

    fun getName(): String {
        return name
    }

    fun getCoordinates(): Coordinates {
        return coordinates
    }

    fun getMinimalPoint(): Int {
        return minimalPoint
    }

    fun getDescription(): String {
        return description
    }

    fun getTunedInWorks(): Long {
        return tunedInWorks
    }

    fun getDifficulty(): Difficulty {
        return difficulty
    }

    fun getDiscipline(): Discipline {
        return discipline
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LabWork

        if (id != other.id) return false
        if (name != other.name) return false
        if (coordinates != other.coordinates) return false
        if (minimalPoint != other.minimalPoint) return false
        if (description != other.description) return false
        if (tunedInWorks != other.tunedInWorks) return false
        if (difficulty != other.difficulty) return false
        if (discipline != other.discipline) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + coordinates.hashCode()
        result = 31 * result + minimalPoint
        result = 31 * result + description.hashCode()
        result = 31 * result + tunedInWorks.hashCode()
        result = 31 * result + difficulty.hashCode()
        result = 31 * result + discipline.hashCode()
        return result
    }

    override fun toString(): String {
        return "LabWork(id=$id, name='$name', coordinates=$coordinates, minimalPoint=$minimalPoint, description='$description', tunedInWorks=$tunedInWorks, difficulty=$difficulty, discipline=$discipline)"
    }



    override fun compareTo(other: LabWork): Int {
        return this.id.compareTo(other.getId())
    }

    fun setID(id: Long) {
        this.id = id
    }

}




