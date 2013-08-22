package ass1.tests;

import junit.framework.TestCase;

import org.junit.Test;

import ass1.MathUtil;

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
    public void testComponentExtracton() {
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

}
