public class Polynomial {

    double[] coefficients;

    public Polynomial() {
        coefficients = new double[1];
        coefficients[0] = 0;

    }

    public Polynomial(double[] coeffs) {
        coefficients = new double[coeffs.length];

        for (int i = 0; i < coeffs.length; i++) {
            coefficients[i] = coeffs[i];
        }
    }

    public Polynomial add(Polynomial toAdd) {
        int maxLength = Math.max(this.coefficients.length, toAdd.coefficients.length);
        int minLength = Math.min(this.coefficients.length, toAdd.coefficients.length);

        double[] result = new double[maxLength]; // [] not ()
        for (int i = 0; i < this.coefficients.length; i++) {
            result[i] += this.coefficients[i];
        }
        for (int i = 0; i < toAdd.coefficients.length; i++) {
            result[i] += toAdd.coefficients[i];
        }

        return new Polynomial(result);
    }

    public double evaluate(double x) {
        double result = this.coefficients[0];
        for (int i = 1; i < this.coefficients.length; i++) {
            result += this.coefficients[i] * Math.pow(x, i);
        }
        return result;
    }

    public boolean hasRoot(double x) {
        return (evaluate(x) == 0);
    }
}