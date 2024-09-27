import java.io.File;
import java.util.Arrays;

public class Driver {

    //--------------------------------------------------
    // Method to exit and print line upon error
    //--------------------------------------------------
    public static void printExitLine() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int lineNumber = stackTrace[2].getLineNumber();
        System.out.println("Error, exitting Driver at line: " + lineNumber);
        System.exit(1);
    }

    //--------------------------------------------------
    // Testing: No argument constructor
    //--------------------------------------------------
    public static void testConstructorWithNoArgs() {
        
        Polynomial polynomial1 = new Polynomial();
        boolean test1 = polynomial1.coefficients.length == 1;
        boolean test2 = polynomial1.exponents.length == 1;
        boolean test3 = polynomial1.coefficients[0] == 0;
        boolean test4 = polynomial1.exponents[0] == 0;
        if (!test1 || !test2 || !test3 || !test4) printExitLine();
        System.out.println("Constructor with no arguments works");
    }

    //--------------------------------------------------
    // Testing: Coeffs only constructor
    //--------------------------------------------------
    public static void testConstructorWithCoeffs() {

        double[] coeffs = {1, 0, 6};
        Polynomial polynomial = new Polynomial(coeffs);
        double[] expectedCoeffs = {1, 6};
        int[] expectedExps = {0, 2};
        boolean test1 = Arrays.equals(polynomial.coefficients, expectedCoeffs);
        boolean test2 = Arrays.equals(polynomial.exponents, expectedExps);

        double[] coeffs2 = {0, 1};
        Polynomial polynomial2 = new Polynomial(coeffs2);
        double[] expectedCoeffs2 = {1};
        int[] expectedExps2 = {1};
        boolean test3 = Arrays.equals(polynomial2.coefficients, expectedCoeffs2);
        boolean test4 = Arrays.equals(polynomial2.exponents, expectedExps2);

        if (!test1 || !test2 || !test3 || !test4) printExitLine();
        System.out.println("Constructor with coeffs args works");
    }

    //--------------------------------------------------
    // Testing: Coeffs and exps constructor
    //--------------------------------------------------
    public static void testConstructorWithCoeffsAndExps() {

        double[] coeffs1 = {0, 1, 0, 6, 0};
        int[] exps1 = {1, 5, 2, 4, 17};
        Polynomial polynomial1 = new Polynomial(coeffs1, exps1);
        double[] expectedCoeffs1 = {1, 6};
        int[] expectedExps1 = {5, 4};
        boolean test1 = Arrays.equals(polynomial1.coefficients, expectedCoeffs1);
        boolean test2 = Arrays.equals(polynomial1.exponents, expectedExps1);

        if (!test1 || !test2) printExitLine();
        System.out.println("Constructor with coeffs and exps works");
    }

    //--------------------------------------------------
    // Testing: Constructor from file
    //--------------------------------------------------
    public static void testConstructorFromFile() {

        File file = new File("test_file.txt");
        Polynomial polynomial1 = new Polynomial(file);  
        double[] expectedCoeffs1 = {5, -3.6, 7};
        int[] expectedExps1 = {0, 2, 3};

        boolean test1 = Arrays.equals(polynomial1.coefficients, expectedCoeffs1);
        boolean test2 = Arrays.equals(polynomial1.exponents, expectedExps1);

        if (!test1 || !test2) printExitLine();
        System.out.println("Constructor from file works");
    }

    //--------------------------------------------------
    // Testing: findMaxExponent
    //--------------------------------------------------
    public static void testFindMaxExponent() {

        double[] coeffs1 = {1, -2, 3};
        int[] exps1 = {2, 17, 4};
        Polynomial polynomial1 = new Polynomial(coeffs1, exps1);
        int maxExp = Polynomial.findMaxExponent(polynomial1);
        boolean test1 = maxExp == 17;

        if (!test1) printExitLine();
        System.out.println("findMaxExponent works");
    }

    //--------------------------------------------------
    // Testing: add
    //--------------------------------------------------
    public static void testAdd() {

        double[] coeffs1 = {1, 2, 3};
        int[] exps1 = {2, 3, 4};
        double[] coeffs2 = {1, -2, 4};
        int[] exps2 = {1, 3, 5};
        Polynomial polynomial1 = new Polynomial(coeffs1, exps1);
        Polynomial polynomial2 = new Polynomial(coeffs2, exps2);
        Polynomial result = polynomial1.add(polynomial2);
        double[] expectedCoeffs1 = {1, 1, 3, 4};
        int[] expectedExps1 = {1, 2, 4, 5};
        boolean test1 = Arrays.equals(result.coefficients, expectedCoeffs1);
        boolean test2 = Arrays.equals(result.exponents, expectedExps1);

        if (!test1 || !test2) printExitLine();
        System.out.println("add works");
    }

    //--------------------------------------------------
    // Testing: evaluate
    //--------------------------------------------------
    public static void testEvaluate() {
        double[] coeffs1 = {4, 2, 8, -9, -3};
        int[] exps1 = {7, 3, 2, 5, 0};
        Polynomial polynomial1 = new Polynomial(coeffs1, exps1);
        boolean test1 = polynomial1.evaluate(0.5) == -1.0;
        if (!test1) printExitLine();
        System.out.println("evaluate works");
    }

    //--------------------------------------------------
    // Testing: hasRoot
    //--------------------------------------------------
    public static void testHasRoot() {
        double[] coeffs1 = {1, -4, 3};
        int[] exps1 = {2, 1, 0};
        Polynomial polynomial1 = new Polynomial(coeffs1, exps1);
        boolean test1 = polynomial1.hasRoot(3);
        if (!test1) printExitLine();
        System.out.println("hasRoot works");
    }

    //--------------------------------------------------
    // Testing: multiply
    //--------------------------------------------------
    public static void testMultiply() {
        double[] coeffs1 = {3, -2, 4};
        int[] exps1 = {2, 1, 0};
        double[] coeffs2 = {5, 6, -1};
        int[] exps2 = {2, 1, 0};
        double[] coeffs3 = {-4, 26, 5, 8, 15};
        int[] exps3 = {0, 1, 2, 3, 4};
        Polynomial polynomial1 = new Polynomial(coeffs1, exps1);
        Polynomial polynomial2 = new Polynomial(coeffs2, exps2);
        Polynomial resultPolynomial = polynomial1.multiply(polynomial2);
        Polynomial expectedPolynomial = new Polynomial(coeffs3, exps3);
        boolean test1 = Arrays.equals(resultPolynomial.coefficients, expectedPolynomial.coefficients);
        boolean test2 = Arrays.equals(resultPolynomial.exponents, expectedPolynomial.exponents);

        if (!test1 || !test2) printExitLine();
        System.out.println("multiply works");
    }

    //--------------------------------------------------
    // Testing: saveToFile
    //--------------------------------------------------
    public static void testSaveToFile() {
        double[] coeffs1 = {1, -4, 3};
        int[] exps1 = {2, 1, 0};
        Polynomial polynomial1 = new Polynomial(coeffs1, exps1);
        polynomial1.saveToFile("test_save_to_file.txt");

        System.out.println("\nCheck test_save_to_file.txt");
        System.out.println("It should say \"1.0x2-4.0x+3.0\"");

        System.out.println("saveToFile works? (Check file)");
    }

    public static void main(String [] args) {
        /*
        Polynomial p = new Polynomial(); System.out.println(p.evaluate(3));
        double [] c1 = {6,0,0,5};
        Polynomial p1 = new Polynomial(c1);
        double [] c2 = {0,-2,0,0,-9};
        Polynomial p2 = new Polynomial(c2); Polynomial s = p1.add(p2); System.out.println("s(0.1) = " + s.evaluate(0.1)); if(s.hasRoot(1))
        System.out.println("1 is a root of s"); else
        System.out.println("1 is not a root of s");
        */

        testConstructorWithNoArgs();
        testConstructorWithCoeffs();
        testConstructorWithCoeffsAndExps();
        testConstructorFromFile();
        testFindMaxExponent();
        testAdd();
        testEvaluate();
        testHasRoot();
        testMultiply();
        testSaveToFile();
        System.out.println("\nAll functions successfully tested");
    }
}