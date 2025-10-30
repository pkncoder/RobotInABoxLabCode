package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LimelightHelpers {

    private NetworkTable limelightTable;
    private NetworkTableEntry txEntry;
    private NetworkTableEntry tyEntry;
    private NetworkTableEntry taEntry;

    public LimelightHelpers() {
        // Get the Limelight NetworkTable
        limelightTable = NetworkTableInstance.getDefault().getTable("limelight");

        // Get the entries for tx, ty, and ta
        txEntry = limelightTable.getEntry("tx");
        tyEntry = limelightTable.getEntry("ty");
        taEntry = limelightTable.getEntry("ta");
    }

    public double getTx() {
        // Get the horizontal offset from the crosshair to the target
        return txEntry.getDouble(0.0); // 0.0 is the default value if no target is found
    }

    public double getTy() {
        // Get the vertical offset from the crosshair to the target
        return tyEntry.getDouble(0.0); // 0.0 is the default value if no target is found
    }

    public double getTa() {
        // Get the target area (0-100% of image)
        return taEntry.getDouble(0.0); // 0.0 is the default value if no target is found
    }

    public boolean hasTarget() {
        // Check if the Limelight has any valid targets (tv = 0 or 1)
        NetworkTableEntry tvEntry = limelightTable.getEntry("tv");
        return tvEntry.getDouble(0.0) == 1.0;
    }
}