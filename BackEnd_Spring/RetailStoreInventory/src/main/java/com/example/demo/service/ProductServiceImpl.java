package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Planogram;
import com.example.demo.model.Point;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private Planogram planogram;

	public Product getByName(String productName) {
		return productRepository.findByProductNameIgnoreCase(productName);
	}

	public List<Point> shortestPath(String sourceProductName, String destinationProductName) {
		Product Source = getByName(sourceProductName);
		Product Destination = getByName(destinationProductName);
		Point sourcePoint = planogram.coor2DToMatrix(Source.getCoorx(), Source.getCoory());
		Point destinationPoint = planogram.coor2DToMatrix(Destination.getCoorx(), Destination.getCoory());

		// System.out.println("source="+Source.getCoorx()+","+Source.getCoory()+"
		// Destination="+Destination.getCoorx()+","+""+Destination.getCoory());
		// System.out.println("1. source="+sourcePoint.getX()+","+sourcePoint.getY()+"
		// Destination="+destinationPoint.getX()+","+""+destinationPoint.getY());

		PathFinder s = new PathFinder();
		int plan[][] = planogram.getPlan();
		List<com.example.demo.service.PathFinder.Point> distance = s.BFS(plan,
				new PathFinder.Point(sourcePoint.getX(), sourcePoint.getY()),
				new PathFinder.Point(destinationPoint.getX(), destinationPoint.getY()));

		System.out.println("distance" + distance.size());
		List<Point> shortestCoordinate = new ArrayList<Point>();
		for (int i = 0; i < distance.size(); i++) {
			Point temp = planogram.matrixTo2DCoor(distance.get(i).x, distance.get(i).y);
			shortestCoordinate.add(temp);
			System.out.println(distance.get(i).x + " " + distance.get(i).y);
		}
		return shortestCoordinate;
	}

	public List<Point> shortestPath(int x, int y, String destinationProductName) {
		//Product Source = productRepository.findByCoorxAndCoory(x, y);
		Product Destination = getByName(destinationProductName);
		Point sourcePoint = planogram.coor2DToMatrix(x, y);
		Point destinationPoint = planogram.coor2DToMatrix(Destination.getCoorx(), Destination.getCoory());

		// System.out.println("source="+Source.getCoorx()+","+Source.getCoory()+"
		// Destination="+Destination.getCoorx()+","+""+Destination.getCoory());
		// System.out.println("1. source="+sourcePoint.getX()+","+sourcePoint.getY()+"
		// Destination="+destinationPoint.getX()+","+""+destinationPoint.getY());

		PathFinder s = new PathFinder();
		int plan[][] = planogram.getPlan();
		List<com.example.demo.service.PathFinder.Point> distance = s.BFS(plan,
				new PathFinder.Point(sourcePoint.getX(), sourcePoint.getY()),
				new PathFinder.Point(destinationPoint.getX(), destinationPoint.getY()));

		System.out.println("distance" + distance.size());
		List<Point> shortestCoordinate = new ArrayList<Point>();
		for (int i = 0; i < distance.size(); i++) {
			Point temp = planogram.matrixTo2DCoor(distance.get(i).x, distance.get(i).y);
			shortestCoordinate.add(temp);
			System.out.println(distance.get(i).x + " " + distance.get(i).y);
		}
		return shortestCoordinate;
	}
}