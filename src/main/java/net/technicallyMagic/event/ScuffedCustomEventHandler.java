package net.technicallyMagic.event;

import net.technicallyMagic.particle.custom.ParticleConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


/*
    A very scuffed way to do it, but it should work...
    Basically allows the ModEvents class to access (read) the values within this class
    that are being updated by other classes/events/whatever else.
 */
public class ScuffedCustomEventHandler {

    // Particle Creation Section
    private static ArrayList<ParticleConstructor> particles = new ArrayList<>();
    public static Queue<ArrayList<ParticleConstructor>> particleQueue = new LinkedList<>();

    public static void addParticleToTick(ParticleConstructor particle) {
        particles.add(particle);
    }

    public static void pushParticleTick(){
        particleQueue.add(particles);
        particles = new ArrayList<>();
    }

    // Server Tick Tracking System
    protected static boolean tickTracking = false;
    private static int currentTick;

    protected static void bumpTick() {
        currentTick++;
    }

    public static void startTickTracking() {
        currentTick = 0;
        tickTracking = true;
    }

    public static int peekTick() {
        return currentTick;
    }

    public static int endTickTracking() {
        tickTracking = false;
        return currentTick;
    }

    public static boolean isTracking(){return tickTracking;}



}
