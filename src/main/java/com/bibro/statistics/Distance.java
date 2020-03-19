package com.bibro.statistics;

import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.distance.DistanceUtils;
import org.locationtech.spatial4j.distance.GeodesicSphereDistCalc;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.impl.PointImpl;

public class Distance {

    private static GeodesicSphereDistCalc geodesicSphereDistCalc = new GeodesicSphereDistCalc.Haversine();

    public static double calculate(double longitude1, double latitude1, double longitude2, double latitude2) {
        Point point1 = new PointImpl(longitude1, latitude1, SpatialContext.GEO);
        Point point2 = new PointImpl(longitude2, latitude2, SpatialContext.GEO);

        double radians = geodesicSphereDistCalc.distance(point1, point2);
        return DistanceUtils.radians2Dist(radians, DistanceUtils.DEG_TO_KM);
    }
}
