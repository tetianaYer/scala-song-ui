package example

import com.raquo.airstream.ownership.Owner
import com.raquo.airstream.web.FetchOptions
import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.api.features.unitArrows
import com.raquo.laminar.receivers.ChildReceiver.text
import example.models.{Song, SongResponse}
import example.styles.GlobalStyles
import org.scalajs.dom
import org.scalajs.dom.document
import scalacss.ProdDefaults.*
import scalacss.StyleA
import scalacss.internal.mutable.GlobalRegistry
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.language.implicitConversions


@main
def helloWorld(): Unit =
  GlobalRegistry.addToDocumentOnRegistration()
  GlobalRegistry.register(GlobalStyles)
  implicit val owner: Owner = new Owner {}

  extension (style: StyleA)
    def toClassName: Mod[HtmlElement] = className := style.className.value

  given Conversion[StyleA, Mod[HtmlElement]] =
    (styleA: StyleA) => className := styleA.className.value

  val tickStream = EventStream.periodic(10)
  ///// SONGS LIST STUFF ///
  def songListDatabaseRow(name: String, artist: String, genre: String, duration: String) = div(GlobalStyles.userPadding,
    table(GlobalStyles.userRow, GlobalStyles.userPadding, //heightAttr := 20,
      tbody(
        tr(
          td(p(s"$name"), GlobalStyles.songTableTextStyle, GlobalStyles.toTheLeft, widthAttr := 150),
          td(p(s"$artist"), GlobalStyles.songTableTextStyle, GlobalStyles.toTheLeft,  widthAttr := 100),
          td(p(s"$genre"), GlobalStyles.songTableTextStyle, GlobalStyles.toTheLeft,  widthAttr := 50),
          td(p(s"$duration"), GlobalStyles.songTableTextStyle, GlobalStyles.toTheLeft,  widthAttr := 50),
        )
      )
    )
  )
  val songsList = Var(List.empty[Song])
  val songsElements = songsList.signal.map { songs =>
    songs.map(song => div(songListDatabaseRow(song.title, song.artist, "rock", song.length.toString)))
  }

  val songListDatabase = div(GlobalStyles.songListDatabase,
    FetchStream.get("http://127.0.0.1:8081/v1/songs") --> { responseText => {
      println("Decoding......")
      val response = upickle.default.read[List[Song]](responseText)
      println(response)
      songsList.set(response)
    }
    },
    h2("Song List Database"),
    div(
      children <-- songsElements
      //      songListDatabaseRow("Follow you", "Imagine Dragons", "rock", "3:51"),
      //      songListDatabaseRow("Believer", "Imagine Dragons", "rock", "3:24"),
      //      songListDatabaseRow("Demons", "Imagine Dragons", "rock", "2:57"),
      //      songListDatabaseRow("Radioactive", "Imagine Dragons", "rock", "3:08"),
      //      songListDatabaseRow("Thunder", "Imagine Dragons", "rock", "3:07"),
    )
  )

  def deleteUser() = button(
    GlobalStyles.delete,
    span(
      cls := "button-text",
      "Delete user"
    ),
    // Make fetch request when this div element is mounted:
    //    FetchStream.get("http://127.0.0.1:8081/v1/songs") --> { responseText => println(responseText) }
    // Make fetch request on every click:
    //    onClick.flatMap(_ => FetchStream.get("http://127.0.0.1:8081/v1/songs")) --> { responseText => println(responseText) },
    //    // Same, but also get the click event:
    //    onClick.flatMap(ev => FetchStream.get("http://127.0.0.1:8081/v1/songs").map((ev, _))) --> {
    //      case (ev, responseText) => println(responseText)

    onClick --> println("DELETE!!")
  )

  def userTable(name: String) = div(GlobalStyles.userPadding,
    table( GlobalStyles.userRow, GlobalStyles.userPadding,
      tbody(
        tr( GlobalStyles.userRow,
          td(p(s"$name"), GlobalStyles.userTableTextStyle, GlobalStyles.toTheLeft),
          td(GlobalStyles.toTheRight, GlobalStyles.columnWidth,
            deleteUser()
          )
        )
      )
    )
  )

  val userList = div(
    h3("Users:"),
    GlobalStyles.userlist,
    div(
      userTable("Taylor Swift"),
      userTable("Harry Potter"),
      userTable("Tetiana"),
      userTable("Table user")
    )
  )

  def inputField(labelText: String, placeholderText: String) = div(
    div(GlobalStyles.toTheRight, label(GlobalStyles.label, labelText)),
    div(
      input(
        GlobalStyles.inputField,
        placeholder := placeholderText
      )
    )
  )

  val postSongResp = Var("")

  def sendPostRequest(songTitle: String, songDuration: String, artist: String): Binder.Base = {
    //    val duration: Double = songDuration.toDouble
    val song = Song(songDuration, songTitle, artist)
//    val songStub = Song("3.67", "Killer", "Queen")
    println(song)
    val json = upickle.default.write[Song](song)
    println(json)
    FetchStream.post("http://127.0.0.1:8081/v1/songs", _.body(json)) --> { responseText => {
      val response: String = upickle.default.read[SongResponse](responseText).text
      postSongResp.set(response)
    }
    }
  }

  def ctaButton(text: String) = button(
    GlobalStyles.ctaButton,
    span(
      cls := "button-text",
      text
    ),
    onClick --> println("POST!!")

  )

  def ctaButtonAddSong(text: String, songTitle: String, songDuration: String, artist: String) = button(
    GlobalStyles.ctaButton,
    span(
      cls := "button-text",
      text
    ),
    onClick.flatMap { _ =>
      println(songTitle)
      val songStub = Song("3.67", "Killer", "Queen")
      val song = Song("3.67", songTitle, "Queen")
      val json = upickle.default.write[Song](song)
      FetchStream.post("http://127.0.0.1:8081/v1/songs", _.body(json))
    } --> { responseText =>
      val response: String = upickle.default.read[SongResponse](responseText).text
      dom.console.log(response)
      postSongResp.set(response)
    }
  )

  val nameVar = Var(Song)
  def TextInput(): Input = input(typ := "text")

  case class FormState(title: String, artist: String, duration: String)
  val formStateVar = Var(FormState("", "", ""))
//  val titleWriter = formStateVar.updater[String]((currentState, title) => currentState.copy(title = title))
//  val artistWriter = formStateVar.updater[String]((currentState, artist) => currentState.copy(artist = artist))
//  val durationWriter = formStateVar.updater[String]((currentState, duration) => currentState.copy(duration = duration))

//  val songTitle = Var("")
  val songVar = formStateVar.zoom(_.title)((state, newTitle) => state.copy(title = newTitle))
//  val songVar: Source[String] = formStateVar.updater[String]((state, newTitle) => state.copy(title = newTitle))

  //  val songArtist = Var("")
//  val songDuration = Var("")
//  val submitObserver = Observer[FormState] { state => sendPostRequest(state.title, state.duration, state.artist) }

  val submitter = Observer[FormState] { state =>
    ctaButtonAddSong("Add song", state.title, state.duration, state.artist)
      //dom.window.alert(s"Song title: ${state.title}")
  }

  val addSongForm = div(
    GlobalStyles.addSongUserBoard,
    h3("Add song"),
    div(GlobalStyles.toTheLeft,
      form(
        onSubmit.mapTo(formStateVar.now()) --> submitter,
        div(
          div(GlobalStyles.toTheRight, label(GlobalStyles.label, "Song title: ")),
          div(
            input(
              GlobalStyles.inputField,
              placeholder := "Enter song title",
              value <-- songVar,
              onInput.mapToValue --> songVar
            )
          )
        ),
        p("Song title: ", text <-- songVar.signal),
        //        value <-- formStateVar.signal.map(t => t.title),
        //        onInput.mapToValue --> titleWriter,
      ),
//      div(
//        inputField("Artist: ", "Enter artist name"),
//        value <-- formStateVar.signal.map(_.artist),
//        onInput.mapToValue --> artistWriter
//      ),
//      div(
//        inputField("Duration: ", "Enter song duration"),
//        value <-- formStateVar.signal.map(_.duration),
//        onInput.mapToValue --> durationWriter
//      )
    ),
//    p(button(typ("submit"), "Submit song test"))
    p(ctaButtonAddSong("Add song", songVar.now(), "state.duration", "state.artist"))
  )



//  val addSongForm  = div(
//    GlobalStyles.addSongUserBoard,
//    h3("Add song"),
//    div(GlobalStyles.toTheLeft,
//      div(
//        div(
//          div(GlobalStyles.toTheRight, label(GlobalStyles.label, "Song title: ")),
//          div(
//            input(
//              GlobalStyles.inputField,
//              placeholder := "Enter song title",
//              onInput.mapToValue --> songTitle
//            )
//          )
//        ),
//        p("Song title: ",
//          text <-- songTitle),
////        value <-- formStateVar.signal.map(t => t.title),
////        onInput.mapToValue --> titleWriter,
//      ),
//      div(
//        inputField("Artist: ", "Enter artist name"),
//        value <-- formStateVar.signal.map(_.artist),
//        onInput.mapToValue --> artistWriter
//      ),
//      div(
//        inputField("Duration: ", "Enter song duration"),
//        value <-- formStateVar.signal.map(_.duration),
//        onInput.mapToValue --> durationWriter
//      )
//    ),
//    p(ctaButtonAddSong("Add song", songTitle.signal.now(), formStateVar.now().duration, formStateVar.now().artist))
//  )
  val addUserForm = div(
    GlobalStyles.addSongUserBoard,
    h3("Add user"),
    div(
      inputField("User name: ", "Enter user's name"),
      inputField("Age: ", "Enter user's age"),
      inputField("Favorite song: ", "Enter user's favorite song")
    ),
    p( ctaButton("Add user"))
  )

  val addCreateSong = div(
    GlobalStyles.pageContainer,
    div(GlobalStyles.topPanel,
      div(
        GlobalStyles.pageContainer,addSongForm),
      div(
        GlobalStyles.pageContainer,addUserForm)
    ),
    userList,
    songListDatabase
  )


  def appElement(): HtmlElement =
    div(
      GlobalStyles.pageContainer,
      div(
        GlobalStyles.topPanel,
        idAttr := "app-container",songListDatabase,
        addCreateSong)
    )



  renderOnDomContentLoaded(dom.document.querySelector("#app"), appElement())
