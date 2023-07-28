package a.piguave.data.user

import org.bson.codecs.pojo.annotations.BsonId
import java.time.LocalDate

data class User(
    @BsonId val id: String,
    val name: String,
    val bio: String,
    val birthdate: LocalDate,
    val gender: Gender,
    val interestedIn: Interest,
    val liked: List<String>,
    val passed: List<String>,
    val pictures: List<String>,
)
enum class Gender{ MALE, FEMALE;
    fun toInterest(): Interest = when(this){
        MALE -> Interest.MEN
        FEMALE -> Interest.WOMEN
    }
}
enum class Interest { MEN, WOMEN, BOTH;
    fun toGender(): Gender? = when(this){
        MEN -> Gender.MALE
        WOMEN -> Gender.FEMALE
        BOTH -> null
    }
}