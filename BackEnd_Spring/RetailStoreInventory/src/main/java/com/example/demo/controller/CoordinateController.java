package com.example.demo.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Point;
import com.example.demo.service.ProductServiceImpl;

@RestController
@CrossOrigin
public class CoordinateController {
	@Autowired
	private ProductServiceImpl service;

	@GetMapping("/getPathByProductName/{SourceProductName}/{DestinationProductName}")
	public List<Point> getPath(@PathVariable String SourceProductName, @PathVariable String DestinationProductName) {
		try {
			List<Point> result = service.shortestPath(SourceProductName, DestinationProductName);
			Collections.reverse(result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping("/getPathByCoordinates/{x}/{y}/{DestinationProductName}")
	public List<Point> getPath(@PathVariable int x, @PathVariable int y, @PathVariable String DestinationProductName) {
		try {
			List<Point> result = service.shortestPath(x, y, DestinationProductName);
			Collections.reverse(result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
