package example

import com.raquo.airstream.ownership.Owner
import com.raquo.airstream.web.FetchOptions
import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.api.features.unitArrows
import com.raquo.laminar.nodes.DetachedRoot
import example.models.{Song, User}
import example.styles.GlobalStyles
import org.scalajs.dom
import org.scalajs.dom.document
import scalacss.ProdDefaults.*
import scalacss.StyleA
import scalacss.internal.mutable.GlobalRegistry

import scala.language.implicitConversions
import scala.scalajs.js


@main
def helloWorld(): Unit =
  GlobalRegistry.addToDocumentOnRegistration()
  GlobalRegistry.register(GlobalStyles)
  implicit val owner: Owner = new Owner {}

  extension (style: StyleA)
    def toClassName: Mod[HtmlElement] = className := style.className.value

  given Conversion[StyleA, Mod[HtmlElement]] =
    (styleA: StyleA) => className := styleA.className.value
  // ENDPOINTS
  val host = "http://127.0.0.1:8080"

  def sendPostRequest(path: String, json: String): EventStream[String] = {
    val url = s"$host$path"
    FetchStream.post(url, _.body(json))
  }

  def sendAddSongRequest(path: String, json: String): EventStream[String] = {
    val url = s"$host$path"
    FetchStream.post(url, _.body(json))
  }

  def sendGetRequest(path: String): EventStream[String] = {
    val url = s"$host$path"
    FetchStream.get(url)
  }

  def sendDeleteRequest(path: String, uuid: String): EventStream[String] = {
    val url = s"$host$path/$uuid"
    FetchStream.put(url)
  }

  ///// SONGS LIST STUFF ///

  val songsList = Var(List.empty[Song])


  def deleteSong(songUuid: Option[String]) = button(
    GlobalStyles.deleteIcon,
    span(
      cls := "button-text",
      "x"
    ),
    onClick.flatMap { _ =>
      songUuid match {
        case Some(uuid) => sendDeleteRequest("/v1/songs", uuid)
        case _ => EventStream.fromValue("Cannot delete Song!!!!!")
      }
          } --> Observer[String] { responseText =>
      if (responseText == "Song deleted") {
        songsList.update(_.filterNot(_.songUuid == songUuid))
      }
      println(responseText)
      }
  )


  def songListDatabaseRow(song: Song, genre: String ) = div(GlobalStyles.userPadding,
    table(GlobalStyles.userRow, GlobalStyles.userPadding,
      tbody(
        tr(
          td(p(s"${song.title}"), GlobalStyles.songTableTextStyle, GlobalStyles.toTheLeft, widthAttr := 150),
          td(p(s"${song.artist}"), GlobalStyles.songTableTextStyle, GlobalStyles.toTheLeft,  widthAttr := 100),
          td(p(s"$genre"), GlobalStyles.songTableTextStyle, GlobalStyles.toTheLeft,  widthAttr := 50),
          td(p(s"${song.length}"), GlobalStyles.songTableTextStyle, GlobalStyles.toTheLeft,  widthAttr := 50),
          td(p(deleteSong(song.songUuid)), GlobalStyles.songTableTextStyle, GlobalStyles.toTheLeft,  widthAttr := 50),
        )
      )
    )
  )

  val songsElements = songsList.signal.map { songs =>
    songs.map(song => div(songListDatabaseRow(song, "rock")))
  }

  val songListDatabase = div(GlobalStyles.songListDatabase,
    sendGetRequest("/v1/songs") --> { responseText => {
      println("Decoding......")
      val response = upickle.default.read[List[Song]](responseText)
      println(response)
      songsList.set(response)
    }
    },
    h2("Song List Database"),
    div(
      children <-- songsElements
    )
  )

  val isModalVisible = Var(false)

  def customModal(content: HtmlElement): HtmlElement = {
    div(
      GlobalStyles.userInfo,
      display <-- isModalVisible.signal.map(if (_) "block" else "none"),
      div(
        cls := "modal-content",
        content,
        button(
          "Close",
          onClick --> (_ => isModalVisible.set(false))
        )
      )
    )
  }

  def deleteUser() = button(
    GlobalStyles.delete,
    span(
      cls := "button-text",
      "Delete user"
    ),
    onClick --> println("DELETE!!")
  )

  val userDetails = div(
    GlobalStyles.userInfo,
    h3("User details"),
    div(GlobalStyles.toTheLeft,
      p("User name: ", "Blala"),
      p(deleteUser()),
    )
  )


  def userTable(name: String) = div(GlobalStyles.userPadding,
    table( GlobalStyles.userRow, GlobalStyles.userPadding,
      tbody(
        tr( GlobalStyles.userRow,
          td(p(s"$name"), GlobalStyles.userTableTextStyle, GlobalStyles.toTheLeft),
          td(GlobalStyles.toTheRight, GlobalStyles.columnWidth,
            deleteUser()
          ),
          onClick --> (_ => isModalVisible.set(true))
        )
      )
    )
  )

  val usersList = Var(List.empty[User])
  val userElements = usersList.signal.map { users =>
    users.map(user => div(userTable(user.userName)))
  }

  val userList = div(GlobalStyles.userlist,
    sendGetRequest("/v1/users") --> { responseText => {
      println("Decoding......")
      val response = upickle.default.read[List[User]](responseText)
      println(response)
      usersList.set(response)
    }
    },
    h2("Users List"),
    div(
      children <-- userElements
    )
  )

  def ctaButton(text: String) = button(
    GlobalStyles.ctaButton,
    span(
      cls := "button-text",
      text
    ),
    onClick --> println("POST!!")
  )

  def ctaButtonAddSong(text: String, addSongForm: Var[AddUserForm]) = button(
    GlobalStyles.ctaButton,
    span(
      cls := "button-text",
      text
    ),
    onClick.flatMap { _ =>
      val addSong = addSongForm.now()
      val song = Song(length= addSong.duration, title = addSong.title, artist = addSong.artist)
      val json = upickle.default.write[Song](song)
      sendPostRequest("/v1/songs", json)
    } --> Observer[String] { responseText =>
      dom.console.log(responseText)
      val response: Song = upickle.default.read[Song](responseText)
      songsList.update(_ :+ response)
    }
  )

  def ctaButtonAddUser(text: String, userStateVar: Var[UserState]) = button(
    GlobalStyles.ctaButton,
    span(
      cls := "button-text",
      text
    ),
    onClick.flatMap { _ =>
      val userData = userStateVar.now()
      val favSong = userData.favouriteSongUuid.length match {
        case 0 => None
        case _ => Some(userData.favouriteSongUuid)
      }
      val userAge = userData.age.length match {
        case 0 => None
        case _ => Some(userData.age)
      }
      println(userStateVar.now())
      val user = User(userData.userName, userAge, favSong)
      val json = upickle.default.write[User](user)
      sendPostRequest("/v1/user", json)
    } --> Observer[String] { response =>
      dom.console.log(response)
      val user: User = upickle.default.read[User](response)
      usersList.update(_ :+ user)
    }
  )

  case class AddUserForm(title: String, artist: String, duration: String)
  val addUserFormVar = Var(AddUserForm("", "", ""))

  val songVar = addUserFormVar.zoom(_.title)((state, newTitle) => state.copy(title = newTitle))
  val songArtist = addUserFormVar.zoom(_.artist)((state, newArtist) => state.copy(artist = newArtist))
  val songDuration = addUserFormVar.zoom(_.duration)((state, newDuration) => state.copy(duration = newDuration))

  def inputField(labelText: String, placeholderText: String, myVar: Var[String]) = div(
    div(GlobalStyles.toTheRight, label(GlobalStyles.label, labelText)),
    div(
      input(
        GlobalStyles.inputField,
        placeholder := placeholderText,
        value <-- myVar,
        onInput.mapToValue --> myVar
      )
    )
  )

  val addSongForm = div(
    GlobalStyles.addSongUserBoard,
    h3("Add song"),
    div(GlobalStyles.toTheLeft,
      form(
        inputField("Song title: ", "Enter song title", songVar),
        inputField("Artist: ", "Enter artist name", songArtist),
        inputField("Duration: ", "Enter song duration", songDuration)
      ),
      p(ctaButtonAddSong("Add song", addUserFormVar))
    )
  )

  case class UserState(userName: String, age: String, favouriteSongUuid: String)
  val userStateVar = Var(UserState("", "", ""))
  val userNameVar = userStateVar.zoom(_.userName)((state, userName) => state.copy(userName = userName))
  val ageVar = userStateVar.zoom(_.age)((state, age) => state.copy(age = age))
  val favouriteSongVar = userStateVar.zoom(_.favouriteSongUuid)((state, favouriteSongUuid) => state.copy(favouriteSongUuid = favouriteSongUuid))

  val addUserForm = div(
    GlobalStyles.addSongUserBoard,
    h3("Add user"),
    div(GlobalStyles.toTheLeft,
      form(
        inputField("User name: ", "Enter user name", userNameVar),
        inputField("Age: ", "Enter user's age", ageVar),
        inputField("Favorite song: ", "Enter user's favorite song", favouriteSongVar)
      ),
      p(ctaButtonAddUser("Add user", userStateVar))
    )
  )

  val mainPage = div(
    GlobalStyles.topPanel,
    div(
    GlobalStyles.pageContainer,
      songListDatabase,
    div(GlobalStyles.topPanel,
      div(
        GlobalStyles.pageContainer,addSongForm),
      div(
        GlobalStyles.pageContainer,addUserForm)
    ),
    userList,
      customModal(
          userDetails
      )
    )
  )


  def appElement(): HtmlElement =
    div(
      GlobalStyles.pageContainer,
      div(
        GlobalStyles.topPanel,
        idAttr := "app-container",songListDatabase,
        mainPage)
    )



  renderOnDomContentLoaded(dom.document.querySelector("#app"), appElement())
