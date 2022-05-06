package gluecode;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Setup {
	public Shared sh;
	public SimpleDateFormat sf;
	public Date dt;
	//Dependency injection via constructor
	public Setup(Shared sh)
	{
		this.sh=sh;
	}
	//Hooked methods (cucumber-java itself can take current scenario details as an object)
	@Before
	public void method1(Scenario s)
	{
		sf=new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		dt=new Date();
		sh.s=s;
		sh.s.log("currently running scenario is"+sh.s.getName()+" at"+sf.format(dt));
	}
	
	@After
	public void method2(Scenario s)
	{
		//display the status of finished scenario
		sh.s.log(sh.s.getName()+"Result at "+sf.format(dt)+"is"+sh.s.getStatus().name());
	}

}
