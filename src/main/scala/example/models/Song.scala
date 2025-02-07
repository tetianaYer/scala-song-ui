package example.models

import upickle.default.{macroRW, ReadWriter as RW}

case class Song(length: String, title: String, artist: String)

object Song:
  given RW[Song] = macroRW


case class SongResponse(text: String)

object SongResponse:
  given RW[SongResponse] = macroRW



