package com.mmolosay.shared.lintrules

import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.SourceCodeScanner
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.UCallExpression
import org.jetbrains.uast.UReferenceExpression

@Suppress("UnstableApiUsage")
public class EmptyArgumentsDetector : Detector(), SourceCodeScanner {

//    private val uastTypes = listOf(UCallExpression::class.java)
//
//    override fun getApplicableUastTypes(): List<Class<out UElement>> =
//        uastTypes

    override fun getApplicableMethodNames(): List<String> =
        listOf("Arguments")

//    override fun createUastHandler(context: JavaContext): UElementHandler =
//        object : UElementHandler() {
//
//            override fun visitCallExpression(node: UCallExpression) {
//                val function = node.resolve() ?: return
//                context.report(ISSUE, node, context.getLocation(node), "AA")
//            }
//        }

    override fun visitReference(
        context: JavaContext,
        reference: UReferenceExpression,
        referenced: PsiElement
    ) {
        super.visitReference(context, reference, referenced)
    }

    override fun visitMethodCall(
        context: JavaContext,
        node: UCallExpression,
        method: PsiMethod
    ) {
        super.visitMethodCall(context, node, method)
        val evaluator = context.evaluator
        if (evaluator.isMemberInClass(method, "com.mmolosay.stringannotations.compose.args.ArgumentsUtilsKt")) {
            report(context, node)
        }
    }

    private fun report(context: JavaContext, node: UCallExpression) =
        context.report(
            issue = ISSUE,
            scope = node,
            location = context.getCallLocation(
                call = node,
                includeReceiver = true,
                includeArguments = true
            ),
            message = "AAAc"
        )

    public companion object {

        @JvmField
        public val ISSUE: Issue = Issue.create(
            id = "EmptyAnnotationArguments",
            briefDescription = "Arguments are empty",
            explanation = """You should not create empty arguments like this, use `emptyArguments()`""", // TODO
            category = Category.CORRECTNESS,
            priority = 9,
            severity = Severity.INFORMATIONAL, // TODO: Severity.INFORMATIONAL
            androidSpecific = true, // TODO:
            implementation = Implementation(
                EmptyArgumentsDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )
    }
}