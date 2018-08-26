package example;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import java.util.Date;

/**
 * <pre>
 * 配置文件需要改的。
 * </pre>
 *
 * @author likangming  likangming@kungeek.com
 * @version 1.00.00
 * @since 2018/8/26 18:01
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 * </pre>
 */
public class AutomatedDataDelegate implements JavaDelegate{
    @Override
    public void execute(DelegateExecution delegateExecution) {
        Date now = new Date();
        delegateExecution.setVariable("autoWelcomeTime", now);
        System.out.println("Faux call to backend for ["
                + delegateExecution.getVariable("fullName") + "]");
    }
}
