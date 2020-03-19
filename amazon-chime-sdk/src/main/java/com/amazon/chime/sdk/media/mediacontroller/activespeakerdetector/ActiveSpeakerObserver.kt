/*
 * Copyright (c) 2020 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 */

package com.amazon.chime.sdk.media.mediacontroller.activespeakerdetector

import com.amazon.chime.sdk.media.mediacontroller.AttendeeInfo

/**
 * [ActiveSpeakerObserver] provides API calls to allow an
 * observer to listen to active speaker updates
 */

interface ActiveSpeakerObserver {
    /**
     * @property scoreCallbackIntervalMs: Int? Specifies period (in milliseconds) of updates for
     * onActiveSpeakerScoreChange. If this is null, the observer will not get active
     * speaker score updates. Should be a value greater than 0.
     */
    val scoreCallbackIntervalMs: Int?

    /**
     * Notifies observers of changes to active speaker
     *
     * @param attendeeInfo: Array<[AttendeeInfo]> - An array of active speakers
     */
    fun onActiveSpeakerDetect(attendeeInfo: Array<AttendeeInfo>)

    /**
     * Periodically sends active speaker scores to observers ONLY IF
     * scoreCallbackIntervalMs is not null
     *
     * @param scores: Map<[AttendeeInfo], Double> - Map of active speakers to respective scores.
     * Scores of 0 mean the attendee is not an active speaker.
     */
    fun onActiveSpeakerScoreChange(scores: Map<AttendeeInfo, Double>)
}