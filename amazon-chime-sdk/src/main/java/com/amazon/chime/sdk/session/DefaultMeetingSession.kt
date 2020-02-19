package com.amazon.chime.sdk.session

import android.content.Context
import com.amazon.chime.sdk.media.AudioVideoFacade
import com.amazon.chime.sdk.media.DefaultAudioVideoFacade
import com.amazon.chime.sdk.media.clientcontroller.AudioClientController
import com.amazon.chime.sdk.media.clientcontroller.AudioClientObserver
import com.amazon.chime.sdk.media.clientcontroller.DefaultAudioClientController
import com.amazon.chime.sdk.media.clientcontroller.DefaultAudioClientObserver
import com.amazon.chime.sdk.media.devicecontroller.DefaultDeviceController
import com.amazon.chime.sdk.media.mediacontroller.DefaultAudioVideoController
import com.amazon.chime.sdk.media.mediacontroller.DefaultRealtimeController
import com.amazon.chime.sdk.utils.logger.Logger

class DefaultMeetingSession(
    override val configuration: MeetingSessionConfiguration,
    override val logger: Logger,
    context: Context
) : MeetingSession {

    override val audioVideo: AudioVideoFacade

    init {
        val audioClientObserver: AudioClientObserver = DefaultAudioClientObserver(logger)
        val audioClientController: AudioClientController = DefaultAudioClientController(
            context,
            logger,
            audioClientObserver
        )

        val audioVideoController =
            DefaultAudioVideoController(audioClientController, audioClientObserver, configuration)
        val realtimeController =
            DefaultRealtimeController(audioClientController, audioClientObserver)
        val deviceController = DefaultDeviceController(context, audioClientController)
        audioVideo = DefaultAudioVideoFacade(
            context,
            audioVideoController,
            realtimeController,
            deviceController
        )
    }
}