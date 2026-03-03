package resume

import Styles._
import scalatags.Text.all._

object Resume {

  def main(args: Array[String]): Unit = {

    def autolink(url: String) = a(url.stripPrefix("https://").stripPrefix("http://"), href := url)

    def row = div(display.flex, flexDirection := "row")

    def col = div(display.flex, flexDirection := "column")

    def titledBlock(title: String, loc: String, bullets: Frag*) = div(
      row(
        h3(roleText, title),
        div(rightGreyText, loc)
      ),
      bulletList(bullets: _*)
    )

    def subListBlock(title: String, bullets: Frag*) = div(
      h4(title),
      ul(bullets.map(li(subListItem, _)))
    )

    def bulletList(bullets: Frag*) = ul(
      listBlock,
      bullets.map(li(listItem, _))
    )

    def section(title: String, body: Frag) = tr(

      td(h2(paddingTop := 10, paddingBottom := 10, sectionHeading, title)),
      td(paddingTop := 10, paddingBottom := 10, body)
    )

    val blob = html(
      fontFamily := "Calibri, Candara, Segoe, 'Segoe UI', Optima, Arial, sans-serif",
      head(
        scalatags.Text.tags2.style(raw(cssReset)),
        scalatags.Text.tags2.style(raw(styleSheetText)),
        meta(charset := "UTF-8")
      ),
      body(
        width := 720,
        boxSizing.`border-box`,
        padding := 30,
        row(
          width := "100%",
          div(
            width := "50%",
            h1(nameText, "张天伦")
          ),
          col(
            width := "50%",
            div(textAlign.right, greyText, "15921814184"),
            div(textAlign.right, greyText, "owenzhang1990@gmail.com"),
            div(
              textAlign.right,
              greyText,
              autolink("https://github.com/manuzhang")
            )
          )
        )
      ),
      hr,
      table(
        width := "100%",
        section(
          "工作经历",
          col(
            row(h2(sectionHeading, "eBay"), div(rightGreyText, "上海")),
            titledBlock(
              "资深（MTS 2）软件工程师, 卖家 API", "2025年8月 - 2026年2月",
              "负责维护 eBay 公共交易 API（Trading API），该接口服务于全球数千名第三方开发者",
              "主导重构商品重复上架检测机制，通过分布式锁消除并发问题和关键数据库的热点问题"
            ),
            titledBlock(
              "资深（MTS 2）软件工程师, 大数据平台", "2019年6月 - 2025年8月",
              subListBlock("主导基于 Apache Iceberg 的下一代数据湖建设",
                "研发定制化迁移方案，实现大规模 Spark/Hive 表向 Iceberg 表的无缝平滑迁移",
                "开发数据湖管理器，实现了 Iceberg 表维护、性能调优及存储成本优化的全面自动化",
              ),
              subListBlock("负责 PB 级高可用 Spark 平台运维，支撑每日 10,000+ 生产作业稳定运行",
                "排查生产任务的运行故障，优化任务性能，节省集群资源",
                "构建集中化的 Spark 发行版和配置管理服务，支持不同业务场景需求",
                "集成自动化测试、性能剖析和版本控制工具，实现 Spark 大版本的自动化升级"),
            ),
            row(h2(sectionHeading, "唯品会"), div(rightGreyText, "上海")),
            titledBlock(
              "高级软件工程师，机器学习平台", "2017年8月 - 2019年6月",
              "负责端到端机器学习平台的架构与开发，为数据科学家和工程师提供模型开发、训练及部署的一体化工作空间",
              "开发了高性能的拖拽式流水线编辑器，抽象了复杂的执行逻辑，使非技术用户也能构建生产级的机器学习工作流",
              "开发流处理作业以准备实时模型训练数据，显著提升了模型的实时性"
            ),
            row(h2(sectionHeading, "英特尔"), div(rightGreyText, "上海")),
            titledBlock(
              "软件工程师，Gearpump 项目", "2015年1月 - 2017年8月",
              "Gearpump（基于 Akka 的实时大数据引擎）核心开发者，负责开发 Kafka 连接器、Storm 兼容层及事务 API"
            ),
            titledBlock(
              "软件工程师，英特尔 Hadoop 发行版", "2013年7月 - 2015年1月",
              "开发 storm-benchmark，一套专门用于大规模分析 Apache Storm 性能的基准测试套件，发现关键架构瓶颈",
              "参与贡献 mapreduce-nativetask 项目，该项目将 MapReduce 性能提升了 30% 并被并入 Hadoop 主分支"
            ),
            titledBlock(
              "实习生，英特尔 Hadoop 发行版", "2013年1月 - 2013年7月",
              "针对基于 Apache HBase 构建的消息队列进行了基准测试"
            )
          )
        ),
        section(
          "开源社区",
          col(
            div(
              row(h2(sectionHeading, "项目")),
              titledBlock(
                "Apache Iceberg（提交数排名前 10 的贡献者）",
                "",
                autolink("https://github.com/apache/iceberg")
              ),
              titledBlock(
                "Apache Spark（自 2019 年起长期贡献者）",
                "",
                autolink("https://github.com/apache/spark")
              ),
              titledBlock(
                "Apache Beam（Committer）",
                "",
                autolink("https://github.com/apache/beam")
              ),
              titledBlock(
                "Gearpump（核心开发人员）",
                "",
                autolink("https://github.com/gearpump/gearpump")
              ),
              titledBlock(
                "awesome-streaming（超过三千颗星）",
                "",
                autolink("https://github.com/manuzhang/awesome-streaming")
              )
            )
          )
        ),
        section(
          "技能专长",
          bulletList(
            row(h3(roleText, "编程语言："),
              Seq(
                "Scala",
                "Java",
                "Python",
                "SQL",
                "Shell",
                "C/C++",
                "JavaScript",
                "Haskell"
              ).mkString(", ")
            ),
            row(h3(roleText, "分布式系统："),
              Seq(
                "Iceberg",
                "Spark",
                "Hadoop",
                "Akka",
                "Beam",
                "Storm",
                "Kafka",
                "Cassandra"
              ).mkString(", ")
            ),
            row(h3(roleText, "基础设施/工具："),
              Seq(
                "Docker/Kubernetes",
                "Ubuntu",
                "CentOS",
                "Git",
                "Intellij",
                "Jupyter/JupyterLab",
                "Vim",
                "Emacs"
              ).mkString(", ")
            )
          )
        ),
        section(
          "教育背景",
          col(
            div(
              row(
                h2(sectionHeading, "华东师范大学"),
                div(rightGreyText, "上海")
              ),
              titledBlock(
                "软件工程，本科", "2009年9月 - 2013年7月"
              )
            )
          )
        )
      )
    )
    os.write.over(os.pwd / Symbol("target") / "resume.html", blob.render)
  }
}
