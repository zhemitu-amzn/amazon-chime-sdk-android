package com.amazon.chime.sdk.media.mediacontroller

import com.amazon.chime.sdk.media.clientcontroller.AudioClientController
import com.amazon.chime.sdk.media.clientcontroller.AudioClientObserver
import com.amazon.chime.sdk.media.enums.SignalStrength
import com.amazon.chime.sdk.media.enums.VolumeLevel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class DefaultRealtimeControllerTest {
    private val observer = object : RealtimeObserver {
        override fun onVolumeChange(attendeeVolumes: Map<String, VolumeLevel>) {
        }

        override fun onSignalStrengthChange(attendeeSignalStrength: Map<String, SignalStrength>) {
        }
    }

    @MockK
    private lateinit var audioClientObserver: AudioClientObserver

    @MockK
    private lateinit var audioClientController: AudioClientController

    @InjectMockKs
    private lateinit var realtimeController: DefaultRealtimeController

    @Before
    fun setup() = MockKAnnotations.init(this, relaxUnitFun = true)

    @Test
    fun `realtimeLocalMute should call audioClientController setMute with true and return the status`() {
        every { audioClientController.setMute(true) } returns true
        assertTrue(realtimeController.realtimeLocalMute())
        verify { audioClientController.setMute(true) }
    }

    @Test
    fun `realtimeLocalUnmute should call audioClientController setMute with false and return the status`() {
        every { audioClientController.setMute(false) } returns true
        assertTrue(realtimeController.realtimeLocalUnmute())
        verify { audioClientController.setMute(false) }
    }

    @Test
    fun `realtimeAddObserver should call audioClientController subscribeToRealTimeEvents with given observer`() {
        realtimeController.realtimeAddObserver(observer)
        verify { audioClientObserver.subscribeToRealTimeEvents(observer) }
    }

    @Test
    fun `realtimeRemoveObserver should call audioClientController unsubscribeFromRealTimeEvents with given observer`() {
        realtimeController.realtimeRemoveObserver(observer)
        verify { audioClientObserver.unsubscribeFromRealTimeEvents(observer) }
    }
}