package com.example.demo.model;



import org.springframework.stereotype.Component;

@Component
public class Planogram {

	int plan[][] = { { 0, 1, 1, 1, 0 },
					 { 0, 1, 0, 1, 0 },
					 { 0, 1, 0, 1, 0 },
					 { 0, 1, 1, 1, 0 }, 
					 { 0, 1, 0, 1, 0 }, 
					 { 0, 1, 0, 1, 0 },
					 { 1, 1, 1, 1, 0 } };
	
	public Planogram() {

	}

	public int[][] getPlan() {
		return plan;
	}

	public Point matrixTo2DCoor(int x, int y) {
		Point point = new Point(y - 2, 3 - x);
		return point;
	}

	public Point coor2DToMatrix(int x, int y) {
		Point point = new Point(3-y, 2 + x);
		return point;
	}

}