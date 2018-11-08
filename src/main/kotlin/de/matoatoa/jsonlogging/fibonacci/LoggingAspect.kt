package de.matoatoa.jsonlogging.fibonacci

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.*
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component
class LoggingAspect {
    @Pointcut("execution(* de.matoatoa..*(..))")
    fun method() {
    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    fun webcontroller() {
    }

    @Before("method() && webcontroller()")
    fun logEnterInfo(jointPoint: JoinPoint) = LoggerFactory.getLogger(jointPoint.signature.declaringType)
            .info("Incoming request {}{}", jointPoint.signature.name, jointPoint.args.toMutableList())

    @Before("method() && within(@TraceLogging *) && !webcontroller()")
    fun logEnterMethod(jointPoint: JoinPoint) = LoggerFactory.getLogger(jointPoint.signature.declaringType)
            .debug("Entering method {}{}", jointPoint.signature.name, jointPoint.args.toMutableList())

    @AfterReturning("method() && within(@TraceLogging *)", returning = "returnValue")
    fun logReturnMethod(jointPoint: JoinPoint, returnValue: Any) = LoggerFactory.getLogger(jointPoint.signature.declaringType)
            .debug("Leaving method {}={}", jointPoint.signature.name, returnValue)

    @AfterThrowing("method() && within(@TraceLogging *)", throwing = "up")
    fun logExceptionMethod(jointPoint: JoinPoint, up: Exception) = LoggerFactory.getLogger(jointPoint.signature.declaringType)
            .warn("Throwing {} : {}", jointPoint.signature.toShortString(), up.message)
}
