package org.yourorghere;

import java.awt.Font;
import java.util.Random;
import java.util.Scanner;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

class memory_allocation
{ 
    // Method to allocate memory to blocks as per Best fit 
    // algorithm 
    public int[] firstFit(int[] blockSize,int[] processSize) 
    { 
        
        int m = blockSize.length; 
        int n = processSize.length; 
        int allocation[] = new int[n]; 
      
        // Initially no block is assigned to any process 
        for (int i = 0; i < allocation.length; i++) 
            allocation[i] = -1; 
      
        // pick each process and find suitable blocks 
        // according to its size ad assign to it 
        for (int i = 0; i < n; i++) 
        { 
            for (int j = 0; j < m; j++) 
            { 
                if (blockSize[j] >= processSize[i]) 
                { 
                    allocation[i] = j; 
                    blockSize[j] -= processSize[i]; 
      
                    break; 
                } 
            } 
           
        } 
      
        return allocation;
    }
    public int[] bestFit(int blockSize[], int m, int processSize[],  
                                                     int n) 
    { 
        // Stores block id of the block allocated to a 
        // process 
        int allocation[] = new int[n]; 
       
        // Initially no block is assigned to any process 
        for (int i = 0; i < allocation.length; i++) 
            allocation[i] = -1; 
       
     // pick each process and find suitable blocks 
        // according to its size ad assign to it 
        for (int i=0; i<n; i++) 
        { 
            // Find the best fit block for current process 
            int bestIdx = -1; 
            for (int j=0; j<m; j++) 
            { 
                if (blockSize[j] >= processSize[i]) 
                { 
                    if (bestIdx == -1) 
                        bestIdx = j; 
                    else if (blockSize[bestIdx] > blockSize[j]) 
                        bestIdx = j; 
                } 
            } 
       
            // If we could find a block for current process 
            if (bestIdx != -1) 
            { 
                // allocate block j to p[i] process 
                allocation[i] = bestIdx; 
       
                // Reduce available memory in this block. 
                blockSize[bestIdx] -= processSize[i]; 
            } 
        } 
       
       
        return allocation;
    }
    public int[] worstFit(int blockSize[], int m, int processSize[],  
                                                     int n) 
    { 
        // Stores block id of the block allocated to a 
        // process 
        int allocation[] = new int[n]; 
       
        // Initially no block is assigned to any process 
        for (int i = 0; i < allocation.length; i++) 
            allocation[i] = -1; 
       
        // pick each process and find suitable blocks 
        // according to its size ad assign to it 
        for (int i=0; i<n; i++) 
        { 
            // Find the best fit block for current process 
            int wstIdx = -1; 
            for (int j=0; j<m; j++) 
            { 
                if (blockSize[j] >= processSize[i]) 
                { 
                    if (wstIdx == -1) 
                        wstIdx = j; 
                    else if (blockSize[wstIdx] < blockSize[j]) 
                        wstIdx = j; 
                } 
            } 
       
            // If we could find a block for current process 
            if (wstIdx != -1) 
            { 
                // allocate block j to p[i] process 
                allocation[i] = wstIdx; 
       
                // Reduce available memory in this block. 
                blockSize[wstIdx] -= processSize[i]; 
            } 
        } 
       
        
        return allocation;
    }    
}

public class Animate1 extends Application {

    private static double SCENE_WIDTH = 800;
    private static double SCENE_HEIGHT = 600;

    static Random random = new Random();

    Canvas canvas;
    GraphicsContext graphicsContext;
     
    AnimationTimer loop;

    Scene scene;

    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();

        canvas = new Canvas(SCENE_WIDTH, SCENE_HEIGHT);

        graphicsContext = canvas.getGraphicsContext2D();
        
        Pane layerPane = new Pane();

        layerPane.getChildren().addAll(canvas);

        root.setCenter(layerPane);

        scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();
        double  y=100;
        int[] arr={100, 500, 200, 300, 600,300};
        graphicsContext.setLineWidth(5);
         graphicsContext.fillText("200", 180, 100);
         graphicsContext.fillText("170", 180, 160);
         graphicsContext.fillText("110", 180, 280);
         graphicsContext.fillText("80", 180, 340);
         graphicsContext.fillText("60", 180, 380);
         graphicsContext.fillText("10", 180, 480);
         graphicsContext.fillText("0", 180, 500);
         graphicsContext.setLineWidth(2);
         int t=100;
         
         int i=arr.length-1;
        graphicsContext.strokeLine(200, 100, 400, 100);
        do{
        graphicsContext.strokeLine(200, t + (arr[i]*2)/10, 400,t+ (arr[i]*2)/10 );
        t=t+(arr[i]*2)/10;
            
        i--;
        }while(i>=0);
        graphicsContext.strokeLine(200, 100, 200, 500);
        graphicsContext.strokeLine(400, 100, 400, 500);
        startAnimation1(y);
        System.out.println("done 1");
        
    }
    
    
    private void startAnimation1(final double y) {
            int[] arr={100, 500, 200, 300, 600,300}; 
            final int[] p = new int[6];
            p[0]=0;
            for(int i=1;i<p.length;i++){
                for(int j=0;j<i;j++){
                p[i] = p[i]+arr[j];
                        }
            }
            
            final int[] processSize = {216,412,112,467};
            String[] arrs = {"1","2","3","4"};
            String[] process = {"216","412","112","467"};
         
          memory_allocation ma = new memory_allocation();
          int[] all = new int[processSize.length];
          Scanner sc = new Scanner(System.in);
          System.out.println("Enter the type of memory allocation method");
          System.out.println("1. FIRST FIT");
          System.out.println("2. BEST FIT");
          System.out.println("3. WORST FIT");
          int ch=sc.nextInt();
          if(ch==1){
              graphicsContext.fillText("Executing First fit", 130, 40);
             all = ma.firstFit(arr, processSize);
          }else if(ch==2){
                graphicsContext.fillText("Executing Best fit", 130, 40);
              all = ma.bestFit(arr, arr.length, processSize,processSize.length);
          }else if(ch==3){
                graphicsContext.fillText("Executing Worst fit", 130, 40);
              all = ma.worstFit(arr, arr.length, processSize,processSize.length);
          }
          final int[] all1 = all;
          String[] alls = new String[all.length];
          for(int i=0;i<alls.length;i++){
              if(all[i]==-1){
                  alls[i] = Integer.toString(all[i]);
              }else{
              alls[i] = Integer.toString(all[i]+1);
              }
          }
           graphicsContext.fillText("Process no", 450, 240);
             graphicsContext.fillText("Process size", 520, 240);
               graphicsContext.fillText("Block no", 600, 240);
               int height = 270;
               for(int i=0;i<4;i++){
               graphicsContext.fillText(arrs[i], 450, height);
               graphicsContext.fillText(process[i], 520,height);
               graphicsContext.fillText(alls[i], 600, height);
               height=height+30;
               }
               
        loop = new AnimationTimer() {
            
            double startX = 200;
            double endX = 400;
            
            double x = startX;
            double speed = 0.3;
                //red rectangle
             double y2 = 100;
           
            @Override
           
            public void handle(long now) {
                int yy = 500;
                
                graphicsContext.setFill(Color.BLUE);
               graphicsContext.fillText("BLOCK SIZES: [100, 500, 200, 300, 600,300]", 450, 60);
                for(int i=0;i<4;i++){
                    if(i==0){
                        graphicsContext.setFill(Color.GREEN);
                        if(all1[i]==-1){
                            
                    graphicsContext.fillText("FIRST PROCESS: Process not allocated", 450, 100);
                        }else{
                        
                    graphicsContext.fillText("FIRST PROCESS: Filling block with green color", 450, 100);
                    }
                    }else if(i==1){
                        graphicsContext.setFill(Color.RED);
                        if(all1[i]==-1){
                             graphicsContext.fillText("SECOND PROCESS:Process not allocated", 450, 130);
                        }
                        else{
                    graphicsContext.fillText("SECOND PROCESS:Filling block with red color", 450, 130);
                        }
                    }else if(i==2){
                         graphicsContext.setFill(Color.AQUA);
                        if(all1[i]==-1){
                          
                    graphicsContext.fillText("THIRD PROCESS:Process not allocated", 450, 160);
                        }
                        else{
                    graphicsContext.fillText("THIRD PROCESS:Filling block with blue color", 450, 160);
                        }
                    }else if(i==3){
                        graphicsContext.setFill(Color.CRIMSON);  
                        if(all1[i]==-1){
                              
                    graphicsContext.fillText("FOURTH PROCESS:Process not allocated", 450, 190);
                        }else{
                              
                    graphicsContext.fillText("FOURTH PROCESS:Filling block with brown color", 450, 190);
                        }   
                    }
                    if(all1[i]!=-1){
                    int f = 0;
                    if(i==0){
                       yy=500;
                            yy=yy-(p[all1[i]]*2)/10; 
                            f = 500-(p[all1[i]]*2)/10; 
                    }
                    else{
                    for(int j=0;j<i;j++){
                        if(all1[j] == all1[i]){
                            yy=500;
                           yy=yy-(p[all1[i]]*2)/10-(processSize[j]*2)/10; 
                           f = 500-(p[all1[i]]*2)/10-(processSize[j]*2)/10;
                           break;
                        }
                        else{
                           yy=500;
                            yy=yy-(p[all1[i]]*2)/10; 
                            f = 500-(p[all1[i]]*2)/10;
                        }
                    }
                    }
                  //  yy = 500-(p[all[i]]*2)/10;
                   
                    do{ 
                    graphicsContext.fillOval(x,yy, 2,2);
                    yy=yy-3;
                }while(yy>f-(processSize[i]*2)/10);
                    }   
                }
                
                x+=speed;

                if( x >= endX) {
                    loop.stop();  
                   
                }
                
            }
          
        };
        
        loop.start();
    }
   private void startAnimation(final double y) {
            
               
        loop = new AnimationTimer() {
            
            double startX = 200;
            double endX = 400;
            
            double x = startX;
            double speed = 0.3;
                //red rectangle
             double y2 = 100;
           
            @Override
           
            public void handle(long now) {
                int yy = 500;
                
                
                    do{ 
                    graphicsContext.fillOval(x,yy, 2,2);
                    yy=yy-3;
                }while(yy>400);
                       
                
                
                x+=speed;

                if( x >= endX) {
                    loop.stop();
                     
                   
                }
                
            }
          
        };
        
        loop.start();
    }
   
    
    public static void main(String[] args) {
        launch(args);
    }
}