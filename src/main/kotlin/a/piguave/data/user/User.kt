package a.piguave.data.user

import org.bson.BsonDateTime
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class User(
    val name: String,
    val bio: String,
    val birthdate: BsonDateTime,
    val gender: Gender,
    val interestedIn: Interest,
    val liked: List<String>,
    val passed: List<String>,
    val pictures: List<String>,
    @BsonId val id: ObjectId = ObjectId()
)
enum class Gender{ MALE, FEMALE }
enum class Interest { MEN, WOMEN, BOTH }