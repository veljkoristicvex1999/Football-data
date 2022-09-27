//package com.example.ConsumerApp.components;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//@Component
//public class WrapperComponent {
//
//
//    private final AreaComponent areaComponent;
//    private final TeamComponent teamComponent;
//
//    @Autowired
//    public WrapperComponent(AreaComponent areaComponent, TeamComponent teamComponent) {
//        this.areaComponent = areaComponent;
//        this.teamComponent = teamComponent;
//
//
//       ExecutorService executorService = Executors.newFixedThreadPool(2);
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                saveArea();
//            }
//        });
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                saveTeam();
//            }
//        });
//
//    }
//    public void saveArea(){
//
//                areaComponent.saveAreaToDatabase();
//    }
//    public void saveTeam(){
//
//                teamComponent.saveTeamsToDatabase();
//
//    }
//}
