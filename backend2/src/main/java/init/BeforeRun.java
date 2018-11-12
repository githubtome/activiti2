package init;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

/**
 * <pre>
 * 执行前，准备工作(创建数据表的两种方式)。
 * </pre>
 *
 * @author likangming  likangming@kungeek.com
 * @version 1.00.00
 * @since 2018/11/12 16:30
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 * </pre>
 */
public class BeforeRun {
    // 使用代码创建工作流所需要的23涨表
    @Test
    public void createDbTable () {
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        // 设置数据库信息
        processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
        processEngineConfiguration.setJdbcUrl("jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEndocing=utf8");
        processEngineConfiguration.setJdbcUsername("root");
        processEngineConfiguration.setJdbcPassword("root");
        /*
          *  processEngineConfiguration 中的配置
         *  DB_SCHEMA_UPDATE_FALSE = "false"; // 不能自动创建表（能够使用脚本创建）
           *    DB_SCHEMA_UPDATE_CREATE_DROP = "create-drop"; // 先删除再更新
         *  DB_SCHEMA_UPDATE_TRUE = "true"; // 如果表不存在我就自动创建表
         */
        // 设置数据库操作的设置
        processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        // 获取工作流的核心对象
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
        System.out.println("创建成功");
    }

    /**
     * 使用配置文件创建流程引擎
     */
    @Test
    public void createByConfig() {
        ProcessEngine processEngine = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti.cfg.xml")
                .buildProcessEngine();
    }
}
