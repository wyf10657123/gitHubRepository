package util;


import java.util.Scanner;

public class PointUtil {

    private static final double DOUBLE_EQUAL_ACCURACY = 1e-3;

    /* case1：凹包
    16
    10 70
    90 70
    90 10
    80 10
    80 60
    70 60
    70 20
    30 20
    30 50
    40 50
    40 30
    50 30
    50 60
    20 60
    20 10
    10 10
    6
    60 40
    70 40
    35 50
    20 10
    90 70
    30 50
    answer:
    Yes
    Yes(edge)
    Yes(edge)
    Yes(vertex)
    Yes(vertex)
    Yes(vertex)
    case 2 空心凹包
    10
    10 60
    60 60
    60 10
    20 10
    20 40
    50 40
    50 50
    40 50
    40 20
    10 20
    6
    20 50
    30 30
    20 20
    40 40
    50 50
    50 40
    answer:
    Yes
    No
    Yes(vertex)
    Yes(vertex)
    Yes(vertex)
    Yes(vertex)
    case 3 交叉凹包
    4
    10 30
    30 30
    10 10
    30 10
    5
    20 20
    20 25
    20 15
    15 20
    25 20
    answer:
    Yes(vertex)
    Yes
    Yes
    No
    No
    case 4 水平扫描线相较于顶点
    5
    10 10
    10 30
    20 50
    30 40
    30 10
    4
    20 30
    10 30
    20 40
    20 50
    answer:
    Yes
    Yes(vertex)
    Yes
    Yes(vertex)

    case 5
    6
    10 20
    20 40
    30 40
    40 50
    40 30
    30 10
    2
    35 40
    40 10
    answer:
    Yes
    No
    case 6
    4
    10 20
    20 20
    20 10
    10 10
    1
    10 15
    answer:
    Yes(edge)
         */
    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);
        while (cin.hasNextInt()) {
            int count = cin.nextInt();
            Point[] pList = new Point[count];
            for (int i = 0; i < count; i++) {
                int a, b;
                a = cin.nextInt();
                b = cin.nextInt();
                pList[i] = new Point(a, b);
            }
            Convex convex = new Convex(pList);

            int countP = cin.nextInt();
            for (int i = 0; i < countP; i++) {
                int a, b;
                a = cin.nextInt();
                b = cin.nextInt();
                Point target = new Point(a, b);
                if (Solution1.inTheConvex(target, convex)) {
                    System.out.println("Yes");
                } else {
                    System.out.println("No");
                }
            }
        }

    }


    /**
     * 交点法
     */
    private static class Solution1 {

        private static boolean inTheConvex(Point target, Convex convex) {
            Point[] directedPoints = convex.getDirectedPoints();
            int leftAcrossCount = 0;
            for (int i = 0; i < directedPoints.length; i++) {
                Point p1 = directedPoints[i];
                Point p2;
                if (i == directedPoints.length - 1) {
                    p2 = directedPoints[0];
                } else {
                    p2 = directedPoints[i + 1];
                }
                //点在线段上则在闭包内
                if (onTheLine(target, p1, p2)) {
                    return true;
                }
                // 水平线段不需要做参考判断
                if (doubleEqual(p1.getY(), p2.getY())) {
                    // 水平线不判断；
                    continue;
                }

                 /*可以开启这段注释进行剪枝，性能会好一些，但是算法理解上会困难一些
                 if (doubleEqual(p1.getY(), target.getY()) && p1.getX() <= target.getX()) {
                    // p1 在射线上
                    if (p1.getY() > p2.getY()) {
                        // p1 比 p2 高
                        leftAcrossCount++;
                    }
                    continue;
                } else if ((doubleEqual(p2.getY(), target.getY()) && p2.getX() <= target.getX())) {
                    // p2 在射线上
                    if (p2.getY() > p1.getY()) {
                        // p2 比 p1 高
                        leftAcrossCount++;
                    }
                    continue;
                }*/
                Point acrossPoint = getAcrossPoint(target, p1, p2);
                if (null == acrossPoint) {
                    continue;
                }

                // 对于在凸包顶点相交的特殊处理
                if (doubleEqual(acrossPoint.getY(), min(p1.getY(), p2.getY()))) {
                    continue;
                }

                if (acrossPoint.getX() < target.getX()) {
                    leftAcrossCount++;
                }
            }
            if (leftAcrossCount % 2 == 1) {
                return true;
            }
            return false;
        }

        /**
         * 获取水平直线 f(x)=target.x 与 线段 [p1,p2] 的交点
         */
        private static Point getAcrossPoint(Point target, Point p1, Point p2) {
            double scanerLine = target.getY();
            if (scanerLine < min(p1.getY(), p2.getY())
                    || scanerLine > max(p1.getY(), p2.getY())) {
                return null;
            }
            double acrossX;
            if (doubleEqual(p1.getX(), p2.getX())) {
                // 垂直
                acrossX = p1.getX();
            } else {
                double k = (p1.getX() - p2.getX()) / (p1.getY() - p2.getY());
                acrossX = (scanerLine - p2.getY()) * k + p2.getX();
            }

            return new Point(acrossX, scanerLine);
        }

        /**
         * 判断点 target 是否在线段 [p1,p2] 上
         */
        private static boolean onTheLine(Point target, Point p1, Point p2) {
            if (target.getX() < min(p1.getX(), p2.getX())
                    || target.getX() > max(p1.getX(), p2.getX())
                    || target.getY() < min(p1.getY(), p2.getY())
                    || target.getY() > max(p1.getY(), p2.getY())) {
                return false;
            }

            return doubleEqual((p2.getY() - target.getY()) * (p2.getX() - p1.getX()),
                    (p2.getY() - p1.getY()) * (p2.getX() - target.getX()));
        }

        private static double max(double a, double b) {
            return a > b ? a : b;
        }

        private static double min(double a, double b) {
            return a < b ? a : b;
        }

        private static boolean doubleEqual(double a, double b) {
            double delta = a - b;
            return delta < DOUBLE_EQUAL_ACCURACY && delta > -DOUBLE_EQUAL_ACCURACY;
        }
    }


    /**
     * 叉乘法
     */
    private static class Solution2 {

        private static boolean inTheConvex(Point target, Convex convex) {
            Point[] directedPoints = convex.getDirectedPoints();
            int leftAcrossCount = 0;
            for (int i = 0; i < directedPoints.length; i++) {
                Point p1 = directedPoints[i];
                Point p2;
                if (i == directedPoints.length - 1) {
                    p2 = directedPoints[0];
                } else {
                    p2 = directedPoints[i + 1];
                }
                //点在线段上则在闭包内
                if (onTheLine(target, p1, p2)) {
                    return true;
                }
                // 水平线段不需要做参考判断
                if (doubleEqual(p1.getY(), p2.getY())) {
                    // 水平线不判断；
                    continue;
                }

                if ((doubleEqual(p1.getY(), target.getY()) && p1.getX() <= target.getX())
                        || ((doubleEqual(p2.getY(), target.getY()) && p2.getX() <= target.getX()))) {
                    int tt = -1;
                    if (p1.y == target.y)
                        tt = 1;
                    else if (p2.y == target.y)
                        tt = 2;
                    int maxx;
                    if (p1.y > p2.y)
                        maxx = 1;
                    else
                        maxx = 2;
                    if (tt == maxx) //如果这个端点的纵坐标较大的那个端点
                        leftAcrossCount++;

                    continue;
                }
                Point t = new Point(target.x - 1, target.y);
                if (xmulti(p1, t, target) * xmulti(p2, t, target) <= 0) {    //L和边s相交
                    Point lowp, higp;
                    if (p1.y > p2.y) {
                        lowp = p2;
                        higp = p1;
                    } else {
                        lowp = p1;
                        higp = p2;
                    }
                    if (xmulti(target, higp, lowp) >= 0) {
                        leftAcrossCount++;
                    }
                }

            }
            if (leftAcrossCount % 2 == 1) {
                return true;
            }
            return false;
        }

        private static boolean onTheLine(Point target, Point p1, Point p2) {
            if (target.getX() < min(p1.getX(), p2.getX())
                    || target.getX() > max(p1.getX(), p2.getX())
                    || target.getY() < min(p1.getY(), p2.getY())
                    || target.getY() > max(p1.getY(), p2.getY())) {
                return false;
            }

            return doubleEqual(xmulti(p1, p2, target), 0);
        }

        private static double xmulti(Point p1, Point p2, Point p0)    //求p1p0和p2p0的叉积,如果大于0,则p1在p2的顺时针方向
        {
            return (p1.x - p0.x) * (p2.y - p0.y) - (p2.x - p0.x) * (p1.y - p0.y);
        }

        private static double max(double a, double b) {
            return a > b ? a : b;
        }

        private static double min(double a, double b) {
            return a < b ? a : b;
        }

        private static boolean doubleEqual(double a, double b) {
            double delta = a - b;
            return delta < DOUBLE_EQUAL_ACCURACY && delta > -DOUBLE_EQUAL_ACCURACY;
        }
    }

    private static class Point {
        double x;
        double y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        double getX() {
            return x;
        }

        double getY() {
            return y;
        }
    }

    private static class Convex {
        Point[] directedPoints;

        Convex(Point[] pList) {
            directedPoints = pList;
        }

        Point[] getDirectedPoints() {
            return directedPoints;
        }
    }
}

