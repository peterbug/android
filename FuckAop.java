package com.evergrande.eif.business.missions.home;

//abcd

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;
import java.util.Map;
import java.util.function.BiConsumer;

//@Aspect
public class FuckAop {
    @Pointcut("execution(* android.view.View.onMeasure(..))")
    public void pointcut2() {
        Log.e("XXX", this.getClass().getSimpleName() + " pointcut2: ");
    }

    //    @Pointcut("staticinitialization(com.evergrande.eif.business.missions.home.HDHomeManager.s_instance)")
//    public void pointcut10() {
////        Log.e("XXX", this.getClass().getSimpleName() + " pointcut2: ");
//    }
    @Before("staticinitialization(com.evergrande.eif.business.missions.home.HDHomeManager)")
    public void beforeStaticBlock(JoinPoint joinPoint) {
        Log.e("XXX", this.getClass().getSimpleName() + " beforeStaticBlock: ");
    }

    @Before("initialization(com.evergrande.eif.business.missions.home.HDHomeManager.new())")
    public void beforeStatic(JoinPoint joinPoint) {
        Log.e("XXX", this.getClass().getSimpleName() + " initialization: ");
    }

    //    @Pointcut("execution(* android.support.v4.app.FragmentActivity*.onResume(..))")
//    public void pointcut3() {
//        Log.e("XXX", this.getClass().getSimpleName() + " pointcut3: ");
//    }
//
//    @Pointcut("execution(* android.app.Activity*.onCreate(..))")
//    public void pointcut4() {
//        Log.e("XXX", this.getClass().getSimpleName() + " pointcut4: ");
//    }
//
//    @Pointcut("execution(* android.app.TaskRecord.Application.onCreate(..))")
//    public void pointcut5() {
//        Log.e("XXX", this.getClass().getSimpleName() + " pointcut5: ");
//    }
//
//    @Pointcut("execution(* com.android.server.am.TaskRecord*.performClearTaskLocked(..))")
//    public void pointcut6() {
//        Log.e("XXX", this.getClass().getSimpleName() + " pointcut6: ");
//    }
//
//    @Pointcut("execution(* com.dy.gesture.ActivityA.startAc1(..))")
//    public void pointcut7() {
//        Log.e("XXX", this.getClass().getSimpleName() + " pointcut7: ");
//    }
//
//    //    @Pointcut("get(* abc.ThreadPoolService.drMaxTh)")
    @Pointcut("get(* com.evergrande.eif.userInterface.activity.modules.homePage.HDHomePageShowActivity.exchangeMission)")
    public void pointcut8() {
        Log.e("XXX", this.getClass().getSimpleName() + " pointcut8: ");
    }

    //    @Before("set(HDHomeMission com.evergrande.eif.userInterface.activity.modules.homePage.HDHomePageShowActivity.exchangeMission)")
//    @Before("set(* com.evergrande.eif.userInterface.activity.modules.homePage.HDHomePageShowActivity.exchangeMission)")
//    @Pointcut("set(HDHomeMission com.evergrande.eif.userInterface.activity.modules.homePage.HDHomePageShowActivity.exchangeMission)")
    @Pointcut("set(* com.evergrande.eif.userInterface.activity.modules.homePage.HDHomePageShowActivity.exchangeMission)")
    public void pointcut9() {
        Log.e("XXX", this.getClass().getSimpleName() + " pointcut9: ");
    }

    @Around("pointcut2()")
    public void around2(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.e("XXX", this.getClass().getSimpleName() + " " + joinPoint + " " + joinPoint.getSignature() + " " + Arrays.toString(joinPoint.getArgs()) + " " + joinPoint.getTarget());
        joinPoint.proceed();
    }

    //    @Around("pointcut3()")
//    public void around3(ProceedingJoinPoint joinPoint) throws Throwable {
//        final StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + " around33: ");
//        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
//        if (map != null && map.size() > 0) {
//            int x = 0;
//            map.forEach(new BiConsumer<Thread, StackTraceElement[]>() {
//                @Override
//                public void accept(Thread thread, StackTraceElement[] stackTraceElements) {
//                    if (stackTraceElements != null && stackTraceElements.length > 0) {
//                        for (StackTraceElement e : stackTraceElements) {
////                            sb.append(e.getFileName() + ">" + e.getMethodName() + ">" + e.getLineNumber());
////                            sb.append("\n");
//                        }
//                    }
//                }
//            });
//        }
//        Log.e("XXX", sb.toString());
//        joinPoint.proceed();
//    }
//
//    @Around("pointcut4()")
//    public void around4(ProceedingJoinPoint joinPoint) throws Throwable {
//        final StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + " around44: ");
//        Log.e("XXX", sb.toString());
//        joinPoint.proceed();
//    }
//
//    @Around("pointcut5()")
//    public void around5(ProceedingJoinPoint joinPoint) throws Throwable {
//        Log.e("XXX", this.getClass().getSimpleName() + " around5x: ");
//        joinPoint.proceed();
//    }
//
//    @Around("pointcut6()")
//    public void around6(ProceedingJoinPoint joinPoint) throws Throwable {
//        Log.e("XXX", this.getClass().getSimpleName() + " around6x: ");
//        joinPoint.proceed();
//    }
//
//    @Around("pointcut7()")
//    public void around7(ProceedingJoinPoint joinPoint) throws Throwable {
//        Log.e("XXX", this.getClass().getSimpleName() + " around7x: ");
//        joinPoint.proceed();
//    }
//
    @AfterReturning(pointcut = "pointcut8()", returning = "exchangeMission")
    public void around8(HDHomeMission exchangeMission) {
        Log.e("XXX", this.getClass().getSimpleName() + " around8x: " + exchangeMission);
    }

    //
    @Around("pointcut9()")
    public void around9(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.e("XXX", this.getClass().getSimpleName() + " around9x: " + Arrays.toString(joinPoint.getArgs()));
        joinPoint.proceed();
    }

//    @Around("initialization()")
//    public void pointcut10(ProceedingJoinPoint joinPoint) throws Throwable {
//        Log.e("XXX", this.getClass().getSimpleName() + " pointcut10: " + joinPoint.getArgs());
//    }
}
