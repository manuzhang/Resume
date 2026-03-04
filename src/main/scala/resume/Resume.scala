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
              "资深（MTS 2）软件工程师, 大数据平台", "2019年6月 - 2026年2月",
              subListBlock("主导基于 Apache Iceberg 的下一代数据湖建设",
                """
                   负责 Iceberg 在生产环境的落地与规模化推广，基于社区版本针对内部场景和需求进行定制，推动 Iceberg 与 Spark 版本演进的兼容性与生产验证；
                   修复实践中发现的问题，反馈给社区并参与新功能的开发和讨论
                """,
                """
                   研发定制化迁移方案，在零数据拷贝和不影响业务的前提下，实现 300+ Spark/Hive 表向 Iceberg 表的无缝平滑迁移，满足 GDPR 等合规性要求
                """,
                """
                   开发数据湖优化器，通过监听 Iceberg commit 事件自动触发 compaction 任务合并小文件，降低元数据开销与读放大，提升查询稳定性与性能，节省集群资源
                """,
                """
                   设计基于 Iceberg 的 CDC 数据入湖方案，解决流式更新下查询性能下降的问题，满足准实时数据分析需求
                """
              ),
              subListBlock("开发维护高可用的 Spark 平台，支撑 PB 级数据处理和分析需求",
                """
                   基于社区版本根据内部需求对 Spark 进行定制化改造和升级，排查并解决生产任务运行故障，输出标准化排障流程与性能优化建议
                """,
                """
                   持续演进作业监控与诊断平台：解析 Spark EventLog，提取任务运行与诊断信息并写入 ElasticSearch，
                   支撑查询与诊断服务，提升故障定位与自助排障效率
                """,
                """
                   主导 Spark AQE 在生产平台稳定性落地并规模化迁移，重点治理数据倾斜场景以降低人工介入；
                   同时参与并贡献 Spark 社区相关修复与验证，推动其大规模生产可用
                """,
                """
                   建设并演进中心化 Spark 配置服务：支持不同集群多个版本与配置变更的灰度发布与回滚，以及不同业务场景在统一平台下的差异化需求
                """,
                """
                   基于中心化配置服务和诊断平台打造自动化 Spark 大版本升级流程，实现 10000+ 任务从 2.4 到 3.1 再到 3.5 的平滑升级；
                   修复升级过程中发现的问题，并反馈给社区
                """
               ),
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
              """
                 研发基于 Akka 的新一代实时大数据引擎 Gearpump，负责开发 Kafka 连接器、Storm 兼容层及事务 API，集成到 Apache Beam，
                 并将项目贡献给 Apache 基金会
              """
            ),
            titledBlock(
              "软件工程师，英特尔 Hadoop 发行版", "2013年7月 - 2015年1月",
              "开发 storm-benchmark，一套专门用于大规模分析 Apache Storm 性能的基准测试套件，发现关键架构瓶颈",
              "参与贡献 mapreduce-nativetask 项目，该项目将 MapReduce 性能提升了 30% 并被并入 Hadoop 主分支"
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
                "Apache Spark（长期积极贡献者）",
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
