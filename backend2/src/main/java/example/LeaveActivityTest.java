package example;

import org.activiti.engine.*;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

/**
 * <pre>
 * 程序的中文名称。
 * </pre>
 *
 * @author likangming  likangming@kungeek.com
 * @version 1.00.00
 * @since 2018/11/12 17:26
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 * </pre>
 */
public class LeaveActivityTest {
    /**
     * 会默认按照Resources目录下的activiti.cfg.xml创建流程引擎
     */
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    @Test
    public void test() {
        //以下两种方式选择一种创建引擎方式：1.配置写在程序里 2.读对应的配置文件
        //1
        testCreateProcessEngine();
        //2
        testCreateProcessEngineByCfgXml();

        deployProcess();
        startProcess();
        queryTask();
        //handleTask();
    }

    /**
     * 测试activiti环境
     */
    @Test
    public void testCreateProcessEngine() {
        ProcessEngineConfiguration cfg = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        cfg.setJdbcDriver("com.mysql.jdbc.Driver");
        cfg.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/demo");
        cfg.setJdbcUsername("root");
        cfg.setJdbcPassword("root");
        //配置建表策略
        cfg.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        ProcessEngine engine = cfg.buildProcessEngine();
    }

    /**
     * 根据配置文件activiti.cfg.xml创建ProcessEngine
     */
    @Test
    public void testCreateProcessEngineByCfgXml() {
        ProcessEngineConfiguration cfg = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        ProcessEngine engine = cfg.buildProcessEngine();
    }

    /**
     * 发布流程
     * RepositoryService
     */
    @Test
    public void deployProcess() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        DeploymentBuilder builder = repositoryService.createDeployment();
        builder.addClasspathResource("leave.bpmn");
        builder.deploy();
    }

    /**
     * 启动流程
     * RuntimeService
     */
    @Test
    public void startProcess() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //可根据id、key、message启动流程
        runtimeService.startProcessInstanceByKey("myProcess_1");
    }

    /**
     * 查看任务
     * TaskService
     */
    @Test
    public void queryTask() {
        TaskService taskService = processEngine.getTaskService();
        //根据assignee(代理人)查询任务
        String assignee = "emp";
//        String assignee = "manager";
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).list();

        for (Task task : tasks) {
            System.out.println("taskId:" + task.getId() +
                    ",taskName:" + task.getName() +
                    ",assignee:" + task.getAssignee() +
                    ",createTime:" + task.getCreateTime());
        }
    }
    @Test
    public void queryManagerTask() {
        TaskService taskService = processEngine.getTaskService();
        //根据assignee(代理人)查询任务
        String assignee = "manager";
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).list();

        for (Task task : tasks) {
            System.out.println("taskId:" + task.getId() +
                    ",taskName:" + task.getName() +
                    ",assignee:" + task.getAssignee() +
                    ",createTime:" + task.getCreateTime());
        }
    }

    /**
     * 办理任务
     */
    @Test
    public void handleTask() {
        TaskService taskService = processEngine.getTaskService();
        //根据上一步生成的taskId执行任务
        String taskId = "7505";
        taskService.complete(taskId);
    }
}
