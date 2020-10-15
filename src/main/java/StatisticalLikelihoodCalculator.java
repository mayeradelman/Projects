import java.util.Scanner;

public class StatisticalLikelihoodCalculator {
	public static void main(final String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the base rate either as a decimal or as a percentage (followed immediately by the percent sign): ");
		Double baseRate = rateStringToDoubleConverter(scanner.next());
		System.out.print("Enter the sample size: ");
		int sampleSize = scanner.nextInt();
		System.out.print("Enter the observed rate either as a decimal or as a percentage (followed immediately by the percent sign): ");
		Double observedRate = rateStringToDoubleConverter(scanner.next());
		Double variance = (observedRate - baseRate);
		Double standardDeviation = Math.sqrt(variance);
		Double zScore = (observedRate - baseRate) / (standardDeviation / Math.sqrt(sampleSize));
	}

	static double rateStringToDoubleConverter(String rateAsString) {
		if(rateAsString.charAt(rateAsString.length() - 1) == '%'){
			rateAsString = rateAsString.substring(0, rateAsString.length() - 1);
			return new Double(rateAsString) / 100;
		}else{
			return new Double(rateAsString);
		}
	}
}
