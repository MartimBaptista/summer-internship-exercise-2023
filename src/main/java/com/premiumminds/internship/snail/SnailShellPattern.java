package com.premiumminds.internship.snail;

import java.util.concurrent.*;

/**
 * Created by aamado on 05-05-2023.
 */
class SnailShellPattern implements ISnailShellPattern {
  
  private ExecutorService executor = Executors.newSingleThreadExecutor();

  /**
   * Method to get snailshell pattern
   * 
   * @param matrix matrix of numbers to go through
   * @return order array of values thar represent a snail shell pattern
   */
  public Future<int[]> getSnailShell(int[][] matrix) {
    return executor.submit(() -> {
      int[] res = new int[matrix.length * matrix.length];
      int resCounter = 0;

      int shrinkage = 0;
      while(shrinkage * 2 < matrix.length){

        //shrikage reverted
        int revShrinkage = matrix.length - shrinkage - 1;

        //Moving right
        for (int i = shrinkage; i <= revShrinkage; i++) {
          res[resCounter++] = matrix[shrinkage][i];
        }
        
        //Moving down
        for (int i = shrinkage + 1; i <= revShrinkage; i++) {
          res[resCounter++] = matrix[i][revShrinkage];
        }

        //Moving left
        for (int i = revShrinkage - 1; i >= shrinkage; i--) {
          res[resCounter++] = matrix[revShrinkage][i];
        }

        //Moving up
        for (int i = revShrinkage - 1; i >= shrinkage + 1; i--) {
          res[resCounter++] = matrix[i][shrinkage];
        }

        shrinkage++;
      }

      return res;
    });
  }
}
