import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Polynomial {

    double[] coefficients;
    int[] exponents;

    public Polynomial() {
        coefficients = new double[1];
        exponents = new int[1];
        coefficients[0] = 0;
        exponents[0] = 0;
    }

    public Polynomial(double[] coeffs) {
        int len = 0;

        for (int i = 0; i < coeffs.length; i++) {
            if (coeffs[i] != 0) {
                len++;
            }
        }

        coefficients = new double[len];
        exponents = new int[len];

        int i = 0;
        int j = 0;
        while (i < coeffs.length) {
            if (coeffs[i] != 0) {
                coefficients[j] = coeffs[i];
                exponents[j] = i;
                j++;
            }
            i++;
        }
    }

    public Polynomial(double[] coeffs, int[] exps) {

        int n = 0;
        for (int i = 0; i < coeffs.length; i++) {
            if (coeffs[i] != 0.0) n++;
        }

        coefficients = new double[n];
        exponents = new int[n];

        int i = 0;
        int j = 0;
        while (i < coeffs.length) {
            if (coeffs[i] != 0.0) {
                coefficients[j] = coeffs[i];
                exponents[j] = exps[i];
                j++;
            }
            i++;
        }
    }

    public Polynomial(File text) {
        try (Scanner scanner = new Scanner(text)){
            if (scanner.hasNextLine()) {
                String polynomial = scanner.nextLine();
                String[] terms = polynomial.split("(?=[+-])");

                this.coefficients = new double[terms.length];
                this.exponents = new int[terms.length];

                for (int i = 0; i < terms.length; i++) {
                    String term = terms[i];

                    if (term.contains("x")) {
                        String[] parts = term.split("x");
                        
                        if (parts[0].isEmpty() || parts[0].equals("+")) this.coefficients[i] = 1.0;
                        else if (parts[0].equals("-")) this.coefficients[i] = -1.0;
                        else this.coefficients[i] = Double.parseDouble(parts[0]);
                        
                        this.exponents[i] = (parts.length > 1) ? Integer.parseInt(parts[1]) : 1;
                    } else {
                        this.coefficients[i] = Double.parseDouble(term);
                        this.exponents[i] = 0;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong in constructing a Polynomial from a file");
        }
    }

    public static int findMaxExponent(Polynomial y) {
        int maxExp = -1;
        for (int i = 0; i < y.coefficients.length; i++) {
            if (y.exponents[i] > maxExp) {
                maxExp = y.exponents[i];
            }
        }
        return maxExp;
    }

    /*
    public double[] createCoeffsArray(Polynomial y) {
        int n = y.coefficients.length;
        double[] coeffs = new double[findMaxExponent(y) + 1];

        for (int i = 0; i < n; i++) {
            coeffs[y.exponents[i]] += y.coefficients[i];
        }

        return coeffs;
    }
    */

    public Polynomial add(Polynomial toAdd) {
        int n = this.coefficients.length;
        int m = toAdd.coefficients.length;
        double[] coeffs = new double[Math.max(findMaxExponent(this), findMaxExponent(toAdd)) + 1];

        for (int i = 0; i < n; i++) {
            coeffs[this.exponents[i]] += this.coefficients[i];
        }
        for (int j = 0; j < m; j++) {
            coeffs[toAdd.exponents[j]] += toAdd.coefficients[j];
        }

        return new Polynomial(coeffs);
    }

    public double evaluate(double x) {
        double result = 0;

        for (int i = 0; i < this.coefficients.length; i++) {
            result += this.coefficients[i] * Math.pow(x, this.exponents[i]);
        }

        return result;
    }

    public boolean hasRoot(double x) {
        return (evaluate(x) == 0);
    }

    public Polynomial multiply(Polynomial toMultiply) {
        int n = this.coefficients.length;
        int m = toMultiply.coefficients.length;
        int maxExp = findMaxExponent(this) + findMaxExponent(toMultiply);
        double[] coeffs = new double[maxExp + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int idx = this.exponents[i] + toMultiply.exponents[j];
                coeffs[idx] += this.coefficients[i] * toMultiply.coefficients[j];
            }
        }

        return new Polynomial(coeffs);
    }

    public void saveToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            StringBuilder polynomial = new StringBuilder();

            for (int i = 0; i < this.coefficients.length; i++) {

                if (this.coefficients[i] == 0) continue;
                if (i > 0) polynomial.append(this.coefficients[i] > 0 ? "+" : "");
                polynomial.append(this.coefficients[i]);
                if (this.exponents[i] > 0) {
                    polynomial.append("x");
                    polynomial.append(this.exponents[i] > 1 ? this.exponents[i] : "");
                }
            }
            writer.write(polynomial.toString());
        }
        catch (IOException e) {
            System.out.println("Error occured, IOException in saveToFile");
        }
    }
}