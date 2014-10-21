/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
 package logic;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
   PerceptronLogicalAndTest.class,
   PerceptronLogicalNotTest.class,
   PerceptronLogicalOrTest.class
})

public class AllTests {}
