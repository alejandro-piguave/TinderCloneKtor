package a.piguave.rest.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object LocalDateAsString: KSerializer<LocalDate> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("LocalDate", PrimitiveKind.STRING)

    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    override fun deserialize(decoder: Decoder): LocalDate {
        val string = decoder.decodeString()
        return LocalDate.parse(string, formatter)
    }

    override fun serialize(encoder: Encoder, value: LocalDate) {
        val string = formatter.format(value)
        encoder.encodeString(string)
    }

}