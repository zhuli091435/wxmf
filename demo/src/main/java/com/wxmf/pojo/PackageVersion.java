package com.wxmf.pojo;

public class PackageVersion {
    /**
     * ID
     */
    private Integer ID;
    /**
     * PackageName
     */
    private String PackageName;
    /**
     * PackagePath
     */
    private String PackagePath;
    /**
     * HardwareVersion
     */
    private String HardwareVersion;
    /**
     * SystemProgramVersion
     */
    private String SystemProgramVersion;
    /**
     * ApplicationVersion
     */
    private String ApplicationVersion;

    public PackageVersion() {
    }

    public PackageVersion(Integer ID, String PackageName, String PackagePath, String HardwareVersion, String SystemProgramVersion, String ApplicationVersion) {
        this.ID = ID;
        this.PackageName = PackageName;
        this.PackagePath = PackagePath;
        this.HardwareVersion = HardwareVersion;
        this.SystemProgramVersion = SystemProgramVersion;
        this.ApplicationVersion = ApplicationVersion;
    }

    public Integer getID() {
        return this.ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getPackageName() {
        return this.PackageName;
    }

    public void setPackageName(String PackageName) {
        this.PackageName = PackageName;
    }

    public String getPackagePath() {
        return this.PackagePath;
    }

    public void setPackagePath(String PackagePath) {
        this.PackagePath = PackagePath;
    }

    public String getHardwareVersion() {
        return this.HardwareVersion;
    }

    public void setHardwareVersion(String HardwareVersion) {
        this.HardwareVersion = HardwareVersion;
    }

    public String getSystemProgramVersion() {
        return this.SystemProgramVersion;
    }

    public void setSystemProgramVersion(String SystemProgramVersion) {
        this.SystemProgramVersion = SystemProgramVersion;
    }

    public String getApplicationVersion() {
        return this.ApplicationVersion;
    }

    public void setApplicationVersion(String ApplicationVersion) {
        this.ApplicationVersion = ApplicationVersion;
    }


    @Override
    public String toString() {
        return "PackageVersion{" +
                "ID=" + ID +
                ", PackageName=" + PackageName +
                ", PackagePath=" + PackagePath +
                ", HardwareVersion=" + HardwareVersion +
                ", SystemProgramVersion=" + SystemProgramVersion +
                ", ApplicationVersion=" + ApplicationVersion +
                '}';
    }

}
