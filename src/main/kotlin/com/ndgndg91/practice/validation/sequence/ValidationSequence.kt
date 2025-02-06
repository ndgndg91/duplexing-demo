package com.ndgndg91.practice.validation.sequence

import jakarta.validation.GroupSequence
import com.ndgndg91.practice.validation.group.AdvancedSequence
import com.ndgndg91.practice.validation.group.BasicSequence

@GroupSequence(BasicSequence::class, AdvancedSequence::class)
interface ValidationSequence