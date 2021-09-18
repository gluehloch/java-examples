package generics;

import java.util.ArrayList;
import java.util.List;

public class SomeClass implements SomeInterface<SomeClass> {

	public SomeClass getSomeClass() {
		return this;
	}

	public SomeEnum getEnum() {
		return SomeEnum.ONE;
	}

	public void doSomething() {
		SomeClass someClass = new SomeClass();
		AnotherClass anotherClass = new AnotherClass();

		SomeInterface<SomeClass> someClass2 = someClass.getSomeClass();
		SomeInterface<AnotherClass> someClass3 = anotherClass.getSomeClass();

		List<SomeInterface<?>> list = new ArrayList<SomeInterface<?>>();
		list.add(someClass2);
		list.add(someClass3);

		SomeInterface<?> someClass4 = list.get(0).getSomeClass();
		someClass4.getSomeClass().getSomeClass();
		// Die Typinformation (?) geht hier verloren.
	}

	public class AnotherClass implements SomeInterface<AnotherClass> {

		@Override
		public SomeInterface<AnotherClass> getSomeClass() {
			return this;
		}
	}

}
