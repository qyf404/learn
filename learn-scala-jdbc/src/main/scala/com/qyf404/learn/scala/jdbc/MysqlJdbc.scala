package com.qyf404.learn.scala.jdbc

import java.sql.{Connection, DriverManager, Timestamp}

object MysqlJdbc {
  // connect to the database named "mysql" on the localhost
  val driver = "com.mysql.jdbc.Driver"
  val url = "jdbc:mysql://localhost:3306/scala_demo?useUnicode=true&characterEncoding=utf-8"
  val username = "root"
  val password = "123456"

  def insert(): Unit = {
    // there's probably a better way to do this
    var connection: Connection = null

    try {
      // make the connection
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)

      // create the statement, and run the insert
      val statement = connection.prepareStatement("insert into t_user (full_name,alias,citizenship,state,gmt_created,gmt_modified) values(?,?,?,?,?,?)")
      statement.setString(1, "张三")
      statement.setString(2, "[\"李四\"]")
      statement.setString(3, "[\"CH\"]")
      statement.setInt(4, 0)
      statement.setTimestamp(5, new Timestamp(System.currentTimeMillis()))
      statement.setTimestamp(6, new Timestamp(System.currentTimeMillis()))

      val bool = statement.execute()
      println("insert result :" + bool)

    } catch {
      case e => e.printStackTrace()
    } finally {
      connection.close()
    }

  }

  def select(): Unit = {
    // there's probably a better way to do this
    var connection: Connection = null

    try {
      // make the connection
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)

      // create the statement, and run the select query
      val statement = connection.createStatement()
      val resultSet = statement.executeQuery("SELECT id, full_name, alias, gmt_created FROM t_user")
      while (resultSet.next()) {
        val id = resultSet.getString("id")
        val fullName = resultSet.getString("full_name")
        val alias = resultSet.getString("alias")
        val createTime = resultSet.getString("gmt_created")
        println(s"id=${id}, fullName=${fullName}, alias=${alias}, createTime=${createTime}")
      }
    } catch {
      case e => e.printStackTrace()
    } finally {
      connection.close()
    }
  }

  def main(args: Array[String]) {
    insert()
    select()
  }
}
