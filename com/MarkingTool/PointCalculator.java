package com.MarkingTool;

public class PointCalculator {
	Main main;
	
	public PointCalculator( Main main ) {
		this.main = main;
	}
	
	public double calculateX( double x, double angle, double length ) {
		double newX = (x + length*Math.sin(angle/360*2*Math.PI));
		return newX;
	}
	
	public double calculateZ( double z, double angle, double length ) {
		double newZ = (z + length*Math.cos((angle+180)/360*2*Math.PI));
		return newZ;
	}
}
