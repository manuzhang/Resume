package resume

import ammonite.ops._
import Styles._
import Styles.sheet._
import scalatags.Text.all._
object Resume{
  def dataUri(filepath: Path) = {
    "data:image/png;base64," +
    javax.xml.bind.DatatypeConverter.printBase64Binary(
      read.bytes! filepath
    )
  }

  def main(args: Array[String]) = {

    def autolink(url: String) = a(url.stripPrefix("https://").stripPrefix("http://"), href:=url)
    def row = div(display.flex, flexDirection := "row")
    def col = div(display.flex, flexDirection := "column")
    def titledBlock(title: String, loc: String, bullets: Frag*) = div(
      row(
        h3(roleText, title, paddingTop := 10),
        div(rightGreyText, loc, paddingTop := 5, paddingBottom := 5)
      ),
      bulletList(bullets:_*)
    )
    def bulletList(bullets: Frag*) = ul(
      listBlock,
      bullets.map(li(listItem, _))
    )
    def quickBullet(lhs: String, rhs: String) = tr(
      td(div(para, roleText, paddingRight := 5, lhs)),
      td(para, rhs)
    )
    def logo(s: String) = {
      img(height := 15, src := dataUri(cwd/'images/s))
    }
    def section(title: String, body: Frag) = tr(

      td(h2(paddingTop := 10, paddingBottom := 10, sectionHeading, title, marginRight := 20)),
      td(paddingTop := 10, paddingBottom := 10, body)
    )
    def talk(name: String, loc: String, video: String) = div(
      row(h3(roleText, name), div(rightGreyText, loc)),
      ul(listBlock, li(listItem, autolink(video)))
    )


    val blob = html(
      fontFamily := "Calibri, Candara, Segoe, 'Segoe UI', Optima, Arial, sans-serif",
      head(
        scalatags.Text.tags2.style(raw(cssReset)),
        scalatags.Text.tags2.style(raw(sheet.styleSheetText))
      ),
      body(
        width := 720,
        boxSizing.`border-box`,
        padding := 30,
        row(
          width := "100%",
            div(
              width := "50%",
              h1(nameText, "张天伦"),
              paddingBottom := 5
            )
          )
        ),
      row(
        width := "100%",
        col(
          width := "50%",
          div(
            textAlign.left,
            greyText,
            autolink("http://www.github.com/manuzhang"),
            paddingBottom := 2
          ),
          div(
            textAlign.left,
            greyText,
            autolink("http://manuzhang.github.io")
          )
        ),
        col(
          width := "50%",
          div(textAlign.right, greyText, "owenzhang1990@gmail.com", paddingBottom := 2),
          div(textAlign.right, greyText, "15921814184")
        )
      ),

        hr,
        table(
          width := "100%",
          section(
            "工作经历",
            col(
              row(h2(sectionHeading, "英特尔"), logo("Intel.png"), div(rightGreyText, "上海")),

              titledBlock(
                "软件工程师，大数据流处理", "2014年7月至今",
                """
                   开发基于 Akka 的高性能流处理引擎 Gearpump，实现 Apache Kafka 的连接器，Apache Storm 的透明兼容层，
                   Exactly-Once 语义，和 Apache Beam 的集成等。Gearpump 已经成为 Apache 基金会孵化项目。
                """,
                autolink("https://github.com/apache/incubator-gearpump")
              ),
              titledBlock(
                "软件工程师，大数据流处理", "2016年12月至今",
                """
                   支持客户基于 Apache Flink 开发调试流处理应用，与社区沟通，反馈 Bug，贡献源码。
                """
              ),
              titledBlock(
                "软件工程师，大数据流处理", "2016年7月 - 2016年8月",
                """
                   开发 HiBench 的流处理模块，对 Apache Spark Streaming, Apache Storm, Apache Flink 和
                   Apache Gearpump(incubating) 四个框架进行功能比较和性能测评。
                """,
                autolink("https://github.com/intel-hadoop/HiBench")
              ),
              titledBlock(
                "软件工程师, 大数据流处理", "2014年7月 - 2015年1月",
                """
                   开发性能测评工具 storm-benchmark，对 Apache Storm 进行性能测评。
                """,
                autolink("https://github.com/intel-hadoop/storm-benchmark")
              ),
              titledBlock(
                "软件工程师，英特尔 Hadoop 发行版", "2013年7月 - 2014年7月",
                """
                   开发 Apache Hadoop MapReduce Nativetask, 实现了对 Apache Pig 的支持和 Apache Hadoop 2.0 版的升级。
                   使用 HiBench 对其进行性能测评，对比原生的 MapReduce 有 30% 的性能提升。
                   该模块将于 Hadoop 3.0 版发布。
                """,
                """
                   基于 NTP 协议实现集群的时间同步。
                """
              ),
              titledBlock(
                "软件工程师实习，英特尔 Hadoop 发行版", "2013年1月 - 2013年7月",
                """
                   对一个基于 Apache HBase 实现的消息队列的性能测评
                """
              ),
              titledBlock(
                "华东师范大学海量计算研究所实习", "2013年7月 - 2013年12月",
                """
                   调研 Apache Cassandra 分布式数据库。
                """
              )
            )
        ),
        section(
          "Skills",
          h3(roleText,
            bulletList(
              Seq(
                "Scala",
                "Java",
                "C/C++",
                "Python",
                "JavaScript (AngularJS 1)",
                "Haskell",
                "Shell",
                "SQL"
              ).mkString(" - "),
              Seq(
                "Apache Storm",
                "Apache Flink",
                "Apache Beam",
                "Apache Kafka",
                "Akka",
                "Apache Hadoop",
                "Apache Cassandra"
              ).mkString(" - "),
              Seq(
                "Docker",
                "Ubuntu",
                "CentOS"
              ).mkString(" - "),
              Seq(
                "Git",
                "Intellij",
                "Vim",
                "Emacs"
              ).mkString(" - ")
            )
          )
        ),
        section(
          "教育经历",
          col(
            div(
              row(
                h2(sectionHeading, "华东师范大学"),
                // Override height to compensate for non-square image
                logo("ECNU.png")(height := 12, paddingTop := 4),
                div(rightGreyText, "上海")
              ),
              titledBlock(
                "软件工程本科", "2009年9月 - 2013年7月"
              )
            )
          )
        ),
        section(
          "社区项目",
          col(
            div(
              row(
                h2(sectionHeading, "awesome-streaming")),
              titledBlock(
                "项目维护者", "2014年12月至今",
                """
                   收录了一些有意思的流处理框架，应用和工具。
                """,
                autolink("https://github.com/manuzhang/awesome-streaming")
              ),
              paddingBottom := 10
            ),
            div(
              row(
                h2(sectionHeading, "上海大数据流处理 Meetup")),
              titledBlock(
                "组织者", "2015年8月至今",
                """
                   定期组织聚会沙龙，邀请业界专家揭开流处理技术内幕，分享流处理最佳实践。
                """,
                autolink("http://www.meetup.com/Shanghai-Big-Data-Streaming-Meetup/")
              )
            )
          )
        )
      )
    )
    write.over(cwd/'target/"resume.html", blob.render)
  }
}
