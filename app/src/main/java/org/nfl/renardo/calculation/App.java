/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.nfl.renardo.calculation;

public class App {

	public static void main(String[] args) {
      Suite suite = Suite.from(1,2,3,4,5,6);
      suite.calculate();
      suite.getCalculation().getResult().forEach(System.out::println);
	}
}
