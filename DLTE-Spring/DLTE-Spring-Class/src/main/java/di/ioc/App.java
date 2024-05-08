package di.ioc;

import com.sun.org.apache.xml.internal.utils.XML11Char;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        BeanFactory beanFactory = new XmlBeanFactory(new FileSystemResource("spring-dispatcher.xml"));
        Branch myBranch = beanFactory.getBean("branch3", Branch.class);
        System.out.println(myBranch);
        Branch branch = beanFactory.getBean("branch1", Branch.class);
        System.out.println(branch);
        Branch branch1 = beanFactory.getBean("branch4", Branch.class);
        System.out.println(branch1);
    }
}
