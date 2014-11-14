package architecture;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class) 
@SuiteClasses( { 
   EventManagementSystemTest.class, 
   SerializationSystemTest.class 
} ) 
public class AllTests {

}
