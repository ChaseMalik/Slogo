package parser;

import java.util.List;
import org.junit.Test;
import workspace.Workspace;
import static org.junit.Assert.*;
import actor.Turtle;
import exceptions.*;


public class TestParser {
    private static final double PRECISION = .00000001;
    private static final Parser p = new Parser();

    @Test(expected = UnclosedListException.class)
    public void TestListException () throws Exception {
        List<Node> list = p.parse("to [ :x ");
    }

    @Test(expected = IncorrectNumInputsException.class)
    public void TestNumInputsException () throws Exception {
        List<Node> list = p.parse("sum 1");
    }

    @Test(expected = IncorrectInputException.class)
    public void TestIncorrectInputException () throws Exception {
        List<Node> list = p.parse("dotimes [ :x ] [ fd :x ] ");
        list.get(0).evaluate(null);

    }

    @Test
    public void TestSum () throws Exception {
        List<Node> list = p.parse("sum sum 20 15 15");
        for (Node n : list) {
            assertEquals(50, n.evaluate(null), PRECISION);
        }
    }

    @Test
    public void TestDif () throws Exception {

        List<Node> list = p.parse("difference - 20 15 15");
        assertEquals(-10, list.get(0).evaluate(null), PRECISION);

    }

    @Test
    public void TestProd () throws Exception {

        List<Node> list = p.parse("* product 2 5 -3");
        assertEquals(-30, list.get(0).evaluate(null), PRECISION);

    }

    @Test
    public void TestQuotient () throws Exception {

        List<Node> list = p.parse("quotient / 20 5 2");
        assertEquals(2, list.get(0).evaluate(null), PRECISION);

    }

    @Test
    public void TestRemainder () throws Exception {

        List<Node> list = p.parse("remainder 20 5");
        assertEquals(0, list.get(0).evaluate(null), PRECISION);

        List<Node> list2 = p.parse("% % 58 17 4");
        assertEquals(3, list2.get(0).evaluate(null), PRECISION);

    }

    @Test
    public void TestMinus () throws Exception {

        List<Node> list = p.parse("minus ~ 2 ~ 44");
        assertEquals(2, list.get(0).evaluate(null), PRECISION);
        assertEquals(-44, list.get(1).evaluate(null), PRECISION);
    }

    @Test
    public void TestRandom () throws Exception {

        List<Node> list = p.parse("random sum 5 5");
        double ans = list.get(0).evaluate(null);
        assertTrue(ans <= 10);
        assertTrue(ans >= 0);
    }

    @Test
    public void TestTrig () throws Exception {

        List<Node> list = p.parse("sin 90");
        double ans = list.get(0).evaluate(null);
        assertEquals(1, ans, PRECISION);
        assertEquals(-1, p.parse("cos * pi / 180 pi").get(0).evaluate(null), PRECISION);
        assertEquals(1, p.parse("tan 45").get(0).evaluate(null), PRECISION);
        assertEquals(45, p.parse("atan tan 45").get(0).evaluate(null), PRECISION);
    }

    @Test
    public void TestPow () throws Exception {

        List<Node> list = p.parse("pow 2 3");
        double ans = list.get(0).evaluate(null);
        assertEquals(8, ans, PRECISION);
        assertEquals(1, p.parse("log 2.718281828").get(0).evaluate(null), PRECISION);
    }

    @Test
    public void TestBoolean () throws Exception {

        List<Node> list = p.parse("less? 5 2");
        double ans = list.get(0).evaluate(null);
        assertEquals(0, ans, PRECISION);
        assertEquals(1, p.parse("greaterp 5 2").get(0).evaluate(null), PRECISION);
        assertEquals(1, p.parse("or 0 not 0").get(0).evaluate(null), PRECISION);
        assertEquals(1, p.parse("equal? 1 less? 4 * 2 pi").get(0).evaluate(null), PRECISION);
        assertEquals(1, p.parse("notequal? and 0 3 and 5 4").get(0).evaluate(null), PRECISION);
    }

    @Test
    public void TestIf () throws Exception {

        List<Node> list = p.parse("if greater? sum 1 0 - 5 5 [ sum 5 5 ]");
        double ans = list.get(0).evaluate(null);
        assertEquals(10, ans, PRECISION);
        // TODO this shows that sin(0) < cos(90) due to issues with doubles is that ok?
        List<Node> list2 = p.parse("if less? sin 0 cos 90 [ sum 5 5 difference 55 23 ]");
        double ans2 = list2.get(0).evaluate(null);
        assertEquals(32, ans2, PRECISION);
    }

    @Test
    public void TestIfElse () throws Exception {

        Workspace workspace = new Workspace();
        List<Node> list =
                p.parse("ifelse greater? sum 1 0 - 5 5 [ make :a 5 ] [ make :a 10 ] sum :a 0");
        for (Node n : list) {
            n.evaluate(workspace);
        }
        assertEquals(5, list.get(1).evaluate(workspace), PRECISION);
        list = p.parse("ifelse greater? sum 1 0 - 5 3 [ make :a 5 ] [ make :a 10 ] sum :a 0");
        for (Node n : list) {
            n.evaluate(workspace);
        }
        assertEquals(10, list.get(1).evaluate(workspace), PRECISION);

    }

    @Test
    public void TestSum2 () throws Exception {

        List<Node> list = p.parse("+ 20 20 sum 15 15");

        assertEquals(40, list.get(0).evaluate(null), PRECISION);
        assertEquals(30, list.get(1).evaluate(null), PRECISION);
    }

    @Test
    public void TestForward () throws Exception {

        Workspace workspace = new Workspace();
        workspace.createTurtle(0);
        List<Node> list = p.parse("forward 50");
        assertEquals(50, list.get(0).evaluate(workspace), PRECISION);
        assertEquals(50, workspace.getActiveTurtles().get(0).getY(), PRECISION);
    }

    @Test
    public void TestMake () throws Exception {

        List<Node> list = p.parse("make :var sum sum 1 1 20 sum :var 15");
        Workspace workspace = new Workspace();
        assertEquals(22, list.get(0).evaluate(workspace), PRECISION);
        assertEquals(37, list.get(1).evaluate(workspace), PRECISION);
    }

    @Test
    public void TestRepeat () throws Exception {

        Workspace workspace = new Workspace();
        List<Node> list = p.parse("make :a 0 repeat 5 [ make :a sum :a 5 ] sum :a 0");
        list.get(0).evaluate(workspace);
        list.get(1).evaluate(workspace);
        assertEquals(25, list.get(2).evaluate(workspace), PRECISION);
    }

    @Test
    public void TestFor () throws Exception {

        List<Node> list = p.parse("for [ 0 10 1 ] [ sum 5 5 ] sum 10 10");
        assertEquals(20, list.get(1).evaluate(null), PRECISION);
    }

    @Test
    public void TestTo () throws Exception {

        List<Node> list =
                p.parse("to square [ :var ] [ repeat 4 [ forward :var left 90 ] ] square [ 50 ] ");
        Workspace workspace = new Workspace();
        workspace.createTurtle(0);
        assertEquals(1, list.get(0).evaluate(workspace), PRECISION);
        list.get(1).evaluate(workspace);
        Turtle t = workspace.getTurtles().get(0);
        assertEquals(0, t.getX(), PRECISION);
        list =
                p.parse("make :a 0 to r [ :var :b ] [ repeat :var [ make :a sum ( :b :var :a ) ] ] r [ 10 5 ] sum :a 0");
        for (Node n : list) {
            n.evaluate(workspace);
        }
        assertEquals(150, list.get(3).evaluate(workspace), PRECISION);
    }

    @Test
    public void TestRecursion () throws Exception {

        List<Node> list =
                p.parse("make :a 0 to r [ :var ] [ make :a sum :var :a if greater? :var 1 [ r [ - :var 1 ] ]  ] r [ 10 ] sum :a 0 ");
        Workspace workspace = new Workspace();
        for (Node n : list) {
            n.evaluate(workspace);
        }
        assertEquals(55, list.get(3).evaluate(workspace), PRECISION);

    }

}
