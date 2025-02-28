package example.models

import java.util.UUID
import upickle.default.{macroRW, ReadWriter as RW}

case class User(
                userName: String,
                age: Option[String],
                favouriteSongUuid: Option[String]
               )

object User:
  given RW[User] = macroRW
