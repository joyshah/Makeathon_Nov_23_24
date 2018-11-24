package com.example.demo.service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class PathFinder {
	public static Queue<QueueNode> l;
	public static final int ROW = 7;
	public static final int COL = 5;
	int[] rowNum = new int[] { -1, 0, 0, 1 };
	int[] colNum = new int[] { 0, -1, 1, 0 };

	static class Point {
		public Point(int row, int col) {
			this.x = row;
			this.y = col;
		}
		int x;
		int y;
	}
		static class QueueNode {
		public QueueNode(Point src, int d) {
			this.pt = src;
			this.dist = d;
		}

		public QueueNode(Point src, int d, Point p) {
			this.pt = src;
			this.dist = d;
			shortestPathCoordinate.add(p);
		}

		Point pt; 
		int dist;
		List<Point> shortestPathCoordinate = new ArrayList<>();

		public void addAll(List<Point> pts) {
			shortestPathCoordinate.addAll(pts);
		}
	}

	boolean isValid(int row, int col) {
		return (((row >= 0) && (row < ROW)) && ((col >= 0) && (col < COL)));
	}

	

	List<Point> BFS(int mat[][], Point src, Point dest) {
		if ((mat[src.x][src.y] == 0) || (mat[dest.x][dest.y] == 0))
			return null;

		boolean[][] visited = new boolean[ROW][COL];

		
		visited[src.x][src.y] = true;
		Queue<QueueNode> q = new ArrayDeque<QueueNode>();

		QueueNode s = new QueueNode(src, 0);
		q.add(s); 
		
		while (!q.isEmpty()) {
			QueueNode curr = q.peek();
			Point pt = curr.pt;
			List<Point> temp = curr.shortestPathCoordinate;

			if (pt.x == dest.x && pt.y == dest.y) 
				return curr.shortestPathCoordinate;
	
			q.poll();

			for (int i = 0; i < 4; i++) {
				int row = pt.x + rowNum[i];
				int col = pt.y + colNum[i];

				
				if ((isValid(row, col) && mat[row][col] == 1) && !visited[row][col]) {
					visited[row][col] = true;
					QueueNode adjCell = new QueueNode(new Point(row, col), curr.dist + 1, new Point(row, col));
					adjCell.addAll(temp);
					q.add(adjCell);
				}
			}
		}

		return null;
	}
}
