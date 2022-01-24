package org.example

import java.sql.DriverManager
import java.util.{Properties, TimeZone}

object Entrypoint extends App {
  val username = "user"
  val password = "password"
  val jdbcUrl = "jdbc:postgresql://localhost:5432/tz"

  val props = new Properties()
  props.setProperty("user", username)
  props.setProperty("password", password)

  val conn = DriverManager.getConnection(jdbcUrl, props)
  println("Connected")

  println("Fetching timezones...")
  val tzSql = "SELECT name FROM pg_timezone_names WHERE NOT name ~ '^posix/'"
  val stmt = conn.prepareStatement(tzSql);
  val result = stmt.executeQuery()
  var postgresTimezones = Set[String]()
  while(result.next()) {
    postgresTimezones += result.getString(1)
  }
  result.close()
  stmt.close()
  conn.close()
  println("Disconnected")

  val javaTimezones = TimeZone.getAvailableIDs.toSet

  println("Timezones in Java but not in PostgreSQL:")
  println((javaTimezones -- postgresTimezones).toList.sorted.mkString(", "))

  println("Timezones in PostgreSQL but not in Java:")
  println((postgresTimezones -- javaTimezones).toList.sorted.mkString(", "))
}
