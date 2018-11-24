package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Planogram;

@SpringBootApplication
public class RetailStoreInventoryApplication {

	public static void main(String[] args) {
		 SpringApplication.run(RetailStoreInventoryApplication.class, args);

		/*
		 * Planogram p = new Planogram();
		 * 
		 * 
		 * System.out.println(p.matrixTo2DCoor(0, 8).getX() + " " + p.matrixTo2DCoor(0,
		 * 8).getY()); System.out.println(p.coor2DToMatrix(4, 4).getX() + " " +
		 * p.coor2DToMatrix(4, 4).getY()); int a1[][] = p.getPlan();
		 * System.out.println(a1[0][8]);
		 * 
		 * System.out.println(p.matrixTo2DCoor(0, 0).getX() + " " + p.matrixTo2DCoor(0,
		 * 0).getY()); System.out.println(p.coor2DToMatrix(-4, 4).getX() + " " +
		 * p.coor2DToMatrix(-4, 4).getY()); int a2[][] = p.getPlan();
		 * System.out.println(a2[0][0]);
		 * 
		 * System.out.println(p.matrixTo2DCoor(8, 0).getX() + " " + p.matrixTo2DCoor(8,
		 * 0).getY()); System.out.println(p.coor2DToMatrix(-4, -4).getX() + " " +
		 * p.coor2DToMatrix(-4, -4).getY()); int a3[][] = p.getPlan();
		 * System.out.println(a3[8][0]);
		 * 
		 * System.out.println(p.matrixTo2DCoor(8, 8).getX() + " " + p.matrixTo2DCoor(8,
		 * 8).getY()); System.out.println(p.coor2DToMatrix(4, -4).getX() + " " +
		 * p.coor2DToMatrix(4, -4).getY());
		 * 
		 * System.out.println(a3[8][8]);
		 */

	}
}
