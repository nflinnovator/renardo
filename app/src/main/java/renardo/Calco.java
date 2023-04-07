package renardo;

class Calco {

    static int[] findComputableTargets(int[] computation, int expectedTarget) {
        int[] computableTargets;
        int lowerComputableTarget = findLowerComputableTarget(computation, expectedTarget);
        int upperComputableTarget = findUpperComputableTarget(computation, expectedTarget);
        if (expectedTarget - lowerComputableTarget == upperComputableTarget - expectedTarget) {
            if (lowerComputableTarget == upperComputableTarget) {
                computableTargets = new int[1];
                computableTargets[0] = lowerComputableTarget;
            } else {
                computableTargets = new int[2];
                computableTargets[0] = lowerComputableTarget;
                computableTargets[1] = upperComputableTarget;
            }
        } else {
            if (expectedTarget - lowerComputableTarget > upperComputableTarget - expectedTarget) {
                computableTargets = new int[1];
                computableTargets[0] = upperComputableTarget;
            } else {
                computableTargets = new int[1];
                computableTargets[0] = lowerComputableTarget;
            }
        }
        return computableTargets;
    }

    static int findLowerComputableTarget(int[] computation, int expectedTarget) {
        int lowerComputableTarget = 0;
        boolean isFound = false;
        int counter = 0;
        while (!isFound && counter < computation.length) {
            if (computation[counter] == expectedTarget) {
                lowerComputableTarget = expectedTarget;
                isFound = true;
            } else {
                if (Math.max(expectedTarget, computation[counter]) == expectedTarget
                        && expectedTarget - computation[counter] < expectedTarget - lowerComputableTarget) {
                    lowerComputableTarget = computation[counter];
                }
            }
            counter++;
        }
        return lowerComputableTarget;
    }

    static int findUpperComputableTarget(int[] computation, int expectedTarget) {
        int upperComputableTarget = 0;
        boolean isFound = false;
        int counter = 0;
        while (!isFound && counter < computation.length) {
            if (computation[counter] == expectedTarget) {
                upperComputableTarget = expectedTarget;
                isFound = true;
            } else {
                if (Math.min(expectedTarget, computation[counter]) == expectedTarget
                        && computation[counter] - expectedTarget < expectedTarget - upperComputableTarget) {
                    upperComputableTarget = computation[counter];
                }
            }
            counter++;
        }
        return upperComputableTarget;
    }
}
