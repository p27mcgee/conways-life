package com.example.conwayslife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Conway's Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        GameOfLifePanel panel = new GameOfLifePanel(50, 50);
        frame.add(panel);

        frame.setVisible(true);
    }
}

class GameOfLifePanel extends JPanel implements ActionListener {
    private final int rows, cols;
    private boolean[][] grid;
    private Timer timer;

    public GameOfLifePanel(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new boolean[rows][cols];
        this.timer = new Timer(300, this);

        // Random initial state
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                grid[r][c] = Math.random() < 0.2;

        this.setPreferredSize(new Dimension(600, 600));
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int cellWidth = getWidth() / cols;
                int cellHeight = getHeight() / rows;
                int col = e.getX() / cellWidth;
                int row = e.getY() / cellHeight;
                if (row >= 0 && row < rows && col >= 0 && col < cols) {
                    grid[row][col] = !grid[row][col];
                    repaint();
                }
            }
        });

        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellWidth = getWidth() / cols;
        int cellHeight = getHeight() / rows;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c]) {
                    g.setColor(Color.BLACK);
                    g.fillRect(c * cellWidth, r * cellHeight, cellWidth, cellHeight);
                }
                g.setColor(Color.LIGHT_GRAY);
                g.drawRect(c * cellWidth, r * cellHeight, cellWidth, cellHeight);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean[][] next = new boolean[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int neighbors = countNeighbors(r, c);
                next[r][c] = grid[r][c] ? (neighbors == 2 || neighbors == 3) : (neighbors == 3);
            }
        }
        grid = next;
        repaint();
    }

    private int countNeighbors(int row, int col) {
        int count = 0;
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue;
                int r = row + dr, c = col + dc;
                if (r >= 0 && r < rows && c >= 0 && c < cols && grid[r][c]) count++;
            }
        }
        return count;
    }
}