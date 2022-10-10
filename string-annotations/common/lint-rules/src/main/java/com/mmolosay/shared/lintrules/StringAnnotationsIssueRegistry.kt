package com.mmolosay.shared.lintrules

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.client.api.Vendor
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

@Suppress("UnstableApiUsage")
public class StringAnnotationsIssueRegistry : IssueRegistry() {

    override val issues: List<Issue> =
        listOf(
            EmptyArgumentsDetector.ISSUE,
            SampleCodeDetector.ISSUE, // TODO: remove
        )

    override val api: Int = CURRENT_API

    override val minApi: Int = 8

    override val vendor: Vendor = Vendor(
        vendorName = "Mikhail Malasai",
        feedbackUrl = "https://github.com/mmolosay/StringAnnotations/issues",
        contact = "https://github.com/mmolosay"
    )
}