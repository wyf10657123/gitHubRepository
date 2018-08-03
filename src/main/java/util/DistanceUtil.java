package util;

import domain.Point;

public class DistanceUtil {

    private static double EARTH_RADIUS = 6371.393;
    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }

    /**
     * 计算两个经纬度之间的距离
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public static double getDistance(double lat1, double lng1, double lat2, double lng2)
    {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
                Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 1000);
        return s;
    }

    /**
     * 计算两点距离
     * @param a
     * @param b
     * @return
     */
    public static double getDistance(Point a,Point b){
        double x1_x2=Math.abs(a.getX()-b.getX());
        double y1_y2=Math.abs(a.getY()-b.getY());
        return Math.sqrt(x1_x2*x1_x2+y1_y2*y1_y2);
    }

    public static void main(String[] args){
        Point a=new Point(120.08239936613,30.293254992977);
        Point b=new Point(120.0845555614,30.2932885593);
        System.out.println(getDistance(a,b));
        System.out.println(getDistance(30.293254992977,120.08239936613,30.2932885593,120.0845555614));
        System.out.println(Math.min(10,8));
    }

}
