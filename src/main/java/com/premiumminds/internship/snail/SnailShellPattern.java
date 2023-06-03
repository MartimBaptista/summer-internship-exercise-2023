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
   * @return order array of values thar represent a snail shell pattern, empty array if invalid input matrix
   */
  public Future<int[]> getSnailShell(int[][] matrix) {
    return executor.submit(() -> {
      //Simple empty matrix and squre matrix check
      //assuming that matrix[0].legth == matrix[any].legth (might not)
      if(matrix.length == 0 || matrix[0].length == 0 || matrix.length != matrix[0].length){ 
        return new int[0];
      }

      //Array that will be returned
      int[] res = new int[matrix.length * matrix.length];
      int resCounter = 0;
      
      //Shrinkage represents how many outside layers have been read
      int shrinkage = 0;
      while(shrinkage * 2 < matrix.length){

        //shrinkage reversed (used like shrinkage but for the right and bottom side of the snail)
        int revShrinkage = matrix.length - shrinkage - 1;

        //Moving right (Top side)
        for (int i = shrinkage; i <= revShrinkage; i++) {
          res[resCounter++] = matrix[shrinkage][i];
        }
        
        //Moving down (Right side) *
        for (int i = shrinkage + 1; i <= revShrinkage; i++) {
          res[resCounter++] = matrix[i][revShrinkage];
        }

        //Moving left (Bottom side) *
        for (int i = revShrinkage - 1; i >= shrinkage; i--) {
          res[resCounter++] = matrix[revShrinkage][i];
        }

        //Moving up (Left side) *
        for (int i = revShrinkage - 1; i >= shrinkage + 1; i--) {
          res[resCounter++] = matrix[i][shrinkage];
        }

        shrinkage++;
        //* Right, Bottom and Left side i counters start with an ofset of 1 
        // to skip the corner values that were read by the previous movement
      }

      return res;
    });
  }
}
