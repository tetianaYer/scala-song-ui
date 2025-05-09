package example.models

import upickle.default.{macroRW, ReadWriter as RW}

case class User(userUuid: Option[String] = None,
                userName: String,
                age: Option[String],
                favouriteSongUuid: Option[String]
               )

object User:
  given RW[User] = macroRW
