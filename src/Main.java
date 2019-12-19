public class Main {
    public static void main(String[] args) {

        IterativeMethod iterativeMethod = new IterativeMethod();
        iterativeMethod.findSolution(Math.pow(10, -5));
        System.out.printf("The biggest value: %7.5f\n", iterativeMethod.getEigenvalue());
        Vector.outputVector(iterativeMethod.getEigenvector());
        System.out.println(iterativeMethod.getIterationAmount());
        iterativeMethod.outputResidual();
        System.out.printf("%3.1e", Vector.norm(iterativeMethod.getResidual()));

    }
}