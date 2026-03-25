import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.*;
import java.util.function.*;
import java.util.Map;

/** The goal of this practice is not to "get the answer" through AI or some other means, but for you to think through the questions and
* come up with a strategy. You can decide not to do it at your own cost.
*/

/**
* In the following, write code to achieve what's asked. You don't need to but if you want to very the accuracy of your code,
* include statements to print the result.
*/

/** Submit to the TA, and he will assign you a grade based on a few selected responses.  */





public class Week10_labs
{
    public static void main(String[] args)
	{
        List<String> fruit = Arrays.asList("cherry","banana","berry","apple","cherry","kiwi","fig","date","lemon","honeydew","cherry","elderberry","apple","banana","grape");

		// Collect elements into a Set
		Set<String> fruitSet = fruit.stream().collect(Collectors.toSet());
		System.out.println("fruitSet = " + fruitSet);
		System.out.println();

        // Collect the fruit into groups based on their first character
		Map<Character, List<String>> charFruitMap = fruit.stream().collect(Collectors.groupingBy(s -> s.charAt(0)));
		System.out.println("Grouped by first char:");
		for (Map.Entry<Character, List<String>> e : charFruitMap.entrySet()) {
			System.out.println(e.getKey() + " = " + e.getValue());
		}
		System.out.println();

		// Group fruit by the length of the name
		Map<Integer, List<String>> lengthFruitMap = fruit.stream().collect(Collectors.groupingBy(String::length));
		System.out.println("Grouped by string length:");
		for (Map.Entry<Integer, List<String>> e : lengthFruitMap.entrySet()) {
			System.out.println(e.getKey() + " = " + e.getValue());
		}
		System.out.println();

		//Collect the fruit that has erry in it
		List<String> erryList = fruit.stream().filter(s -> s.contains("erry")).collect(Collectors.toList());
		System.out.println("erryList = " + erryList);
		System.out.println();

		//Create a partition of fruit based on if it contains erry
		Map<Boolean, List<String>> erryPart = fruit.stream().collect(Collectors.partitioningBy(s -> s.contains("erry")));
		System.out.println("True = " + erryPart.get(true));
		System.out.println("False = " + erryPart.get(false));
		System.out.println();

		//collect the fruit that has 5 or less symbols
		List<String> less5List = fruit.stream().filter(s -> s.length() <= 5).collect(Collectors.toList());
		System.out.println("less5List = " + less5List);
		System.out.println();

		//find the total number of symbols in all the fruit stored
		int totalChar = fruit.stream().reduce(0, 
			(partialSum, f) -> (partialSum + f.length()),
			(sum1, sum2) -> (sum1+sum2)
		);
		System.out.println("Total characters = " + totalChar);
		System.out.println();

		System.out.println();

		List<Integer> data = Arrays.asList(87, 23, 45, 100, 6, 78, 92, 44, 13, 56, 34, 99, 82, 19, 1012, 78, 45, 90, 23, 56, 78, 100, 3, 43, 67, 89, 21, 34, 10);

        // Partition data based on if >=50
		Map<Boolean, List<Integer>> part50 = data.stream().collect(Collectors.partitioningBy((e) -> (e >= 50)));
		System.out.println("True = " + part50.get(true));
		System.out.println("False = " + part50.get(false));
		System.out.println();

		//divide data into groups based on the remainder when divided by 7
		Map<Integer, List<Integer>> groupDiv7 = data.stream().collect(Collectors.groupingBy((e) -> (e%7)));
		for (Map.Entry<Integer, List<Integer>> e : groupDiv7.entrySet()) {
			System.out.println(e.getKey() + " = " + e.getValue());
		}
		System.out.println();

		//find the sum of the data
		int sum = data.stream().collect(Collectors.summingInt(e -> e));
		System.out.println("Sum = " + sum);
		System.out.println();

		//collect the unique values
		List<Integer> uniqueVal = data.stream().distinct().collect(Collectors.toList());
		System.out.println("uniqueVal = " + uniqueVal);
		System.out.println();

        //compute the cube of each values
		List<Integer> cubes = data.stream().collect(Collectors.mapping(e -> e*e*e, Collectors.toList()));
		System.out.println("cubes = " + cubes);
		System.out.println();

		//find the sum of the cubes of each value
		int cubeSum = cubes.stream().collect(Collectors.summingInt(e -> e));
		System.out.println("cubeSum = " + cubeSum);
		System.out.println();

		//increase the value of each element by 5
		List<Integer> incr5 = data.stream().map(e -> e+5).collect(Collectors.toList());
		System.out.println("incr5 = " + incr5);
		System.out.println();

		//compute the cube of the even values
		List<Integer> evenCubes = data.stream().filter(e -> e%2==0).map(e -> e*e*e).collect(Collectors.toList());
		System.out.println("evenCubes = " + evenCubes);
		System.out.println();

   }
}
