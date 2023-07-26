package a.piguave.rest.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.OffsetDateTime

object OffsetDateTimeAsString: KSerializer<OffsetDateTime> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("OffsetDateTime", PrimitiveKind.STRING)
    override fun deserialize(decoder: Decoder): OffsetDateTime {
        val string = decoder.decodeString()
        return OffsetDateTime.parse(string)
    }

    override fun serialize(encoder: Encoder, value: OffsetDateTime) {
        val string = value.toString()
        encoder.encodeString(string)
    }

}