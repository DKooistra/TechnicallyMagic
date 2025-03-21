package net.technicallyMagic.logic;

import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.GenericParticleDataBuilder;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleDataBuilder;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleData;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleDataBuilder;

import java.awt.*;

public class ParticleIDInfo {

    // This class allows for the standardized use of particle colors (possibly more to come)
    // Given an easy to remember/use particleID for development/readability without
    // overwhelming the packets sent to players

/*
Example of where data is used:

WorldParticleBuilder.create(LodestoneParticleRegistry.SPARK_PARTICLE)

                // Set scale over time
                .setScaleData(GenericParticleData.create(0.8f, 0).build())

                // Set transparency over time
                .setTransparencyData(GenericParticleData.create(0.9f, 0.65f).build())

                // Set color stuffs over time
                .setColorData(ColorParticleData.create(new Color(141, 5, 5), new Color(74, 0, 131)).setCoefficient(1.4f).setEasing(Easing.BOUNCE_IN_OUT).build())

                // Set spin over time
                .setSpinData(SpinParticleData.create(0.2f, 0.4f).setSpinOffset((level.getGameTime() * 0.2f) % 6.28f).setEasing(Easing.QUARTIC_IN).build())

                // How longs it lasts
                .setLifetime(30)

                // What's its movement lookin like
                .addMotion(0, 0.01f, 0)

                // It hit stuff?
                .enableNoClip()

                // Where we droppin boys?
                .spawn(level, pos.x, pos.y, pos.z);

 */


    private static final byte librarySize = 1;

    // For each particle ID, there exists an index
    private static String[] particleIDs = new String[librarySize];

    // For each index, there exists...

    // Scale data
    private static GenericParticleDataBuilder[] scalePartData = new GenericParticleDataBuilder[librarySize];

    // Transparency data
    private static  GenericParticleDataBuilder[] transPartData = new GenericParticleDataBuilder[librarySize];

    // Color data
    private static ColorParticleDataBuilder[] colorPartData = new ColorParticleDataBuilder[librarySize];

    // Spin data
    private static SpinParticleDataBuilder[] spinPartData = new SpinParticleDataBuilder[librarySize];


    // Build data for the stuff (makes it easier to add new ones/see which ones correspond)
    public static void buildIDInfoLibrary() {
        //_____________________________
        particleIDs[0] = "redToPurple";
        scalePartData[0] = GenericParticleData.create(0.8f, 0);
        transPartData[0] = GenericParticleData.create(0.9f, 0.65f);
        colorPartData[0] = ColorParticleData.create(new Color(141, 5, 5), new Color(74, 0, 131)).setCoefficient(1.4f).setEasing(Easing.BOUNCE_IN_OUT);
        spinPartData[0] = SpinParticleData.create(0.2f, 0.4f).setEasing(Easing.QUARTIC_IN);
        //_____________________________




    }


    // Get Particle ID from index numb
    public static String getParticleID(int index) {
        return particleIDs[index];
    }


    // Get Scale data from index numb
    public static GenericParticleDataBuilder getScaleData(int index) {
        return scalePartData[index];
    }

    // Get Transparency data from index numb
    public static GenericParticleDataBuilder getTransData(int index) {
        return transPartData[index];
    }

    // Get Color data from index numb
    public static ColorParticleDataBuilder getColorData(int index) {
        return colorPartData[index];
    }

    // Get Spin data from index numb
    public static SpinParticleDataBuilder getSpinData(int index) {
        return spinPartData[index];
    }


    // Get index numb from particleID
    public static byte getIndex(String particleID) {

        byte curIndex = 0;

        // Linear Search for particleID (list isn't gonna be that long)
        for (String id : particleIDs) {
            if (id.equals(particleID)) {
                return curIndex;
            }
            curIndex++;
        }

        // If curIndex == length of list, then it didn't find a match
        if (particleIDs.length == curIndex) {
            return -1;
        } else {
            return curIndex;
        }

    }

}
