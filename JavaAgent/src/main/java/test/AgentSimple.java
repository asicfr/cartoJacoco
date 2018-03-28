package test;

import java.lang.instrument.Instrumentation;

public class AgentSimple {

	public static void premain(String agentArgument, Instrumentation instrumentation) {
		System.out.println("Instrumentation");

		instrumentation.addTransformer(new Transformer());
	}

}
