package nth.introspect.infrastructure.hibernate;

import junit.framework.TestCase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ JbdcRepositoryTest.class, TestAll.class })
public class TestAll extends TestCase {

}
