package example.models

import java.util.UUID
import upickle.default.{macroRW, ReadWriter as RW}

case class User(
    userUuid: UUID,
    userName: String,
    age: Option[Int],
    favouriteSongUuid: Option[UUID]
)

object User:
  given RW[User] = macroRW
