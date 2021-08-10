package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component  // 이렇게 해서 Bean에 등록하는 방법이 있고, SpringConfig에 @Bean을 사용하여 등록하는 방법이 있다.
public class TimeTraceAop { // MemberService Method 동작 시간을 알아보기 위한 AOP

    @Around("execution(* hello.hellospring..*(..))")    // hello.hellospring 밑에 모든 내용에 아래 Method 모두 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toString() + " 의 Method 동작 시간 Test가 시작합니다!");
        try {
//            Object proceed = joinPoint.proceed();// 다음 Method로 진행
//            return proceed;

            // 위의 Code Inline으로 합친 모습
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println("END : " +joinPoint.toString() + " 의 Method 동작 시간은 " + timeMs + "ms 입니다!");
        } // try 문 끝
    } // excute() 끝
} // Class 끝
