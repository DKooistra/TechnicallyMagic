package net.technicallyMagic.logic;

import net.minecraft.world.phys.Vec3;

public class dirVec3 {

    // Points to origin and direction with provided vectors
    // vector: vector of the coordinates (from world origin to player)
    // unitVector: unit vector in the direction player is looking (from player to 1 magnitude in front)
    public dirVec3(Vec3 vector, Vec3 unitVector) {
        this.baseVector = vector;
        this.unitVector = unitVector;
    }

    // Points to origin and direction with provided vectors, provides maximum magnitude
    // vector: vector of the coordinates (from world origin to player)
    // unitVector: unit vector in the direction player is looking (from player to 1 magnitude in front)
    // maxMagnitude: maximum magnitude for the unit vector
    public dirVec3(Vec3 vector, Vec3 unitVector, double maxMagnitude) {
        this.baseVector = vector;
        this.unitVector = unitVector;
        this.maxMagnitude = maxMagnitude;
    }

    // Points to origin (provided coordinates) and direction of unit vector
    public dirVec3(double x, double y, double z, Vec3 unitVector) {
        this.baseVector = new Vec3(x, y, z);
        this.unitVector = unitVector;
    }

    // Points to origin (provided coordinates) and straight up
    public dirVec3(double x, double y, double z) {
        this.baseVector = new Vec3(x, y, z);
        this.unitVector = new Vec3(0, 1, 0);
    }



    // Provides an origin
    public Vec3 baseVector;

    // Provides unit vector for direction
    // How to use unit vector:
    //    By multiplying a unit vector (mag of 1) by a distance, new vector will have that distance in the same direction
    //    Unit circle is the circle of all points of unit vectors (3d space makes a unit sphere)
    public Vec3 unitVector;

    // Provides maximum magnitude of direction vector
    public double maxMagnitude = -1.0;


    // Used when finding a coordinate at a certain distance from origin
    public Vec3 calcCoord(double distance) {
        if (maxMagnitude >= 0 && distance > maxMagnitude) {
            // Do not surpass maximum magnitude, returns endpoint if distance requested is greater
            distance = maxMagnitude;
        }

        // Return desired point on vector
        return unitVector.multiply(distance, distance, distance).add(baseVector);
    }

    // Used when finding a coordinate at a certain distance from origin
    // Extra parameter for editing the origin
    public Vec3 calcCoord(double distance, double x, double y, double z) {
        if (maxMagnitude >= 0 && distance > maxMagnitude) {
            distance = maxMagnitude;
        }

        // Return desired point on vector
        return unitVector.multiply(distance, distance, distance).add(baseVector).add(x, y, z);
    }

}
