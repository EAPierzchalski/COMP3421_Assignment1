package ass1;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection of useful math methods 
 *
 * TODO: The methods you need to complete are at the bottom of the class
 *
 * @author malcolmr
 */
public class MathUtil {

    /**
     * Normalise an angle to the range (-180, 180]
     * 
     * @param angle 
     * @return
     */
    static public double normaliseAngle(double angle) {
        return ((angle + 180.0) % 360.0 + 360.0) % 360.0 - 180.0;
    }

    /**
     * Clamp a value to the given range
     * 
     * @param value
     * @param min
     * @param max
     * @return
     */

    public static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
    
    /**
     * Multiply two matrices
     * 
     * @param p A 3x3 matrix
     * @param q A 3x3 matrix
     * @return
     */
    public static double[][] multiply(double[][] p, double[][] q) {

        double[][] m = new double[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                m[i][j] = 0;
                for (int k = 0; k < 3; k++) {
                   m[i][j] += p[i][k] * q[k][j]; 
                }
            }
        }

        return m;
    }

    /**
     * Multiply a vector by a matrix
     * 
     * @param m A 3x3 matrix
     * @param v A 3x1 vector
     * @return
     */
    public static double[] multiply(double[][] m, double[] v) {

        double[] u = new double[3];

        for (int i = 0; i < 3; i++) {
            u[i] = 0;
            for (int j = 0; j < 3; j++) {
                u[i] += m[i][j] * v[j];
            }
        }

        return u;
    }



    // ===========================================
    // COMPLETE THE METHODS BELOW
    // ===========================================
    

    /**
     * TODO: A 2D translation matrix for the given offset vector
     * 
     * @param pos
     * @return
     */
    public static double[][] translationMatrix(double[] pos) {
        double[][] t = identity();
        t[0][2] = pos[0];
        t[1][2] = pos[1];
        return t;
    }

    /**
     * TODO: A 2D rotation matrix for the given angle
     * 
     * @param angle
     * @return
     */
    public static double[][] rotationMatrix(double angle) {
        double radians = Math.toRadians(angle);
        double s = Math.sin(radians);
        double c = Math.cos(radians);
        double[][] r = identity();
        r[0][0] = c;
        r[0][1] = -s;
        r[1][0] = s;
        r[1][1] = c;
        return r;
    }

    /**
     * TODO: A 2D scale matrix that scales both axes by the same factor
     * 
     * @param scale
     * @return
     */
    public static double[][] scaleMatrix(double scale) {
        double[][] s = identity();
        s[0][0] = scale;
        s[1][1] = scale;
        return s;
    }

    public static double[][] identity() {
        return new double[][]{{1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}};
    }

    public static double[][] TRSMatrix(double[] translation, double rotation, double scale) {
        List<double[][]> transformations = new ArrayList<double[][]>(3);
        transformations.add(translationMatrix(translation));
        transformations.add(rotationMatrix(rotation));
        transformations.add(scaleMatrix(scale));
        return rightMatrixProduct(transformations);
    }

    public static double[][] inverseTRSMatrix(double[] translation, double rotation, double scale) {
        List<double[][]> transformations = new ArrayList<double[][]>(3);
        transformations.add(scaleMatrix(1/scale));
        transformations.add(rotationMatrix(-rotation));
        transformations.add(translationMatrix(new double[]{-translation[0], -translation[1]}));
        return rightMatrixProduct(transformations);
    }

    public static double[][] rightMatrixProduct(List<double[][]> matrices) {
        double[][] m = identity();
        for (double[][] a : matrices) {
            m = multiply(m, a);
        }
        return m;
    }

    public static double[] translationComponent(double[][] matrix) {
        return new double[]{matrix[0][2], matrix[1][2]};
    }

    public static double scaleComponent(double[][] matrix) {
        return norm(new double[]{matrix[0][0], matrix[1][0]});
    }

    public static double rotationComponent(double[][] matrix) {
        return normaliseAngle(Math.toDegrees(Math.atan2(matrix[1][0], matrix[0][0])));
    }

    public static double norm(double[] v) {
        double sumOfSquares = 0;
        for (double vi : v) {
            sumOfSquares += Math.pow(vi, 2);
        }
        return Math.pow(sumOfSquares, 0.5);
    }

    public static String matrix2string(double[][] m) {
        StringBuilder matrixStringBuilder = new StringBuilder();
        for (double[] row : m) {
            StringBuilder rowStringBuilder = new StringBuilder();
            for (double x : row) {
                rowStringBuilder.append(String.format("%f ", x));
            }
            matrixStringBuilder.append(rowStringBuilder.toString());
            matrixStringBuilder.append("\n");
        }
        return matrixStringBuilder.toString();
    }

    public static String printVector(double[] v) {
        StringBuilder vectorStringBuilder = new StringBuilder();
        for (double x: v) {
            vectorStringBuilder.append(String.format("%f\n", x));
        }
        return vectorStringBuilder.toString();
    }

    public static boolean areEqual(double[][] a, double[][] b, double epsilon) {
        boolean eq = true;
        for (int row = 0; row < a.length; row++) {
            for (int col = 0; col < a[row].length; col++) {
                double delta = a[row][col] - b[row][col];
                if (!(-epsilon < delta && delta < epsilon)) {
                    eq = false;
                }
            }
        }
        return eq;
    }
}
