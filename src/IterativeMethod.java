public class IterativeMethod {
    private double[][] matrix;
    private double[][] symmetricMatrix;
    private double[] residual;
    private double[] eigenvector;
    private double[] prevEigenvector;
    private double eigenvalue;
    private double prevEigenvalue;
    private int iterationAmount;
    private int length;

    public IterativeMethod() {
        this(5);
    }

    public IterativeMethod(int length) {
        this.length = length;
        this.matrix = new double[][]{{0.4974, 0.0000, -0.1299, 0.0914, 0.1523},
                {-0.0305, 0.3248, 0.0000, -0.0619, 0.0203},
                {0.0102, -0.0914, 0.5887, 0.0112, 0.0355},
                {0.0305, 0.0000, -0.0741, 0.5887, 0.0000},
                {0.0203, -0.0305, 0.1472, -0.0122, 0.4263}};
        symmetricMatrix = Matrix.matrixCompositionMatrix(Matrix.transposeMatrix(matrix), matrix);
        residual = new double[this.length];
        eigenvector = new double[this.length];
        prevEigenvector = new double[this.length];
        eigenvalue = 0;
        prevEigenvalue = 0;
        iterationAmount = 0;
    }

    public void outputResidual() {
        System.out.print("(");
        for (int i = 0; i < length - 1; i++) {
            System.out.printf("%3.1e; ", residual[i]);
        }
        System.out.printf("%3.1e", residual[length - 1]);
        System.out.println(")");
    }

    private void findResidual() {
        residual = Vector.matrixCompositionVector(symmetricMatrix, eigenvector);
        residual = Vector.vectorMinusVector(residual, Vector.vectorCompositionNumber(eigenvector, eigenvalue));
    }

    private void initialValueForEigenvector() {
        for (int i = 0; i < eigenvector.length; i++) {
            eigenvector[i] = 1;
        }
    }

    private void findEigenvector() {
        prevEigenvector = Vector.copyVector(eigenvector);
        eigenvector = Vector.matrixCompositionVector(symmetricMatrix, eigenvector);
    }

    private void findEigenvalue() {
        prevEigenvalue = eigenvalue;
        eigenvalue = eigenvector[0] / prevEigenvector[0];
    }

    public void findSolution(double accuracy) {
        initialValueForEigenvector();
        do {
            this.findEigenvector();
            this.findEigenvalue();
            eigenvector = Vector.orthonormalize(eigenvector);
            iterationAmount++;
        } while (Math.abs(eigenvalue - prevEigenvalue) >= accuracy);
        findResidual();
    }

    public double[] getResidual() {
        return residual;
    }

    public double[] getEigenvector() {
        return eigenvector;
    }

    public double getEigenvalue() {
        return eigenvalue;
    }

    public int getIterationAmount() {
        return iterationAmount;
    }

}