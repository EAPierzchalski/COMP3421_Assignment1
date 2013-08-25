package ass1.tests;

import ass1.MathUtil;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * COMMENT: Comment MathUtilTest 
 *
 * @author malcolmr
 */
public class MathUtilTest extends TestCase {

    // to account for rounding errors on doubles, we will
    // test to within epsilon of the correct answer:
    
    private static final double EPSILON = 0.001;

    @Test
    public void testTranslation0() {
        double[] v = {0,0};
        double[][] m = MathUtil.translationMatrix(v);
        
        // Should be:
        // [[1,0,0]
        //  [0,1,0]
        //  [0,0,1]]

        assertEquals(1.0, m[0][0], EPSILON);
        assertEquals(0.0, m[0][1], EPSILON);
        assertEquals(0.0, m[0][2], EPSILON);

        assertEquals(0.0, m[1][0], EPSILON);
        assertEquals(1.0, m[1][1], EPSILON);
        assertEquals(0.0, m[1][2], EPSILON);

        assertEquals(0.0, m[2][0], EPSILON);
        assertEquals(0.0, m[2][1], EPSILON);
        assertEquals(1.0, m[2][2], EPSILON);    
    }
    
    @Test
    public void testTranslation1() {
        double[] v = {2,3};
        double[][] m = MathUtil.translationMatrix(v);
        
        // Should be:
        // [[1,0,2]
        //  [0,1,3]
        //  [0,0,1]]
    
        assertEquals(1.0, m[0][0], EPSILON);
        assertEquals(0.0, m[0][1], EPSILON);
        assertEquals(2.0, m[0][2], EPSILON);

        assertEquals(0.0, m[1][0], EPSILON);
        assertEquals(1.0, m[1][1], EPSILON);
        assertEquals(3.0, m[1][2], EPSILON);

        assertEquals(0.0, m[2][0], EPSILON);
        assertEquals(0.0, m[2][1], EPSILON);
        assertEquals(1.0, m[2][2], EPSILON);
    
    }

    @Test
    public void testRotation0() {
        double theta = 0;
        double[][] m = MathUtil.rotationMatrix(theta);
        
        // Should be:
        // [[1,0,0]
        //  [0,1,0]
        //  [0,0,1]]
    
        assertEquals(1.0, m[0][0], EPSILON);
        assertEquals(0.0, m[0][1], EPSILON);
        assertEquals(0.0, m[0][2], EPSILON);

        assertEquals(0.0, m[1][0], EPSILON);
        assertEquals(1.0, m[1][1], EPSILON);
        assertEquals(0.0, m[1][2], EPSILON);

        assertEquals(0.0, m[2][0], EPSILON);
        assertEquals(0.0, m[2][1], EPSILON);
        assertEquals(1.0, m[2][2], EPSILON);    
    }

    @Test
    public void testRotation1() {
        double theta = 90;
        double[][] m = MathUtil.rotationMatrix(theta);
        
        // Should be:
        // [[0,-1,0]
        //  [1,0,0]
        //  [0,0,1]]
    
        assertEquals(0.0, m[0][0], EPSILON);
        assertEquals(-1.0, m[0][1], EPSILON);
        assertEquals(0.0, m[0][2], EPSILON);

        assertEquals(1.0, m[1][0], EPSILON);
        assertEquals(0.0, m[1][1], EPSILON);
        assertEquals(0.0, m[1][2], EPSILON);

        assertEquals(0.0, m[2][0], EPSILON);
        assertEquals(0.0, m[2][1], EPSILON);
        assertEquals(1.0, m[2][2], EPSILON);    
    }

    @Test
    public void testRotation2() {
        double theta = -90;
        double[][] m = MathUtil.rotationMatrix(theta);
        
        // Should be:
        // [[0,1,0]
        //  [-1,0,0]
        //  [0,0,1]]
    
        assertEquals(0.0, m[0][0], EPSILON);
        assertEquals(1.0, m[0][1], EPSILON);
        assertEquals(0.0, m[0][2], EPSILON);

        assertEquals(-1.0, m[1][0], EPSILON);
        assertEquals(0.0, m[1][1], EPSILON);
        assertEquals(0.0, m[1][2], EPSILON);

        assertEquals(0.0, m[2][0], EPSILON);
        assertEquals(0.0, m[2][1], EPSILON);
        assertEquals(1.0, m[2][2], EPSILON);    
    }
    
    @Test
    public void testScale0() {
        double scale = 1;
        double[][] m = MathUtil.scaleMatrix(scale);
        
        // Should be:
        // [[1,0,0]
        //  [0,1,0]
        //  [0,0,1]]
    
        assertEquals(1.0, m[0][0], EPSILON);
        assertEquals(0.0, m[0][1], EPSILON);
        assertEquals(0.0, m[0][2], EPSILON);

        assertEquals(0.0, m[1][0], EPSILON);
        assertEquals(1.0, m[1][1], EPSILON);
        assertEquals(0.0, m[1][2], EPSILON);

        assertEquals(0.0, m[2][0], EPSILON);
        assertEquals(0.0, m[2][1], EPSILON);
        assertEquals(1.0, m[2][2], EPSILON);    
    }

    @Test
    public void testScale1() {
        double scale = 2;
        double[][] m = MathUtil.scaleMatrix(scale);
        
        // Should be:
        // [[2,0,0]
        //  [0,2,0]
        //  [0,0,1]]
    
        assertEquals(2.0, m[0][0], EPSILON);
        assertEquals(0.0, m[0][1], EPSILON);
        assertEquals(0.0, m[0][2], EPSILON);

        assertEquals(0.0, m[1][0], EPSILON);
        assertEquals(2.0, m[1][1], EPSILON);
        assertEquals(0.0, m[1][2], EPSILON);

        assertEquals(0.0, m[2][0], EPSILON);
        assertEquals(0.0, m[2][1], EPSILON);
        assertEquals(1.0, m[2][2], EPSILON);    
    }

    @Test
    public void testInverse() {
        double[] translation = {2, 3};
        double rotation = 60;
        double scale = 3;
        double[][] m1 = MathUtil.TRSMatrix(translation, rotation, scale);
        double[][] m2 = MathUtil.inverseTRSMatrix(translation, rotation, scale);
        double[][] id = MathUtil.identity();

        double[][] m3 = MathUtil.multiply(m2, m1);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(m3[i][j], id[i][j], EPSILON);
            }
        }
    }

    @Test
    public void testComponentExtraction() {
        double[] translation = {2, 3};
        double rotation = 60;
        double scale = 3;
        double[][] m = MathUtil.TRSMatrix(translation, rotation, scale);
        double[] translationComponent = MathUtil.translationComponent(m);
        double rotationComponent = MathUtil.rotationComponent(m);
        double scaleComponent = MathUtil.scaleComponent(m);

        assertEquals(translation[0], translationComponent[0], EPSILON);
        assertEquals(translation[1], translationComponent[1], EPSILON);
        assertEquals(rotation, rotationComponent, EPSILON);
        assertEquals(scale, scaleComponent, EPSILON);
    }

    @Test
    public void testTransformations() {
        double[] translation1 = {1, 3};
        double rotation1 = 45;
        double scale1 = 2;
        double[] translation2 = {-2, 0};
        double rotation2 = -45;
        double scale2 = 3;

        double[][] matrixTRSRootToC1 = MathUtil.TRSMatrix(translation1, rotation1, scale1);
        double[][] matrixTRSRootToC2 = MathUtil.TRSMatrix(translation2, rotation2, scale2);
        double[][] invTRSC2ToRoot = MathUtil.inverseTRSMatrix(translation2, rotation2, scale2);

        double[][] matrixTRSC2ToC1 = MathUtil.multiply(matrixTRSRootToC1, invTRSC2ToRoot);

        System.out.println("Matrix from Root to Coordinate System 1:");
        System.out.println(MathUtil.printMatrix(matrixTRSRootToC1));

        System.out.println("Matrix from Root to Coordinate System 2:");
        System.out.println(MathUtil.printMatrix(matrixTRSRootToC2));

        System.out.println("\nMatrix from Coordinate System 2 to Root:");
        System.out.println(MathUtil.printMatrix(invTRSC2ToRoot));

        System.out.println("\nMatrix from Coordinate System 2 to Coordinate System 1:");
        System.out.println(MathUtil.printMatrix(matrixTRSC2ToC1));

        double[] translationComponent = MathUtil.translationComponent(matrixTRSC2ToC1);
        double rotationComponent = MathUtil.rotationComponent(matrixTRSC2ToC1);
        double scaleComponent = MathUtil.scaleComponent(matrixTRSC2ToC1);



        assertEquals(0, translationComponent[0], EPSILON);
        assertEquals(2.0/Math.sqrt(3), translationComponent[1], EPSILON);
        assertEquals(90, rotationComponent, EPSILON);
        assertEquals(2.0/3.0, scaleComponent, EPSILON);
    }

}
