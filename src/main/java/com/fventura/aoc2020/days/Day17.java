package com.fventura.aoc2020.days;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fventura.aoc2020.common.Day;

public class Day17 implements Day {

    final String[] input;
    Set<Cube> onCubes;
    Set<Cube4d> onCubes4d;

    public Day17() {
        onCubes = new HashSet<>();
        onCubes4d = new HashSet<>();
        input = loadDayStrings(17);
        for (int i = 0; i < input.length; i++) {
            String line = input[i];
            int onIndex;
            while ((onIndex = line.indexOf("#")) != -1) {
                onCubes.add(new Cube(i, onIndex, 0));
                onCubes4d.add(new Cube4d(i, onIndex, 0, 0));
                line = line.replaceFirst("#", " ");
            }
        }
    }

    @Override
    public Object part1() {
        return getOnCubes(6);
    }

    @Override
    public Object part2() {
        return getOnCubes4d(6);
    }

    private Object getOnCubes(int iterations) {
        for (int i = 0; i < iterations; i++) {
            final Set<Cube> newCubes = new HashSet<>(Set.copyOf(onCubes));
            for (Cube cube : onCubes) {
                Set<Cube> neighbors = cube.getNeighbors();
                int activeNeighbors = 0;
                for (Cube neighbor : neighbors) {
                    if (onCubes.contains(neighbor)) {
                        activeNeighbors++;
                    } else {
                        Set<Cube> neighborsNeighbors = neighbor.getNeighbors();
                        int activeNeighborNeighbors = 0;
                        for (Cube neighborsNeighbor : neighborsNeighbors) {
                            if (onCubes.contains(neighborsNeighbor)) {
                                activeNeighborNeighbors++;
                            }
                        }
                        if (activeNeighborNeighbors == 3) {
                            newCubes.add(neighbor);
                        }
                    }
                }
                if (activeNeighbors == 2 || activeNeighbors == 3) {
                    newCubes.add(cube);
                } else {
                    newCubes.remove(cube);
                }
                ;
            }
            onCubes = newCubes;
        }
        return onCubes.size();
    }

    private Object getOnCubes4d(int iterations) {
        for (int i = 0; i < iterations; i++) {
            final Set<Cube4d> newCubes = new HashSet<>(Set.copyOf(onCubes4d));
            for (Cube4d cube : onCubes4d) {
                Set<Cube4d> neighbors = cube.getNeighbors();
                int activeNeighbors = 0;
                for (Cube4d neighbor : neighbors) {
                    if (onCubes4d.contains(neighbor)) {
                        activeNeighbors++;
                    } else {
                        Set<Cube4d> neighborsNeighbors = neighbor.getNeighbors();
                        int activeNeighborNeighbors = 0;
                        for (Cube4d neighborsNeighbor : neighborsNeighbors) {
                            if (onCubes4d.contains(neighborsNeighbor)) {
                                activeNeighborNeighbors++;
                            }
                        }
                        if (activeNeighborNeighbors == 3) {
                            newCubes.add(neighbor);
                        }
                    }
                }
                if (activeNeighbors == 2 || activeNeighbors == 3) {
                    newCubes.add(cube);
                } else {
                    newCubes.remove(cube);
                }
                ;
            }
            onCubes4d = newCubes;
        }
        return onCubes4d.size();
    }

    private static class Cube {
        int x;
        int y;
        int z;

        public Cube(final int x, final int y, final int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public Set<Cube> getNeighbors() {
            final HashSet<Cube> neighbors = new HashSet<>();
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    for (int k = z - 1; k <= z + 1; k++) {
                        if (i == x && j == y && k == z) {
                            continue;
                        }
                        neighbors.add(new Cube(i, j, k));
                    }
                }
            }
            return neighbors;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cube cube = (Cube) o;
            return x == cube.x && y == cube.y && z == cube.z;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z);
        }
    }

    private static class Cube4d {
        int x;
        int y;
        int z;
        int w;

        public Cube4d(final int x, final int y, final int z, final int w) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.w = w;
        }

        public Set<Cube4d> getNeighbors() {
            final HashSet<Cube4d> neighbors = new HashSet<>();
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    for (int k = z - 1; k <= z + 1; k++) {
                        for (int l = w - 1; l <= w + 1; l++) {
                            if (i == x && j == y && k == z && l == w) {
                                continue;
                            }
                            neighbors.add(new Cube4d(i, j, k, l));
                        }
                    }
                }
            }
            return neighbors;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cube4d cube4d = (Cube4d) o;
            return x == cube4d.x && y == cube4d.y && z == cube4d.z && w == cube4d.w;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z, w);
        }
    }
}

